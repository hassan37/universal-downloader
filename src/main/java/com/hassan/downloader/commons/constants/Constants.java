package com.hassan.downloader.commons.constants;

public enum Constants {
	
	CONFIG_FILE_NAME("config.xml"),
	FTP_DEF_USER("anonymous"),
	FTP_DEF_PWD("anonymous"),
	;

	public final String val;
	
	private Constants(String val) { this.val = val; }
}
