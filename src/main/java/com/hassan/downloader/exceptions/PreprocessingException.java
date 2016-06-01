package com.hassan.downloader.exceptions;

public class PreprocessingException extends Exception {
	
	private static final long serialVersionUID = -9201509946540222048L;

	public PreprocessingException() { super(); }

	public PreprocessingException(String msg) { super(msg); }

	public PreprocessingException(Throwable excep) { super(excep); }

	public PreprocessingException(String msg, Throwable excep) { super(msg, excep); }

}
