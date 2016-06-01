package com.hassan.downloader.commons;

import com.hassan.downloader.http.HttpDownloaderClient;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;

public enum DownloaderFactory {
	INSTANCE;

	public Downloader get(final DownloadRequest req, final AppResponse resp) {
		Downloader d = null;
		switch (req.getProtocol()) {
		case HTTP:
			d = HttpDownloaderClient.getInstance(req, resp);
			break;
		case FTP:
			break;
		case UNSUPPORTED:
			break;
		}

		return d;
	}
}
