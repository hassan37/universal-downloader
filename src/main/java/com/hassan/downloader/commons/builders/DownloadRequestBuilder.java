package com.hassan.downloader.commons.builders;

import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.pojos.OutputFile;

import java.net.URL;

public final class DownloadRequestBuilder {
	
	private DownloadRequestBuilder() {}

	public static DownloadRequest build(final URL url, final OutputFile file) {
		DownloadRequest req = new DownloadRequest(url, file);

		return req;
	}

}
