package com.fly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//JPanel中存在反序列化，不写会出现警告
@SuppressWarnings("serial")
public class ShootGame extends JPanel{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 654;
	public static BufferedImage airplane;
	public static BufferedImage background;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage gameover;
	public static BufferedImage hero1;
	public static BufferedImage hero0;
	public static BufferedImage start;
	public static BufferedImage pause;
	private Timer timer;
	int interval = 15;//间隔时间
	int shootIndex = 0;
	int flyEnteredIndex = 10;
	private int score = 0;//分数
	private int state;//游戏状态
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;
	
	public Hero hero = new Hero();
	public Bullet[] bullets = {};
	public FlyingObject[] flyings = {};
	
	public ShootGame(){
		flyings = new FlyingObject[2];
		flyings[0] = new Airplane();
		flyings[1] = new Bee();
		bullets = new Bullet[1];
		bullets[0] = new Bullet(195,350);
	}
	//加载图片
	static{
		try{
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//将图片添加进面板
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		paintHero(g);
		paintBullets(g);
		paintFlyingObject(g);
		paintScore(g);
		paintState(g);
	}
	//画状态
	public void paintState(Graphics g){
		switch(state){
		case START:
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:
			g.drawImage(pause,0,0,null);
			break;
		case GAMEOVER:
			g.drawImage(gameover,0,0,null);
			break;
		}
	}
	//画分数和命
	public void paintScore(Graphics g){
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF,Font.BOLD,25);
		g.setFont(font);
		g.setColor(new Color(0xFFFFFF));//设置颜色
		g.drawString("SCORE:"+score,x,y);
		g.drawString("LIFE:"+hero.getLife(),x,y+30);
	}
	//画飞行物
	public void paintFlyingObject(Graphics g){
		for(int i=0;i<flyings.length;i++){
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);
		}
	}
	//画子弹
	public void paintBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);
		}
	}
	//画英雄机
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	
	public void action(){
		MouseAdapter ma = new MouseAdapter(){
			//鼠标移动
			public void mouseMoved(MouseEvent e){
				if(state==RUNNING){
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x,y);
				}
			}
			//鼠标点击
			public void mouseClicked(MouseEvent e){
				switch(state){
				case START:
					state = RUNNING;
					break;
				case GAMEOVER:
					hero = new Hero();
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					score = 0;
					state = START;
					break;
				}
			}
			//鼠标移出
			public void mouseExited(MouseEvent e){
				if(state != GAMEOVER){
					state = PAUSE;
				}
			}
			//鼠标移入
			public void mouseEntered(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}
			}
		};
			addMouseListener(ma);
			addMouseMotionListener(ma);
		//创建计划任务，将计划任务添加到定时器
		timer = new Timer();
		timer.schedule(new TimerTask(){//游戏进程
			public void run(){
				if(state==RUNNING){
					enterAction();
					stepAction();
					shootAction();
					bangAction();
					outOfBoundsAction();
					checkGameOverAction();
				}
				repaint();
			}
		},interval,interval);
	}
	//检查游戏结束事件
	public void checkGameOverAction(){
		if(isGameOver()){
			state = GAMEOVER;
		}
	}
	//判断游戏结束
	public boolean isGameOver(){
		for(int i=0;i<flyings.length;i++){
			int index = -1;
			if(hero.hit(flyings[i])){
				hero.subtractLife();
				hero.setDoubleFire(0);
				index = i;
			}
			if(index!=-1){
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
		}
		return hero.getLife()<=0;
	}
	//判断飞行物出界事件
	public void outOfBoundsAction(){
		int index = 0;
		FlyingObject[] flyingLives = 
			new FlyingObject[flyings.length];
		for(int i=0;i<flyings.length;i++){
			if(flyings[i].outOfBounds()==false){
				flyingLives[index++] = flyings[i];
			}
		}
		flyings = Arrays.copyOf(flyingLives,index);
		index = 0;
		Bullet[] bulletsLives = 
			new Bullet[bullets.length];
		for(int i=0;i<bullets.length;i++){
			if(bullets[i].outOfBounds()==false){
				bulletsLives[index++] = bullets[i];
			}
		}
		bullets = Arrays.copyOf(bulletsLives,index);
	}
	//增加碰撞事件
	public void bangAction(){
		for(int i=0;i<bullets.length;i++){
			Bullet b = bullets[i];
			bang(b);
		}
	}
	//判断子弹是否打中飞行物
	public void bang(Bullet b){
		int index = -1;
		for(int i=0;i<flyings.length;i++){
			if(flyings[i].shootBy(b)){
				index = i;
				break;
			}
		}
		if(index!=-1){
			FlyingObject one = flyings[index];
			FlyingObject f = flyings[index];
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length-1] = f;
			flyings = Arrays.copyOf(flyings,flyings.length-1);
			
			if(one instanceof Enemy){
				Enemy e = (Enemy)one;
				score+=e.getScore();
			}else if(one instanceof Award){
				Award a = (Award)one;
				int type = a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				default:
					break;
				}
			}
		}
	}
	//发射事件
	public void shootAction(){
		shootIndex++;//子弹数
		if(shootIndex%30==0){
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
		}
	}
	//飞行物和子弹运动事件
	public void stepAction(){
		//飞行物运动
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();
		}
		//子弹运动
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
		//英雄机运动
		hero.step();
	}
	//飞行物进入事件
	public void enterAction(){
		flyEnteredIndex++;
		if(flyEnteredIndex%40==0){
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1] = obj;
		}
	}
	//区分敌机和小蜜蜂出现概率，补飞行物
	public static FlyingObject nextOne(){
		Random rd = new Random();
		int type = rd.nextInt(20);
		if(type==0){
			return new Bee();
		}else{
			return new Airplane(); 
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("飞机大战");
		ShootGame panel = new ShootGame();
		panel.setBackground(Color.BLACK);
		frame.add(panel);
		frame.setSize(WIDTH,HEIGHT);
		frame.setAlwaysOnTop(true);//最上层显示
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//设置居中显示
		frame.setVisible(true);
		panel.action();//主要的动作逻辑
	}
}
