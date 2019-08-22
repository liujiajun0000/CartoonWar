package com.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.Action;

import com.action.ActionAble;
import com.client.GameClient;
import com.util.GetImage;

/**
* @ClassName: EnemyPlane
* @Description: 敌人类
* @author ljj
* @date 2019年8月19日 下午3:30:21
*
*/
public  class EnemyPlane extends Plane implements ActionAble{
	//声明速度
	private int speed;
	//拿客户端
	private GameClient gc;
	
	public EnemyPlane() {
	
	}
	//怪物构造器
	public EnemyPlane(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x=x;
		this.y=y;
		this.img=GetImage.getImg(imgName);
		this.speed=3;
		this.gc=gc;
		this.isGood=isGood;
	}
	@Override
	public void move() {
		x-=speed;
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);
		move();
		if(random.nextInt(500)>490) {
			fire();
		}
	}
	//随机数
	Random random = new Random();
	
	
	//小怪开火
	public void fire() {
		Bullet bullet = new Bullet(x,y+img.getHeight(null)/2,"com/img/fire.png",gc,false);
		gc.bullets.add(bullet);
		}	
	//获取到子弹的矩形
    public Rectangle getRec() {
    	return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
    }
}
