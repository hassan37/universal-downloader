package com.hassan.downloader.commons;

public enum Protocol {

	HTTP("http"),
	FTP("ftp"),
	UNSUPPORTED(""),
	;
	
	private final String val;
	
	private Protocol(String val) { this.val = val; }
	
	public static Protocol getByStr(String protocolStr) {
		Protocol prot = UNSUPPORTED;
		for (Protocol p : Protocol.values()) {
			if (p.val.equalsIgnoreCase(protocolStr))
				prot = p;
		}

		return prot;
	}
}
