package com.client;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;
import java.awt.Paint;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.management.loading.PrivateClassLoader;
import com.constant.Constant;
import com.entity.BackGround;
import com.entity.Boom;
import com.entity.Boss;
import com.entity.Bullet;
import com.entity.EnemyPlane;
import com.entity.Plane;
import com.entity.Prop;
import com.util.GetImage;
import com.util.SoundPlayer;
/**
* @ClassName: GameClient
* @Description: 游戏客户端
* @author ljj
* @date 2019年8月17日 下午1:32:44
*
*/
public class GameClient extends Frame{
	
	//解决图片闪烁
	@Override
	public void update(Graphics g) {
		Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		Graphics backg = backImg.getGraphics();
		Color color = backg.getColor();
		backg.setColor(color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg,0,0,null);
	}
	
	//创建一个Boss集合
	public List<Plane> bosss = new ArrayList<Plane>();
	
	//创建一个背景
	BackGround backImg = new BackGround(0,0,"com/img/background.jpg");
	
//	//创建一个道具
//	Prop prop = new Prop(500,500,"com/img/Prop.png");
	
	//创建一个飞机主角
//	Plane plane = new Plane(100,200,"com/img/plane.png",this,true);
	
	//创建一颗子弹
//	Bullet bullet = new Bullet(500, 200, "com/img/Bullet.png");
	
	//创建道具集合
	public List<Prop> props =new ArrayList<Prop>();
	
	//创建子弹集合
	public List<Bullet> bullets = new ArrayList<Bullet>();
	
	//小怪重生随机
	Random random =new Random();
	int x =random.nextInt(1500); 
	int y =random.nextInt(800);
	//创建小怪
	EnemyPlane enemyplane=new EnemyPlane(800,50,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane1=new EnemyPlane(800,230,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane2=new EnemyPlane(800,410,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane3=new EnemyPlane(800,590,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane4=new EnemyPlane(1100,50,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane5=new EnemyPlane(1100,230,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane6=new EnemyPlane(1100,410,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane7=new EnemyPlane(1100,590,"com/img/EnemyPlane.png",this,false);
	 
	//创建小怪集合
	public List<Plane> enemys= new ArrayList<Plane>();
	
	//创建一个爆炸集合
	public List<Boom> booms = new ArrayList();
	
	//创建我方角色集合
	public List<Plane> planes = new ArrayList<Plane>();
	
	Plane plane = null;
	
	//生成客户端窗口
	public void launchFrame() {
		SoundPlayer soundPlayer =new SoundPlayer("com/sound/background.mp3");
		soundPlayer.start();
		
		//窗口大小
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		
		//标题
		this.setTitle("飞机大战");
		
		//是否显示窗口
		this.setVisible(true);
		
		//改图标
		Image img = GetImage.getImg("com/img/飞机.jpg");
		this.setIconImage(img);
		
		//禁止最大化
//		this.setResizable(false);
		
		//窗口居中
		this.setLocationRelativeTo(null);
		
		//关闭窗口         添加关闭监听器
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		 plane=new Plane(100,200,"com/img/plane.png",this,true);
		 //飞机容器中添加自己
		 planes.add(plane);
		
		//添加鼠标监听
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				plane.mouseClicked(e);
			}
		});
		
		//添加键盘监听
		this.addKeyListener(new KeyAdapter() {
		
			//按下键盘
			@Override
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}
		
			//释放键盘
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		
		//启动线程
		new paintThread().start();
		
		//小怪容器中添加小怪
		enemys.add(enemyplane);
		enemys.add(enemyplane1);
		enemys.add(enemyplane2);
		enemys.add(enemyplane3);
		enemys.add(enemyplane4);
		enemys.add(enemyplane5);
		enemys.add(enemyplane6);
		enemys.add(enemyplane7);
		//添加boss
		bosss.add(new Boss(1600, 350, this,false));
	} 
	
		//重写paint方法
		@Override
		public void paint(Graphics g) {
		    backImg.draw(g);
//			g.drawLine(1200, 0, 1200, 800);
		    for(int i=0;i<planes.size();i++) {
				Plane plane2 = planes.get(i);
				plane2.draw(g);
				plane2.containItems(props);
			}
		//循环画出每个子弹
		for(int i=0;i<bullets.size();i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(planes);
			bullet.hitMonsters(bosss);
		}
		 //	    for(Bullet bullet:bullets) {
		 //	    	bullet.draw(g);
		 //	    }
	    // bullet.draw(g);
		 g.drawString("当前子弹数量："+bullets.size(), 10, 50);
		 g.drawString("当前怪物的数量："+enemys.size(), 10, 70);
		 g.drawString("当前爆炸的数量"+booms.size(), 10, 90);
		 g.drawString("当前道具数量："+props.size(), 10, 110);
		//循环画出小怪
		 for(int i=0;i<enemys.size();i++) {
			 enemys.get(i).draw(g);
		 }
		
		 //循环爆炸
		 for(int i=0;i<booms.size();i++) {
			 if(booms.get(i).isLive()==true) {
				 booms.get(i).draw(g);
			 }
		 }
		//循环道具
		 for(int i=0;i<props.size();i++) {
				 props.get(i).draw(g);
		 }
		 if(enemys.size()==0) {
			 //循环boss集合
			 for(int i=0;i<bosss.size();i++) {
				 bosss.get(i).draw(g);
			 }
		 }
	}
	
	//内部类
	class paintThread extends Thread{
		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
 