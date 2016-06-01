package com.hassan.downloader.processor;

import java.io.IOException;

import org.apache.commons.collections.CollectionUtils;

import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.exceptions.PreprocessingException;

class Preprocessor {
	
	private Preprocessor() {}
	
	public static Preprocessor getInstance() { return new Preprocessor(); }
	
	public void perform() throws PreprocessingException {
		
		//1. load application configurations
		loadApplicationConfigurations();
		
		//2. Load Supported protocols
		checkSupportedProtocols();
		
		//3. TODO: Whatever requires to be done before processing, keep on adding functions here
	}

	private void checkSupportedProtocols() throws PreprocessingException {
		if (CollectionUtils.isEmpty(AppConfig.INSTANCE.getSupportedProtocols())) {
			String eMsg = "There are no protocols are being supported right now.";
			throw new PreprocessingException(eMsg);
		}
	}

	private void loadApplicationConfigurations() throws PreprocessingException {
		try {
			AppConfig.INSTANCE.load();
			System.out.println("Application configuration loaded.");
		} catch (IOException e) {
			String eMsg = "Loading Configuration File failed due to: " + e.getMessage();
			throw new PreprocessingException(eMsg, e);
		}
	}

}
