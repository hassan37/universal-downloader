package com.hassan.downloader.protocols;

import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;

abstract class DefDownloader implements Downloader {
	
	final DownloadRequest req;
	final AppResponse resp;
	
	DefDownloader(final DownloadRequest req, final AppResponse resp) {
		this.req = req;
		this.resp = resp;
	}


}
