package com.entity;

import java.awt.Graphics;

import com.action.ActionAble;
import com.client.GameClient;
import com.util.GetImage;

/**
* @ClassName: BackGround
* @Description: 背景图
* @author ljj
* @date 2019年8月19日 下午2:27:44
*
*/
public class BackGround extends GameObj implements ActionAble{
	private Integer speed;

	public BackGround() {

	}
	public BackGround(int x,int y,String imgName) {
		this.x=x;
		this.y=y;
		this.img =GetImage.getImg(imgName);
		this.speed=1;
	}
	
	@Override
	public void move() {
		x-=speed;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y,null);
		move();
	}

}
