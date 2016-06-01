package com.hassan.downloader.commons.factories;

import com.hassan.downloader.http.HttpDownloader;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;

public enum DownloaderFactory {
	INSTANCE;

	public Downloader getDownloader(final DownloadRequest req, final AppResponse resp) {
		Downloader d = null;
		switch (req.getProtocol()) {
		case HTTP:
			d = HttpDownloader.getInstance(req, resp);
			break;
		case FTP:
			break;
		case UNSUPPORTED:
			break;
		}

		return d;
	}
}
