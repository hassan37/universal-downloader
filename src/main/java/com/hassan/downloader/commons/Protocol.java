package com.hassan.downloader.commons;

import java.util.Set;

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
	
	public static String[] toStrArr(Set<Protocol> protocolsSet) {
		String[] protocolsStrArr = new String[protocolsSet.size()];
		int i = 0;
		for (Protocol p : protocolsSet) {
			protocolsStrArr[i] = p.val;
			i++;
		}

		return protocolsStrArr;
	}

}
