package com.hassan.downloader.commons;

import java.net.URL;

import com.hassan.downloader.pojos.DownloadRequest;

public final class DownloadRequestBuilder {
	
	private DownloadRequestBuilder() {}

	public static DownloadRequest build(URL url) {
		DownloadRequest req = new DownloadRequest(url);

		return req;
	}

}
