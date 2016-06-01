package com.hassan.downloader.processor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.commons.validator.routines.UrlValidator;

import com.hassan.downloader.Application.Processor;
import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.Protocol;
import com.hassan.downloader.commons.exceptions.PreprocessingException;
import com.hassan.downloader.commons.factories.DownloaderFactory;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;

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
		List<URL> validURLs = getValidURLs(urls);

		List<DownloadRequest> reqs = getDownloadRequests(validURLs);

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

	private List<DownloadRequest> getDownloadRequests(List<URL> validURLs) {
		List<DownloadRequest> reqs = null;
		return reqs;
	}

	private void processRequests(List<DownloadRequest> reqs) {
		initExecutorService();
		for (DownloadRequest req : reqs) {
			Downloader d = DownloaderFactory.INSTANCE.getDownloader(req, resp);
		}		
	}

//-------------------------------------------------------------------------------- Post Processing

	private void initExecutorService() {
		
	}

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
