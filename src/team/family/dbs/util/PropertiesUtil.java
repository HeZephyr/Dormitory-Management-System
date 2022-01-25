package team.family.dbs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	//获取dorm.properties中的属性。
	public static String getValue(String key) {
		Properties  prop = new Properties();
		InputStream in = new PropertiesUtil().getClass().getResourceAsStream("/dorm.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String)prop.get(key);
	}
}
