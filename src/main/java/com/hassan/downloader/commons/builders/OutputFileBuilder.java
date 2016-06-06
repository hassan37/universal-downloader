package com.hassan.downloader.commons.builders;

import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.commons.constants.Separator;
import com.hassan.downloader.pojos.OutputFile;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class OutputFileBuilder {

	private OutputFileBuilder() {}

	public static OutputFile build(final URL url) {
		OutputFile f = null;
		if (null != url) {
			String uniqueFileName = buildUniqueFileName(url);
			String targetDir = AppConfig.INSTANCE.getStrProp(ConfigPropKey.TARGET_DIR);
			Path p = Paths.get(targetDir, uniqueFileName);
			f = new OutputFile(p);
		}

		return  f;
	}

	private static String buildUniqueFileName(final URL url) {
		String path = url.getPath();
		int startIndex = path.lastIndexOf(Separator.FWD_SLASH.val) + 1;
		int endIndex = path.lastIndexOf (Separator.DOT.val, path.length());
		String fileName = path.substring(startIndex, endIndex);
		String ext = path.substring(endIndex+1);

		return fileName + Separator.UNDER_SCORE.val + System.currentTimeMillis() + Separator.DOT.val + ext;
	}
}
