package com.hassan.downloader.http;

import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;

public class HttpDownloader implements Downloader {

	final DownloadRequest req;

	final AppResponse resp;
	
	public static Downloader getInstance(final DownloadRequest req, final AppResponse resp) {
		return new HttpDownloader(req, resp);
	}

	private HttpDownloader(final DownloadRequest req, final AppResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void download(DownloadRequest req, AppResponse callbackResp) {
		// TODO Auto-generated method stub

	}

}