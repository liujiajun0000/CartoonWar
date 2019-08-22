package com.constant;
/**
* @ClassName: Constant
* @Description: TODO(������һ�仰��������������)
* @author ljj
* @date 2019��8��17�� ����1:38:35
*
*/
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constant {
	//��ζ�ȡ�����ļ�
	
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
	
	//���
	public static final int GAME_WIDTH = Game_Width;
	//�߶�
	public static final int GAME_HEIGHT = Game_Height;

}

