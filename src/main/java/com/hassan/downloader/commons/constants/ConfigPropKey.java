package com.hassan.downloader.commons.constants;

public enum ConfigPropKey {
	
	SUPPORTED_PROTOCOLS("supported_protocols"),
	MAX_THREADS("max_threads")
	;

	public final String val;
	
	private ConfigPropKey(String val) { this.val = val; }
}
