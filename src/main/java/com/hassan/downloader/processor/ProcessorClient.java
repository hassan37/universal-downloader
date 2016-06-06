package com.hassan.downloader.processor;

import com.hassan.downloader.Application.Processor;
import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.Protocol;
import com.hassan.downloader.commons.builders.DownloadRequestBuilder;
import com.hassan.downloader.commons.builders.OutputFileBuilder;
import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.commons.exceptions.PreprocessingException;
import com.hassan.downloader.commons.factories.DownloaderFactory;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.pojos.OutputFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is used to handle and process the URL requests
 * 
 * @author hafiz.hassan
 *
 */
public final class ProcessorClient implements Processor {

	final AppResponse resp;
	
	ExecutorService executor;

	private ProcessorClient() { resp = new AppResponse(); }

	public static Processor getInstance() { return new ProcessorClient(); }

//-------------------------------------------------------------------------------- Pre-processing

	public void performPreprocessing() throws PreprocessingException {
		Preprocessor.getInstance().perform();
	}

//-------------------------------------------------------------------------------- URLs Processing

	public AppResponse process(String[] urls) {

		//1. URLs Validation
		List<URL> validURLs = getValidURLs(urls);

		//2. Prepare Download Requests
		List<DownloadRequest> reqs = prepareDownloadRequests(validURLs);

		//3. process each download request
		processRequests(reqs);

		return resp;
	}

	private List<URL> getValidURLs(String[] urls) {
		String[] supportedProtocols = Protocol.toStrArr(AppConfig.INSTANCE.getSupportedProtocols());
		UrlValidator uv = new UrlValidator(supportedProtocols);
		List<URL> validURLs = null;

		for (String url : urls) {
			if (uv.isValid(url)) {
				if (null == validURLs) {
					validURLs = new ArrayList<>();
				}

				try {
					validURLs.add(new URL(url));
				} catch (MalformedURLException e) {
					resp.addInvalidUrl(url + " : Malformed URL Exception occured. Msg: " + e.getMessage());
				}
			} else {
				resp.addInvalidUrl(url + " : Invalid or Unsupported URL");
			}
		}

		return validURLs;
	}

	private List<DownloadRequest> prepareDownloadRequests(List<URL> urls) {
		List<DownloadRequest> reqs = null;
		if (CollectionUtils.isNotEmpty(urls)) {
			reqs = new ArrayList<>();
			for (URL u : urls) {
				OutputFile f = OutputFileBuilder.build(u);
				DownloadRequest req = DownloadRequestBuilder.build(u, f);
				reqs.add(req);
			}
		}

		return reqs;
	}

	private void processRequests(List<DownloadRequest> reqs) {
		initExecutorService(reqs.size());
		for (DownloadRequest req : reqs) {
			Downloader d = DownloaderFactory.INSTANCE.getDownloader(req, resp);
			executor.submit(d);
		}		
	}

	private void initExecutorService(int reqsCount) {
		Integer maxThreadsCount = AppConfig.INSTANCE.getIntProp(ConfigPropKey.MAX_THREADS);
		int threadsCount = Math.min(reqsCount, null == maxThreadsCount ? 0 : maxThreadsCount);

		executor = Executors.newFixedThreadPool(threadsCount);
	}

//-------------------------------------------------------------------------------- Post Processing

	public boolean performPostprocessing() {
		return false;
	}

//-------------------------------------------------------------------------------- DOWNLOADER INTERFACE

	/**
	 * Processor Client has Abstract Downloader, responsible for processing Download Request
	 * 
	 * @author hafiz.hassan
	 *
	 */
	public interface Downloader extends Runnable {
	
		void download(DownloadRequest req, AppResponse callbackResp);
	}

}
