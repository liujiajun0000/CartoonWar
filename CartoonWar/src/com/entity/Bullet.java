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
* @Description: 子弹类
* @author ljj
* @date 2019年8月19日 上午11:40:46
*
*/
public class Bullet extends GameObj{
    
	//单次播放音乐的对象
	SinglePlay singlePlay = new SinglePlay();
	
	//拿到客户端
	public GameClient gc;
	
	//子弹类型
	public boolean isGood;
	
	//创建速度
	private int speed;
	public Bullet() {

	}
    //子弹的构造器
	public Bullet(int x,int y,String imgName,GameClient gc,boolean isGood) {
    	this.x=x;
    	this.y=y;
    	this.img=GetImage.getImg(imgName);
    	this.speed=20;
    	this.gc=gc;
    	this.isGood=isGood;
    }
    //继承的move方法
	private void move() {
    	if(isGood) {
    		x+=speed;
    	}else {
    		x-=speed;
    	}
    	outOfBounds();
	}
    //继承的draw方法
	public void draw(Graphics g) {
    	g.drawImage(img,x,y,null);
    	move();
	}
    //子弹越界销毁
    public void outOfBounds() {
    	if(x>Constant.GAME_WIDTH || x<0) {
    		gc.bullets.remove(this);
    	}
    }
    //随机生成道具
    Random random = new Random();
    //子弹打一个怪物
    public boolean hitMonster(Plane plane) {
    	Boom boom=new Boom(plane.x,plane.y,gc);
    	if(this.getRec().intersects(plane.getRec()) && this.isGood!=plane.isGood) {
    		if(plane.isGood) {
    			singlePlay.play("com/sound/bulletEnemy.mp3");
    			plane.blood-=10;
    			//移除子弹
	    		gc.bullets.remove(this);
	    		singlePlay.play("com/sound/EnemyDie.mp3");
    			if(plane.blood==0) {
    				//销毁飞机
        			gc.planes.remove(plane);
        			//添加爆炸
            		gc.booms.add(boom);
        			singlePlay.play("com/sound/planeDie.mp3");
        			//飞机死亡判断
        			plane.isLive=false;
        			//移除子弹
    	    		gc.bullets.remove(this);
    				}
    			}else{
    				singlePlay.play("com/sound/bulletBoom.mp3");
    				if(plane instanceof Boss) {
    					plane.blood-=100;
      					//移除子弹
    					gc.bullets.remove(this);
    					if(plane.blood<=0) {
        					gc.bosss.remove(plane);
//        					//移除子弹
//        					gc.bullets.remove(this);
        					//添加爆炸
        		    		gc.booms.add(boom);
        					singlePlay.play("com/sound/EnemyDie.mp3");
    					}
    				}
    				else{
    					//移除打中怪物
    		    		gc.enemys.remove(plane);
    		    		//添加爆炸
    		    		gc.booms.add(boom);
    		    		//移除子弹
    		    		gc.bullets.remove(this);
    		    		//随机生成一个道具
    		    		if(random.nextInt(500)>400) {
    		    			Prop prop = new Prop(plane.x,plane.y,"com/img/Prop.png");
    		    			gc.props.add(prop);
    		    	}
    			}
    		}
    		//添加爆炸
//    		gc.booms.add(boom);
	    	return true;
    	}
    	return false;
    }
    //子弹打多个怪物
    public boolean hitMonsters(List<Plane> planes) {
    	for(int i=0;i<planes.size();i++) {
    		if(hitMonster(planes.get(i))) {
    			return true;
    		}
    	}return false;
    }
    
    //获取到子弹的矩形
    public Rectangle getRec() {
    	return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
    }
}
