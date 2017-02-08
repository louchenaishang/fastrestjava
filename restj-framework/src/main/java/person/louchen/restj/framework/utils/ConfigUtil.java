package person.louchen.restj.framework.utils;

import java.util.ResourceBundle;

/**
 * config工具类
 * 
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("conf/config");

	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}

}
