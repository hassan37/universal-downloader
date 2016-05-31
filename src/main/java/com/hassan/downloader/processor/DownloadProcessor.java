package com.hassan.downloader.processor;

import java.io.IOException;

import com.hassan.downloader.UniversalDownloader.Processor;
import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.pojos.DownloadResponse;

/**
 * This class is used to handle and process the URL requests
 * 
 * @author hafiz.hassan
 *
 */
public final class DownloadProcessor implements Processor {
	
	private final static DownloadProcessor INSTANCE = new DownloadProcessor();

	private DownloadProcessor() {}
	
	public static Processor getInstance() { return INSTANCE; }

	public boolean performPreprocessing() {
		boolean performed = false;
		
		//1. load application configurations
		try {
			AppConfig.INSTANCE.load();
			performed = true;
		} catch (IOException e) {
			System.out.println("Loading Configuration File failed due to: " + e.getMessage());
		}

		return performed; 
	}

	public DownloadResponse process(String[] urls) {
		return null;
	}

	public boolean performPostprocessing() {
		return false;
	}
}
