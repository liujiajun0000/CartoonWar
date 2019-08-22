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
* @Description: ��Ϸ�ͻ���
* @author ljj
* @date 2019��8��17�� ����1:32:44
*
*/
public class GameClient extends Frame{
	
	//���ͼƬ��˸
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
	
	//����һ��Boss����
	public List<Plane> bosss = new ArrayList<Plane>();
	
	//����һ������
	BackGround backImg = new BackGround(0,0,"com/img/background.jpg");
	
//	//����һ������
//	Prop prop = new Prop(500,500,"com/img/Prop.png");
	
	//����һ���ɻ�����
//	Plane plane = new Plane(100,200,"com/img/plane.png",this,true);
	
	//����һ���ӵ�
//	Bullet bullet = new Bullet(500, 200, "com/img/Bullet.png");
	
	//�������߼���
	public List<Prop> props =new ArrayList<Prop>();
	
	//�����ӵ�����
	public List<Bullet> bullets = new ArrayList<Bullet>();
	
	//С���������
	Random random =new Random();
	int x =random.nextInt(1500); 
	int y =random.nextInt(800);
	//����С��
	EnemyPlane enemyplane=new EnemyPlane(800,50,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane1=new EnemyPlane(800,230,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane2=new EnemyPlane(800,410,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane3=new EnemyPlane(800,590,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane4=new EnemyPlane(1100,50,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane5=new EnemyPlane(1100,230,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane6=new EnemyPlane(1100,410,"com/img/EnemyPlane.png",this,false);
	EnemyPlane enemyplane7=new EnemyPlane(1100,590,"com/img/EnemyPlane.png",this,false);
	 
	//����С�ּ���
	public List<Plane> enemys= new ArrayList<Plane>();
	
	//����һ����ը����
	public List<Boom> booms = new ArrayList();
	
	//�����ҷ���ɫ����
	public List<Plane> planes = new ArrayList<Plane>();
	
	Plane plane = null;
	
	//���ɿͻ��˴���
	public void launchFrame() {
		SoundPlayer soundPlayer =new SoundPlayer("com/sound/background.mp3");
		soundPlayer.start();
		
		//���ڴ�С
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		
		//����
		this.setTitle("�ɻ���ս");
		
		//�Ƿ���ʾ����
		this.setVisible(true);
		
		//��ͼ��
		Image img = GetImage.getImg("com/img/�ɻ�.jpg");
		this.setIconImage(img);
		
		//��ֹ���
//		this.setResizable(false);
		
		//���ھ���
		this.setLocationRelativeTo(null);
		
		//�رմ���         ��ӹرռ�����
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		 plane=new Plane(100,200,"com/img/plane.png",this,true);
		 //�ɻ�����������Լ�
		 planes.add(plane);
		
		//���������
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				plane.mouseClicked(e);
			}
		});
		
		//��Ӽ��̼���
		this.addKeyListener(new KeyAdapter() {
		
			//���¼���
			@Override
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}
		
			//�ͷż���
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		
		//�����߳�
		new paintThread().start();
		
		//С�����������С��
		enemys.add(enemyplane);
		enemys.add(enemyplane1);
		enemys.add(enemyplane2);
		enemys.add(enemyplane3);
		enemys.add(enemyplane4);
		enemys.add(enemyplane5);
		enemys.add(enemyplane6);
		enemys.add(enemyplane7);
		//���boss
		bosss.add(new Boss(1600, 350, this,false));
	} 
	
		//��дpaint����
		@Override
		public void paint(Graphics g) {
		    backImg.draw(g);
//			g.drawLine(1200, 0, 1200, 800);
		    for(int i=0;i<planes.size();i++) {
				Plane plane2 = planes.get(i);
				plane2.draw(g);
				plane2.containItems(props);
			}
		//ѭ������ÿ���ӵ�
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
		 g.drawString("��ǰ�ӵ�������"+bullets.size(), 10, 50);
		 g.drawString("��ǰ�����������"+enemys.size(), 10, 70);
		 g.drawString("��ǰ��ը������"+booms.size(), 10, 90);
		 g.drawString("��ǰ����������"+props.size(), 10, 110);
		//ѭ������С��
		 for(int i=0;i<enemys.size();i++) {
			 enemys.get(i).draw(g);
		 }
		
		 //ѭ����ը
		 for(int i=0;i<booms.size();i++) {
			 if(booms.get(i).isLive()==true) {
				 booms.get(i).draw(g);
			 }
		 }
		//ѭ������
		 for(int i=0;i<props.size();i++) {
				 props.get(i).draw(g);
		 }
		 if(enemys.size()==0) {
			 //ѭ��boss����
			 for(int i=0;i<bosss.size();i++) {
				 bosss.get(i).draw(g);
			 }
		 }
	}
	
	//�ڲ���
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
 