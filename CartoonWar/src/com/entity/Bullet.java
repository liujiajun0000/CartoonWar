package com.entity;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.client.GameClient;
import com.constant.Constant;
import com.util.GetImage;
import com.util.SinglePlay;

/**
* @ClassName: Bullet
* @Description: �ӵ���
* @author ljj
* @date 2019��8��19�� ����11:40:46
*
*/
public class Bullet extends GameObj{
    
	//���β������ֵĶ���
	SinglePlay singlePlay = new SinglePlay();
	
	//�õ��ͻ���
	public GameClient gc;
	
	//�ӵ�����
	public boolean isGood;
	
	//�����ٶ�
	private int speed;
	public Bullet() {

	}
    //�ӵ��Ĺ�����
	public Bullet(int x,int y,String imgName,GameClient gc,boolean isGood) {
    	this.x=x;
    	this.y=y;
    	this.img=GetImage.getImg(imgName);
    	this.speed=20;
    	this.gc=gc;
    	this.isGood=isGood;
    }
    //�̳е�move����
	private void move() {
    	if(isGood) {
    		x+=speed;
    	}else {
    		x-=speed;
    	}
    	outOfBounds();
	}
    //�̳е�draw����
	public void draw(Graphics g) {
    	g.drawImage(img,x,y,null);
    	move();
	}
    //�ӵ�Խ������
    public void outOfBounds() {
    	if(x>Constant.GAME_WIDTH || x<0) {
    		gc.bullets.remove(this);
    	}
    }
    //������ɵ���
    Random random = new Random();
    //�ӵ���һ������
    public boolean hitMonster(Plane plane) {
    	Boom boom=new Boom(plane.x,plane.y,gc);
    	if(this.getRec().intersects(plane.getRec()) && this.isGood!=plane.isGood) {
    		if(plane.isGood) {
    			singlePlay.play("com/sound/bulletEnemy.mp3");
    			plane.blood-=10;
    			//�Ƴ��ӵ�
	    		gc.bullets.remove(this);
	    		singlePlay.play("com/sound/EnemyDie.mp3");
    			if(plane.blood==0) {
    				//���ٷɻ�
        			gc.planes.remove(plane);
        			//��ӱ�ը
            		gc.booms.add(boom);
        			singlePlay.play("com/sound/planeDie.mp3");
        			//�ɻ������ж�
        			plane.isLive=false;
        			//�Ƴ��ӵ�
    	    		gc.bullets.remove(this);
    				}
    			}else{
    				singlePlay.play("com/sound/bulletBoom.mp3");
    				if(plane instanceof Boss) {
    					plane.blood-=100;
      					//�Ƴ��ӵ�
    					gc.bullets.remove(this);
    					if(plane.blood<=0) {
        					gc.bosss.remove(plane);
//        					//�Ƴ��ӵ�
//        					gc.bullets.remove(this);
        					//��ӱ�ը
        		    		gc.booms.add(boom);
        					singlePlay.play("com/sound/EnemyDie.mp3");
    					}
    				}
    				else{
    					//�Ƴ����й���
    		    		gc.enemys.remove(plane);
    		    		//��ӱ�ը
    		    		gc.booms.add(boom);
    		    		//�Ƴ��ӵ�
    		    		gc.bullets.remove(this);
    		    		//�������һ������
    		    		if(random.nextInt(500)>400) {
    		    			Prop prop = new Prop(plane.x,plane.y,"com/img/Prop.png");
    		    			gc.props.add(prop);
    		    	}
    			}
    		}
    		//��ӱ�ը
//    		gc.booms.add(boom);
	    	return true;
    	}
    	return false;
    }
    //�ӵ���������
    public boolean hitMonsters(List<Plane> planes) {
    	for(int i=0;i<planes.size();i++) {
    		if(hitMonster(planes.get(i))) {
    			return true;
    		}
    	}return false;
    }
    
    //��ȡ���ӵ��ľ���
    public Rectangle getRec() {
    	return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
    }
}
