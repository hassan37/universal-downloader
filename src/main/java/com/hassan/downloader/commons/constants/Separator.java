package com.hassan.downloader.commons.constants;

public enum Separator {
	COMMA(","),
	DOT("."),
	FWD_SLASH("/"),
	UNDER_SCORE("_"),
	;
	
	public final String val;
	private Separator(String val) { this.val = val; }
}
