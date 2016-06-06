package com.hassan.downloader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Temp {

	public static void main(String[] args) throws IOException {

		URL url = new URL("http://download.wavetlan.com/SVV/Media/HTTP/mkv/MP4_avc_mp3(720p)(SUPER).MKV");
		File f = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop" + System.getProperty("file.separator") + "MP4_avc_mp3(720p)(SUPER).MKV");
		FileUtils.copyURLToFile(url, f, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
}
