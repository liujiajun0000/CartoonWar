package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Action;

import com.action.ActionAble;
import com.client.GameClient;
import com.constant.Constant;
import com.util.GetImage;
import com.util.SinglePlay;

import javazoom.jl.player.advanced.PlaybackEvent;

/**
* @ClassName: Plane
* @Description: ����һ���ɻ�������
* @author ljj
* @date 2019��8��18�� ����9:09:28
*
*/
public class Plane extends GameObj implements ActionAble{
	//���β������ֵĶ���
	SinglePlay singlePlay = new SinglePlay();
	//�ٶ�
	private int speed;
	//���򲼶�����
	private boolean left,up,right,down;
	//�ѿͻ�������
	public GameClient gc;
	//�жϷɻ����ǹ���
	public boolean isGood;
	//�жϷɻ�����
	public boolean isLive;
	//��ӷɻ��ӵ��ȼ�
	public int bulletLevel=1;
	//���Ѫ��
	public int blood;
	public Plane() {

	}
    //�ɻ��Ĺ�����
	public Plane(int x,int y,String imgName,GameClient gc,boolean isGood) {
    	this.x = x;
    	this.y = y;
    	this.img = GetImage.getImg(imgName);
    	this.speed = 20;
    	this.gc = gc;
    	this.isGood = isGood;
    	this.blood = 100;
    	this.isLive = true;
    }
	//�ƶ��ķ���
    @Override
	public void move() {
    	if(left) {
    		x-=speed;
    	}
    	if(up) {
    		y-=speed;
    	}
    	if(right) {
    		x+=speed;
    	}
    	if(down) {
    		y+=speed;
    	}
    	outOfBound();
	}
	//��һ���ɻ�
    @Override
	public void draw(Graphics g) {
		move();
		g.drawImage(img, x, y, null);
		g.drawString("��ǰ��ɫѪ��Ϊ��"+blood, x+60, y);
	}
    //����ɻ��߽�����
    public void outOfBound() {
    	if(y<=30) {
    		y=30;
    	}
    	if(x<=5) {
    		x=5;
    	}
    	if(x>=Constant.GAME_WIDTH-img.getWidth(null)) {
    		x=Constant.GAME_WIDTH-img.getWidth(null);
    	}
    	if(y>=Constant.GAME_HEIGHT-img.getHeight(null)) {
    		y=Constant.GAME_HEIGHT-img.getHeight(null);
    	}
    }
    public void mouseClicked(MouseEvent e) {
    	if(e.getClickCount()==1) {
    		fire();
    	}
    }
    //���̰���
    public void keyPressed(KeyEvent e) {
    	switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		default:	
			break;
		}
	}
    //�����ͷ�
    public void keyReleased(KeyEvent e) {
    	switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_SPACE:
			if(isLive==true) {
				fire();
			}
			break;
		default:
			break;
		}
    }
    //�ɻ��Ŀ���
    public void fire() {
    	singlePlay.play("com/sound/bulletFire.mp3");
    	Bullet b = new Bullet(x+this.img.getWidth(null),y+this.img.getHeight(null)/2,"com/img/bulletLevel"+bulletLevel+".png",gc,true);
    	gc.bullets.add(b);
    }
    //��ȡ���ɻ��ľ���
    public Rectangle getRec() {
    	return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
    }
    
    //���ɻ�ʰȡ����
    public void containItem(Prop prop) {
    	if(this.getRec().intersects(prop.getRect())) {
    		singlePlay.play("com/sound/propGet.mp3");	
    		//�Ƴ�����
    		gc.props.remove(prop);
    		if(bulletLevel>4) {
    			bulletLevel=5;
    			return;
    		}else {
    		//�����ӵ��ȼ�
    		this.bulletLevel+=1;
    		}
    	}
    }
    //���ɻ�ʰȡ�������
	public void containItems(List<Prop>props) {
		if(props==null) {
			return;
		}else {
			for(int i=0;i<props.size();i++) {
				containItem(props.get(i));
			}
		}
	}
}
