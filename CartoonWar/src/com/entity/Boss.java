package com.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.action.ActionAble;
import com.client.GameClient;
import com.constant.Constant;
import com.util.GetImage;

public class Boss extends Plane implements ActionAble{
	private boolean up = true; 
	
	private int speed = 5;
	
	public Boss() {

	}
	public Boss(int x,int y,GameClient gc,boolean isGood) {
		this.x=x;
		this.y=y;
		this.gc=gc;
		this.isGood=isGood;
		this.blood=50000;
	}
	
	//动态初始化一个图片数组
	private static Image[] imgs = new Image[6];
	static {
		for(int i=0;i<imgs.length;i++) {
			imgs[i] = GetImage.getImg("com/img/boss"+(i+1)+".png");
		}
	}
	int count =0;
	@Override
	public void draw(Graphics g) {
		if(count>5) {
			count=0;
		}
		g.drawImage(imgs[count++],x,y,null);
		move();
		g.drawString("当前血量："+blood, x, y);
	}
	@Override
	public void move() {
		x-=speed;
		if(x<1200) {
			x=1200;
			if(up) {
				y-=speed;
			}
			if(!up) {
				y+=speed;
			}
			if(y>Constant.GAME_HEIGHT-imgs[0].getHeight(null)) {
				up = true;
			}
			if(y<30) {
				up = false;
			}
		}
		if(random.nextInt(500)>450) {
			fire();
		}
	}
	//生成随机数
	Random random = new Random();
	//获取Boss的矩形
	 public Rectangle getRec() {
	    	return new Rectangle(x, y, this.imgs[0].getWidth(null), this.imgs[0].getHeight(null));
	    }
	 public void fire() {
			singlePlay.play("com/sound/bulletFire.mp3");
	    	Bullet b = new Bullet(x,y+this.imgs[0].getHeight(null)/2-40,"com/img/bulletBoss.png",gc,false);
	    	gc.bullets.add(b);
	 }
}
