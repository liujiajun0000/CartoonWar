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
* @Description: 创建一个飞机主角类
* @author ljj
* @date 2019年8月18日 下午9:09:28
*
*/
public class Plane extends GameObj implements ActionAble{
	//单次播放音乐的对象
	SinglePlay singlePlay = new SinglePlay();
	//速度
	private int speed;
	//方向布尔变量
	private boolean left,up,right,down;
	//把客户端拿来
	public GameClient gc;
	//判断飞机还是怪物
	public boolean isGood;
	//判断飞机死活
	public boolean isLive;
	//添加飞机子弹等级
	public int bulletLevel=1;
	//添加血量
	public int blood;
	public Plane() {

	}
    //飞机的构造器
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
	//移动的方法
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
	//画一个飞机
    @Override
	public void draw(Graphics g) {
		move();
		g.drawImage(img, x, y, null);
		g.drawString("当前角色血量为："+blood, x+60, y);
	}
    //处理飞机边界问题
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
    //键盘按下
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
    //键盘释放
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
    //飞机的开火
    public void fire() {
    	singlePlay.play("com/sound/bulletFire.mp3");
    	Bullet b = new Bullet(x+this.img.getWidth(null),y+this.img.getHeight(null)/2,"com/img/bulletLevel"+bulletLevel+".png",gc,true);
    	gc.bullets.add(b);
    }
    //获取到飞机的矩形
    public Rectangle getRec() {
    	return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
    }
    
    //检测飞机拾取道具
    public void containItem(Prop prop) {
    	if(this.getRec().intersects(prop.getRect())) {
    		singlePlay.play("com/sound/propGet.mp3");	
    		//移除道具
    		gc.props.remove(prop);
    		if(bulletLevel>4) {
    			bulletLevel=5;
    			return;
    		}else {
    		//更改子弹等级
    		this.bulletLevel+=1;
    		}
    	}
    }
    //检测飞机拾取多个道具
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
