package com.hassan.downloader.protocols;

import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.commons.constants.RequestState;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.io.IOException;

class HttpDownloader extends DefDownloader {

	public static Downloader getInstance(DownloadRequest req, AppResponse resp) { return new HttpDownloader(req, resp); }

	private HttpDownloader(final DownloadRequest req, final AppResponse resp) { super(req, resp); }

	@Override
	public void run() { download(req, resp); }

	@Override
	public void download(final DownloadRequest req, final AppResponse callbackResp) {
		try {
			File f = req.file.path.toFile();
			req.state = RequestState.DOWNLOADING;
			int connectionTimeout = AppConfig.INSTANCE.getIntProp(ConfigPropKey.HTTP_CONNECTION_TIMEOUT);
			int readTimeout = AppConfig.INSTANCE.getIntProp(ConfigPropKey.HTTP_READ_TIMEOUT);
			
			FileUtils.copyURLToFile(req.url, f, connectionTimeout, readTimeout);
			req.state = RequestState.COMPLETED;
		} catch (IOException e) {
			req.state = RequestState.ERROR;
			req.file.setError("Downloading the file is failed due to: " + ExceptionUtils.getRootCauseMessage(e) + "\n\tError Object: " + ExceptionUtils.getStackTrace(e));
		} finally {
			callbackResp.receive(req);
		}
	}

}
