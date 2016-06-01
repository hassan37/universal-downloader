package com.hassan.downloader.commons;

public enum ConfigPropKey {
	
	SUPPORTED_PROTOCOLS("supported_protocols")
	;

	public final String val;
	
	private ConfigPropKey(String val) { this.val = val; }
}
