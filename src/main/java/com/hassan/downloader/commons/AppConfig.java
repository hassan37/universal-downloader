package com.hassan.downloader.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum AppConfig {

	INSTANCE,
	;

	private final Properties CONFIG_PROPS = new Properties();

	public void load() throws IOException {
		try (InputStream in =  getClass().getClassLoader().getResourceAsStream(Constants.CONFIG_FILE_NAME.val);) {
			CONFIG_PROPS.loadFromXML(in);
		}
	}

	public void reload() throws IOException {
		CONFIG_PROPS.clear();
		CONFIG_PROPS.load(getClass().getClassLoader().getResourceAsStream(Constants.CONFIG_FILE_NAME.val));
	}

	public String getStrProp(ConfigPropKey key) {
		return CONFIG_PROPS.getProperty(key.val);
	}
}
