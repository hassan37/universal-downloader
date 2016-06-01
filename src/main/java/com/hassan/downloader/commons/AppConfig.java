package com.hassan.downloader.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.commons.constants.Constants;
import com.hassan.downloader.commons.constants.ListSeparator;

/**
 * 
 * @author hafiz.hassan
 *
 */
public enum AppConfig {

	INSTANCE,
	;

	private final Properties CONFIG_PROPS = new Properties();
	
	private final Set<Protocol> supportedProtocols = EnumSet.noneOf(Protocol.class);

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
	
	public String[] getListProp(ConfigPropKey key, ListSeparator listSeparator) {
		String[] strArr = null;
		String str = CONFIG_PROPS.getProperty(key.val);
		if (StringUtils.isNotBlank(str) && str.contains(listSeparator.val)) {
			strArr = StringUtils.split(str, listSeparator.val);
		}

		return strArr;
	}

	public Set<Protocol> getSupportedProtocols() {
		
		if (CollectionUtils.isEmpty(supportedProtocols)) {
			String[] spStrArr = getListProp(ConfigPropKey.SUPPORTED_PROTOCOLS, ListSeparator.COMMA);
			
			if (ArrayUtils.isEmpty(spStrArr)) {
				for (String sp : spStrArr) {
					Protocol p = Protocol.getByStr(sp);
					if (Protocol.UNSUPPORTED != p) {
						supportedProtocols.add(p);
					}
				} 
			}
		}

		return supportedProtocols;
	}
	
	
}
