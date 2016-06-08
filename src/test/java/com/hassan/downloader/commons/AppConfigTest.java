package com.hassan.downloader.commons;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;

import com.hassan.downloader.commons.constants.ConfigPropKey;
import com.hassan.downloader.commons.constants.Separator;

public class AppConfigTest {

	@Test
	public void testNonEmptyGetStrProp() {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.TARGET_DIR.val, "C:\\Users\\hafiz.hassan\\Desktop\\");
		String testStrProp = AppConfig.INSTANCE.getStrProp(ConfigPropKey.TARGET_DIR);
		assertThat((null != testStrProp && testStrProp.length() > 0), is(Boolean.TRUE));
	}

	@Test
	public void testNotNullNotZeroIntProp() {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.EXECUTOR_TIMEOUT_VALUE.val, "6");
		Integer testIntProp = AppConfig.INSTANCE.getIntProp(ConfigPropKey.EXECUTOR_TIMEOUT_VALUE);
		assertThat((null != testIntProp && testIntProp > 0), is(Boolean.TRUE));
	}

	@Test
	public void testNotNullNotEmptyListProp() {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.SUPPORTED_PROTOCOLS.val, "http,ftp");
		String[] listProp = AppConfig.INSTANCE.getListProp(ConfigPropKey.SUPPORTED_PROTOCOLS, Separator.COMMA);
		assertThat((null != listProp && listProp.length > 0), is(Boolean.TRUE));
	}

	@Test
	public void testNullListProp() {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.SUPPORTED_PROTOCOLS.val, "http,ftp");
		String[] listProp = AppConfig.INSTANCE.getListProp(ConfigPropKey.SUPPORTED_PROTOCOLS, Separator.DOT);
		assertThat((null == listProp), is(Boolean.TRUE));
	}

	@Test
	public void testDaysTimeUnitProp() {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.EXECUTOR_TIMEOUT_UNIT.val, "days");
		TimeUnit tu = AppConfig.INSTANCE.getTimeUnitProp(ConfigPropKey.EXECUTOR_TIMEOUT_UNIT);
		assertThat((TimeUnit.DAYS == tu), is(Boolean.TRUE));
	}

	@Test
	public void testHoursTimeUnitProp() {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.EXECUTOR_TIMEOUT_UNIT.val, "h");
		TimeUnit tu = AppConfig.INSTANCE.getTimeUnitProp(ConfigPropKey.EXECUTOR_TIMEOUT_UNIT);
		assertThat((TimeUnit.HOURS == tu), is(Boolean.TRUE));
	}

	@Test
	public void testMinutesTimeUnitProp() {
		AppConfig.INSTANCE.CONFIG_PROPS.setProperty(ConfigPropKey.EXECUTOR_TIMEOUT_UNIT.val, "mins");
		TimeUnit tu = AppConfig.INSTANCE.getTimeUnitProp(ConfigPropKey.EXECUTOR_TIMEOUT_UNIT);
		assertThat((TimeUnit.MINUTES == tu), is(Boolean.TRUE));
	}

	@After
	public void cleanAppConfigInstance() {
		AppConfig.INSTANCE.CONFIG_PROPS.clear();
	}
}
