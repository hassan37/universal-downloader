package com.hassan.downloader.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.commons.constants.Constants;
import com.hassan.downloader.commons.constants.Protocol;
import com.hassan.downloader.commons.constants.Separator;

public enum AppConfig {

	INSTANCE,
	;

	public final Properties CONFIG_PROPS = new Properties();
	
	private final Set<Protocol> SUPPORTED_PROTOCOLS = EnumSet.noneOf(Protocol.class);

	public void load() throws IOException {
		try (InputStream in =  getClass().getClassLoader().getResourceAsStream(Constants.CONFIG_FILE_NAME.val);) {
			CONFIG_PROPS.loadFromXML(in);
		}
	}

	public void reload() throws IOException {
		CONFIG_PROPS.clear();
		SUPPORTED_PROTOCOLS.clear();
		CONFIG_PROPS.load(getClass().getClassLoader().getResourceAsStream(Constants.CONFIG_FILE_NAME.val));
	}

	public String getStrProp(ConfigPropKey key) {
		return CONFIG_PROPS.getProperty(key.val);
	}

	public Integer getIntProp(ConfigPropKey key) {
		Integer intProp = null;
		try {
			intProp = Integer.parseInt(CONFIG_PROPS.getProperty(key.val));
		} catch (Exception e) {
			System.out.println("Exception occured in interger conversion due to: " + e.getMessage());
		}

		return intProp;
	}

	public String[] getListProp(ConfigPropKey key, Separator separator) {
		String[] strArr = null;
		String str = CONFIG_PROPS.getProperty(key.val);
		if (StringUtils.isNotBlank(str) && str.contains(separator.val)) {
			strArr = StringUtils.split(str, separator.val);
		}

		return strArr;
	}

	public Set<Protocol> getSupportedProtocols() {

		if (CollectionUtils.isEmpty(SUPPORTED_PROTOCOLS)) {
			String[] spStrArr = getListProp(ConfigPropKey.SUPPORTED_PROTOCOLS, Separator.COMMA);

			if (ArrayUtils.isNotEmpty(spStrArr)) {
				for (String sp : spStrArr) {
					Protocol p = Protocol.getByStr(sp);
					if (Protocol.UNSUPPORTED != p) {
						SUPPORTED_PROTOCOLS.add(p);
					}
				} 
			}
		}

		return SUPPORTED_PROTOCOLS;
	}

	public TimeUnit getTimeUnitProp(ConfigPropKey key) {
		TimeUnit tu = null;
		String str = CONFIG_PROPS.getProperty(key.val);
		if (StringUtils.isNotBlank(str)) {
			switch (str) {

			case "days":
			case "d":
			default:
				tu = TimeUnit.DAYS; break;

			case "hours":
			case "h":
				tu = TimeUnit.HOURS; break;

			case "minutes":
			case "mins":
				tu = TimeUnit.MINUTES; break;

			case "seconds":
			case "secs":
			case "s":
				tu = TimeUnit.SECONDS; break;

			case "mil_sec":
				tu = TimeUnit.MILLISECONDS; break;

			case "mic_sec":
				tu = TimeUnit.MICROSECONDS; break;

			case "nano_sec":
				tu = TimeUnit.NANOSECONDS; break;

			}
		}

		return tu;
	}
}
