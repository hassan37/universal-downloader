package com.hassan.downloader.protocols;

import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;

class FtpDownloader extends DefDownloader {

	public static Downloader getInstance(DownloadRequest req, AppResponse resp) { return new FtpDownloader(req, resp); }

	private FtpDownloader(final DownloadRequest req, final AppResponse resp) { super(req, resp); }

	@Override
	public void run() {
		
	}

	@Override
	public void download(DownloadRequest req, AppResponse callbackResp) {
		
	}

}
