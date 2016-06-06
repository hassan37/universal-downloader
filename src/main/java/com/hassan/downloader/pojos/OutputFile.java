package com.hassan.downloader.pojos;

import java.nio.file.Path;

public class OutputFile {

	public final Path path;

	private String error;

	public OutputFile(Path p) { this.path = p; }

	public String getError() { return error; }

	public void setError(String error) { this.error = error; }
}
