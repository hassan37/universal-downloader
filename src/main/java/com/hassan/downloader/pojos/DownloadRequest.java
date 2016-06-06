package com.hassan.downloader.pojos;

import com.hassan.downloader.commons.constants.Protocol;
import com.hassan.downloader.commons.constants.RequestState;

import java.net.URL;

public class DownloadRequest {
	
	public RequestState state;
	
	public final URL url;
	
	public final Protocol protocol;

	public final OutputFile file;
	
	private String ftpUser;
	private String ftpPwd;

	public DownloadRequest(final URL url, final OutputFile file) {
		this.url = url;
		this.protocol = Protocol.getByStr(url.getProtocol());
		this.file = file;
		this.state = RequestState.PENDING;
	}

	public String getFtpUser() { return ftpUser; }

	public void setFtpUser(String ftpUser) { this.ftpUser = ftpUser; }

	public String getFtpPwd() { return ftpPwd; }

	public void setFtpPwd(String ftpPwd) { this.ftpPwd = ftpPwd; }
	
}
