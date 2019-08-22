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
* @Description: ������
* @author ljj
* @date 2019��8��19�� ����3:30:21
*
*/
public  class EnemyPlane extends Plane implements ActionAble{
	//�����ٶ�
	private int speed;
	//�ÿͻ���
	private GameClient gc;
	
	public EnemyPlane() {
	
	}
	//���ﹹ����
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
	//�����
	Random random = new Random();
	
	
	//С�ֿ���
	public void fire() {
		Bullet bullet = new Bullet(x,y+img.getHeight(null)/2,"com/img/fire.png",gc,false);
		gc.bullets.add(bullet);
		}	
	//��ȡ���ӵ��ľ���
    public Rectangle getRec() {
    	return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
    }
}
