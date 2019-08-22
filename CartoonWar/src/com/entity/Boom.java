package com.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.action.ActionAble;
import com.client.GameClient;
import com.util.GetImage;

/**
* @ClassName: Boom
* @Description: ��ը��
* @author ljj
* @date 2019��8��19�� ����6:31:27
*
*/
public class Boom extends GameObj implements ActionAble{
	//�жϱ�ը�Ƿ����
	private boolean isLive;
	//�ÿͻ���
	private GameClient gc;
	
	public Boom() {
		
	}
	
	public boolean isLive() {
		return isLive;
	}
	
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	//��̬��ʼ����ը
	private static Image[] boomImgs= new Image[9];
	static {
		for(int i=0;i<9;i++) {
			boomImgs[i]=GetImage.getImg("com/img/"+(i+1)+".png");
		}
	}

	//���챬ը
	public Boom(int x,int y,GameClient gc) {
		this.x=x;
		this.y=y;
		this.isLive=true;
		this.gc =gc;
	}
	@Override
	public void move() {
		
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(isLive) {
			if(count>8) {
		    	gc.booms.remove(this);
		    	this.setLive(false);
				return;
			}
			g.drawImage(boomImgs[count++],x,y, null);
		}
	}
}

