package com.hassan.downloader.commons.builders;

import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.pojos.OutputFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OutputFileBuilderTest {

	@Before
	public void setUp() throws Exception {
		AppConfig.INSTANCE.load();
	}

	@Test
	public void testOutfileGenerationWithUniqueName() throws MalformedURLException {
		URL url = new URL("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_50mb.mp4");
		OutputFile f = OutputFileBuilder.build(url);
		System.out.println(f);
		Assert.assertTrue(f.path.getFileName().toString().contains("big_buck_bunny_720p_50mb"));
		Assert.assertTrue(f.path.getFileName().toString().contains(".mp4"));

	}

}