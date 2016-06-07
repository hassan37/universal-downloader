package com.hassan.downloader.pojos;

import java.io.IOException;
import java.nio.file.Files;
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
			System.out.println("File [" + req.file.path.getFileName().toString() + "] " + req.file.getError());
			try {
				Files.deleteIfExists(req.file.path);
			} catch (IOException e) {
				System.out.println("Exception occurred during deleting the file due to: " + e.getMessage());
			}
			break;
		case DOWNLOADING:
		case PENDING:
		default:
			break;
		}
	}

	public boolean addInvalidUrl(String url) {
		return getInvalidURLs().add(url);
	}

}
