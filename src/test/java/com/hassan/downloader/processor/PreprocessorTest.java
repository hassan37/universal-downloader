package com.hassan.downloader.processor;

import org.junit.After;
import org.junit.Test;

import com.hassan.downloader.commons.AppConfig;
import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.commons.exceptions.PreprocessingException;

public class PreprocessorTest {

	@Test(expected=PreprocessingException.class)
	public void testExceptionOnEmptyListOfSupportedProtocols() throws PreprocessingException {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.SUPPORTED_PROTOCOLS.val, "");
		Preprocessor.getInstance().checkSupportedProtocols();
	}

	@After
	public void cleanAppConfigInstance() {
		AppConfig.INSTANCE.CONFIG_PROPS.clear();
	}
}
