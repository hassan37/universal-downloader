package com.hassan.downloader.protocols;

import com.hassan.downloader.commons.constants.RequestState;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class HttpDownloader implements Downloader {

	final DownloadRequest req;
	final AppResponse resp;
	
	public static Downloader getInstance(DownloadRequest req, AppResponse resp) { return new HttpDownloader(req, resp); }

	private HttpDownloader(final DownloadRequest req, final AppResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	@Override
	public void run() { download(req, resp); }

	@Override
	public void download(final DownloadRequest req, final AppResponse callbackResp) {
		try {
			File f = new File(req.file.path.toString());
			req.state = RequestState.DOWNLOADING;
			FileUtils.copyURLToFile(req.url, f);
			req.state = RequestState.COMPLETED;
		} catch (IOException e) {
			req.state = RequestState.ERROR;
			req.file.setError("Downloading the file is failed due to: " + e.getMessage() + "\n Error Object: " + e.toString());
		} finally {
			callbackResp.receive(req);
		}
	}

}
