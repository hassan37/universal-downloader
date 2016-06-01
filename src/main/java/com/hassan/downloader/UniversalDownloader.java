package com.hassan.downloader;

import com.hassan.downloader.exceptions.PreprocessingException;
import com.hassan.downloader.pojos.DownloadResponse;
import com.hassan.downloader.processor.DownloadProcessor;

public class UniversalDownloader {
	
	public Processor proc;
	
	public static UniversalDownloader getInstance() { return new UniversalDownloader(); }
	
	public static void main(String[] args) throws PreprocessingException {
		if (args.length > 0) {
			
			UniversalDownloader app = getInstance();
			
			app.proc = DownloadProcessor.getInstance();
			
			app.proc.performPreprocessing();
			
			app.proc.process(args);
			
			app.proc.performPostprocessing();

		} else {
			System.out.println("Nothing to download. Empty list of URLs.");
		}
	}

	/**
	 * The processor responsible for processing the Download Requests
	 * 
	 * @author hafiz.hassan
	 *
	 */
	public interface Processor {
		
		void performPreprocessing() throws PreprocessingException;
		
		DownloadResponse process(String[] urls);
		
		boolean performPostprocessing();
	}
	
}
