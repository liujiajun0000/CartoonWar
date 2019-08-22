package com.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImage
* @Description: 获取图片
* @author ljj
* @date 2019年8月17日 下午2:10:54
*
*/
public class GetImage {
	//获取图片的方法
	public static Image getImg(String imgName) {
		//反射
		URL resource = GetImage.class.getClassLoader().getResource(imgName);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}
}
