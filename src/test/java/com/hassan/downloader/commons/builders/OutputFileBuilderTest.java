package com.hassan.downloader.commons.builders;

import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Test;

import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.pojos.OutputFile;


public class OutputFileBuilderTest {
	
	private final URL url;
	
	public OutputFileBuilderTest() throws MalformedURLException {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.TARGET_DIR.val, "C:\\Users\\hafiz.hassan\\Desktop\\");
		url = new URL("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_50mb.mp4");
	}
	
	@Test
	public void testOutputfileUniqueNameGeneration() throws MalformedURLException {
		OutputFile f1 = OutputFileBuilder.build(url);
		OutputFile f2 = OutputFileBuilder.build(url);
		
		Boolean isUnique = 
				null != f1 && 
				null != f2 && 
				!f1.path.getFileName().toString().equals(f2.path.getFileName().toString());
		System.out.println(f1.path.getFileName().toString());
		System.out.println(f2.path.getFileName().toString());
		assertThat(isUnique, CoreMatchers.is(Boolean.TRUE));
	}
	
	@Test
	public void testOutputfileExtensionValidity() throws MalformedURLException {
		OutputFile f = OutputFileBuilder.build(url);
		String fileName = f.path.getFileName().toString();
		assertThat(fileName, CoreMatchers.endsWith(".mp4"));
	}
	
	@Test
	public void testOutputfileNameValidity() throws MalformedURLException {
		OutputFile f = OutputFileBuilder.build(url);
		String fileName = f.path.getFileName().toString();
		assertThat(fileName, CoreMatchers.containsString("big_buck_bunny_720p_50mb"));
	}

	@After
	public void setUp() throws Exception {
		AppConfig.INSTANCE.CONFIG_PROPS.clear();
	}
}