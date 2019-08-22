package com.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImage
* @Description: ��ȡͼƬ
* @author ljj
* @date 2019��8��17�� ����2:10:54
*
*/
public class GetImage {
	//��ȡͼƬ�ķ���
	public static Image getImg(String imgName) {
		//����
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
