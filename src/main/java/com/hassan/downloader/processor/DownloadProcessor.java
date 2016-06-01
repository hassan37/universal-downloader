package com.hassan.downloader.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;

import com.hassan.downloader.UniversalDownloader.Processor;
import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.Protocol;
import com.hassan.downloader.exceptions.PreprocessingException;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.pojos.DownloadResponse;

/**
 * This class is used to handle and process the URL requests
 * 
 * @author hafiz.hassan
 *
 */
public final class DownloadProcessor implements Processor {
	
	final DownloadRequest req;
	final DownloadResponse rsp;
	
	private DownloadProcessor() {
		req = new DownloadRequest();
		rsp = new DownloadResponse();
	}
	
	public static Processor getInstance() { return new DownloadProcessor(); }

	public void performPreprocessing() throws PreprocessingException {
		Preprocessor.getInstance().perform();
	}

	public DownloadResponse process(String[] urls) {
		List<String> validURLs = getValidURLs(urls);
		
		for (String url : urls) {
/*			if (UrlValidator.) {
				
			}*/
		}

		return rsp;
	}

	private List<String> getValidURLs(String[] urls) {
		String[] supportedProtocols = Protocol.toStrArr(AppConfig.INSTANCE.getSupportedProtocols());
		UrlValidator uv = new UrlValidator(supportedProtocols);
		List<String> validURLs = null;

		for (String url : urls) {
			if (uv.isValid(url)) {
				if (null == validURLs) {
					validURLs = new ArrayList<>();
				}

				validURLs.add(url);
			} else {
				rsp.addInvalidUrl(url);
			}
		}

		return validURLs;
	}

	public boolean performPostprocessing() {
		return false;
	}
}
