package com.hassan.downloader.protocols;

import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;

public enum Downloaders {
	INSTANCE;

	public Downloader getDownloader(final DownloadRequest req, final AppResponse resp) {
		Downloader d = null;
		switch (req.protocol) {
		case HTTP:
			d = HttpDownloader.getInstance(req, resp);
			break;
		case FTP:
			d = FtpDownloader.getInstance(req, resp);
			break;
		case UNSUPPORTED:
			break;
		}

		return d;
	}
}
