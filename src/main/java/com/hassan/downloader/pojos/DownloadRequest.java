package com.hassan.downloader.pojos;

import java.net.URL;

import com.hassan.downloader.commons.Protocol;
import com.hassan.downloader.commons.constants.RequestState;

public class DownloadRequest {
	
	private RequestState state;
	
	private final URL url;
	
	private final Protocol protocol;
	
	public DownloadRequest(final URL url) {
		this.url = url;
		this.protocol = Protocol.getByStr(url.getProtocol());
		this.state = RequestState.PENDING;
	}

	public RequestState getState() { return state; }
	public void setState(RequestState state) { this.state = state; }

	public URL getUrl() { return url; }

	public Protocol getProtocol() { return protocol; }
	
}
