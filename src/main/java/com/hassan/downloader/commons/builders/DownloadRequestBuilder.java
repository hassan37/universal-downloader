package com.hassan.downloader.commons.builders;

import java.net.URL;

import com.hassan.downloader.commons.constants.Constants;
import com.hassan.downloader.commons.constants.Protocol;
import com.hassan.downloader.pojos.DownloadRequest;
import com.hassan.downloader.pojos.OutputFile;

public final class DownloadRequestBuilder {
	
	private DownloadRequestBuilder() {}

	public static DownloadRequest build(final URL url, final OutputFile file) {
		DownloadRequest req = new DownloadRequest(url, file);
		if (req.protocol == Protocol.FTP) {
			req.setFtpUser(Constants.FTP_DEF_USER.val);
			req.setFtpPwd(Constants.FTP_DEF_PWD.val);
		}

		return req;
	}

}
