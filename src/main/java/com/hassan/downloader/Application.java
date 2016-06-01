package com.hassan.downloader;

import com.hassan.downloader.commons.exceptions.PreprocessingException;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.processor.ProcessorClient;

public class Application {
	
	public Processor proc;
	
	public static Application getInstance() { return new Application(); }
	
	public static void main(String[] args) throws PreprocessingException {
		if (args.length > 0) {
			
			Application app = getInstance();
			
			app.proc = ProcessorClient.getInstance();
			
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
		
		AppResponse process(String[] urls);
		
		boolean performPostprocessing();
	}
	
}
