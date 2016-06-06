package com.hassan.downloader.pojos;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppResponse {

	private List<String> invalidURLs;

	public List<String> getInvalidURLs() {
		if (null == invalidURLs) {
			invalidURLs = new ArrayList<>();
		}

		return invalidURLs;
	}

	public void receive(DownloadRequest req) {
		switch (req.state) {
			case COMPLETED:
		 		System.out.println("File is downloaded successfully at: " + req.file.path.toString());
				break;
			case ERROR:
				System.out.println("File is failed to download due to: " + req.file.getError());
				try {
					FileUtils.forceDelete(new File(req.file.path.toString()));
				} catch (IOException e) {
					System.out.println("Exception occurred during deleting the file due to: " + e.getMessage());
				}
				break;
		}
	}

	public boolean addInvalidUrl(String url) {
		return getInvalidURLs().add(url);
	}
	
}
