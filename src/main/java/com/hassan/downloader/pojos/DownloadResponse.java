package com.hassan.downloader.pojos;

import java.util.ArrayList;
import java.util.List;

public class DownloadResponse {

	private List<String> invalidURLs;

	public List<String> getInvalidURLs() {
		if (null == invalidURLs) {
			invalidURLs = new ArrayList<>();
		}

		return invalidURLs;
	}

	public boolean addInvalidUrl(String url) {
		return getInvalidURLs().add(url);
	}
	
}
