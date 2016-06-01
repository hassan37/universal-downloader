package com.hassan.downloader.processor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;

import com.hassan.downloader.Application.Processor;
import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.Protocol;
import com.hassan.downloader.exceptions.PreprocessingException;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.pojos.AppResponse;

/**
 * This class is used to handle and process the URL requests
 * 
 * @author hafiz.hassan
 *
 */
public final class DownloadProcessor implements Processor {
	
	final AppResponse resp;
	
	private DownloadProcessor() {
		resp = new AppResponse();
	}
	
	public static Processor getInstance() { return new DownloadProcessor(); }

	public void performPreprocessing() throws PreprocessingException {
		Preprocessor.getInstance().perform();
	}

	public AppResponse process(String[] urls) {
		List<URL> validURLs = getValidURLs(urls);
		List<DownloadRequest> reqs = getDownloadRequests(validURLs);
		
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

	public boolean performPostprocessing() {
		return false;
	}
}
