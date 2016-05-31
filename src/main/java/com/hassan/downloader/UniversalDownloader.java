package com.hassan.downloader;

import java.io.IOException;

import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.ConfigPropKey;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.pojos.DownloadResponse;
import com.hassan.downloader.processor.DownloadProcessor;

public class UniversalDownloader {
	
	public Processor proc;
	
	public static UniversalDownloader getInstance() { return new UniversalDownloader(); }
	
	public static void main(String[] args) {
		if (args.length > 0) {
			
			UniversalDownloader app = getInstance();
			
			app.proc = DownloadProcessor.getInstance();
			
			app.pro

			//Download
			
			//1. Load prerequisites 
			if (ud.loadPrerequisites()) {
				System.out.println(AppConfig.INSTANCE.getStrProp(ConfigPropKey.WELCOME_MESSAGES));
			}
		} else {
			System.out.println("Nothing to download. Empty list of URLs.");
		}
	}

//--------------------------- Downloader Interface ---------
	
	public interface Processor {
		
		boolean performPreprocessing();
		
		DownloadResponse process(String[] urls);
		
		boolean performPostprocessing();
	}
	
}
