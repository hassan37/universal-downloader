package com.hassan.downloader.protocols;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.hassan.downloader.commons.constants.RequestState;
import com.hassan.downloader.pojos.AppResponse;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.processor.ProcessorClient.Downloader;

class FtpDownloader extends DefDownloader {
	
	private FTPClient fc;

	public static Downloader getInstance(DownloadRequest req, AppResponse resp) { return new FtpDownloader(req, resp); }

	private FtpDownloader(final DownloadRequest req, final AppResponse resp) {
		super(req, resp);
		fc = new FTPClient();
	}

	@Override
	public void run() { download(req, resp); }

	@Override
	public void download(DownloadRequest req, AppResponse callbackResp) {
		try {
			if (initFtpClientConnection()) {
				File f = new File(req.file.path.toString());
				req.state = RequestState.DOWNLOADING;
				FileUtils.copyInputStreamToFile(fc.retrieveFileStream(req.url.getPath()), f);
				req.state = RequestState.COMPLETED;
			}
		} catch (IOException e) {
			error("Downloading the file is failed due to: " + e.getMessage() + "\n Error Object: " + e);
		} finally {
			callbackResp.receive(req);
			terminateFtpClientConnection();
		}
	}

	private boolean initFtpClientConnection() {
		boolean connected  = false;
		try {
			fc.connect(req.url.getHost(), req.url.getPort() > 0 ? req.url.getPort() : req.url.getDefaultPort());
			if (FTPReply.isPositiveCompletion(fc.getReplyCode())) {
				if(fc.login(req.getFtpUser(), req.getFtpPwd())) {
					fc.enterLocalPassiveMode();
					fc.setFileType(FTP.BINARY_FILE_TYPE);
				}
				
			}
		} catch (IOException e) {
			error("FTP Connection is failed due to: " + e.getMessage() + "\n Error Object: " + e);
		}

		return connected;
	}

	private void terminateFtpClientConnection() {
		if (fc.isAvailable()) {
			try {
				fc.logout();
				fc.disconnect();
			} catch (IOException e) {
				System.out.println("Error occured during closing the connection due to: " + e);
			} finally { fc = null; }
		}
	}

	private void error(String msg) {
		req.state = RequestState.ERROR;
		req.file.setError(msg);
	}

}
