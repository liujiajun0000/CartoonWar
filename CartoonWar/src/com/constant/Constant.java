package com.constant;
/**
* @ClassName: Constant
* @Description: TODO(这里用一句话描述这个类的作用)
* @author ljj
* @date 2019年8月17日 下午1:38:35
*
*/
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constant {
	//如何读取配置文件
	
	static Integer Game_Width = null;
	static Integer Game_Height = null;
	
	public static Properties prop = new Properties();
	static {
		InputStream inputStream = Constant.class.getResourceAsStream("/gameConfiguration.properties");
		try {
			prop.load(inputStream);
			Game_Width = Integer.parseInt(prop.getProperty("Game_Width"));
			Game_Height = Integer.parseInt(prop.getProperty("Game_Height"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//宽度
	public static final int GAME_WIDTH = Game_Width;
	//高度
	public static final int GAME_HEIGHT = Game_Height;

}

