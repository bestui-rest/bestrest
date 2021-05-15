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
//JPanel�д��ڷ����л�����д����־���
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
	int interval = 15;//���ʱ��
	int shootIndex = 0;
	int flyEnteredIndex = 10;
	private int score = 0;//����
	private int state;//��Ϸ״̬
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
	//����ͼƬ
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
	//��ͼƬ��ӽ����
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		paintHero(g);
		paintBullets(g);
		paintFlyingObject(g);
		paintScore(g);
		paintState(g);
	}
	//��״̬
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
	//����������
	public void paintScore(Graphics g){
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF,Font.BOLD,25);
		g.setFont(font);
		g.setColor(new Color(0xFFFFFF));//������ɫ
		g.drawString("SCORE:"+score,x,y);
		g.drawString("LIFE:"+hero.getLife(),x,y+30);
	}
	//��������
	public void paintFlyingObject(Graphics g){
		for(int i=0;i<flyings.length;i++){
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);
		}
	}
	//���ӵ�
	public void paintBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);
		}
	}
	//��Ӣ�ۻ�
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	
	public void action(){
		MouseAdapter ma = new MouseAdapter(){
			//����ƶ�
			public void mouseMoved(MouseEvent e){
				if(state==RUNNING){
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x,y);
				}
			}
			//�����
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
			//����Ƴ�
			public void mouseExited(MouseEvent e){
				if(state != GAMEOVER){
					state = PAUSE;
				}
			}
			//�������
			public void mouseEntered(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}
			}
		};
			addMouseListener(ma);
			addMouseMotionListener(ma);
		//�����ƻ����񣬽��ƻ�������ӵ���ʱ��
		timer = new Timer();
		timer.schedule(new TimerTask(){//��Ϸ����
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
	//�����Ϸ�����¼�
	public void checkGameOverAction(){
		if(isGameOver()){
			state = GAMEOVER;
		}
	}
	//�ж���Ϸ����
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
	//�жϷ���������¼�
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
	//������ײ�¼�
	public void bangAction(){
		for(int i=0;i<bullets.length;i++){
			Bullet b = bullets[i];
			bang(b);
		}
	}
	//�ж��ӵ��Ƿ���з�����
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
	//�����¼�
	public void shootAction(){
		shootIndex++;//�ӵ���
		if(shootIndex%30==0){
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
		}
	}
	//��������ӵ��˶��¼�
	public void stepAction(){
		//�������˶�
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();
		}
		//�ӵ��˶�
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
		//Ӣ�ۻ��˶�
		hero.step();
	}
	//����������¼�
	public void enterAction(){
		flyEnteredIndex++;
		if(flyEnteredIndex%40==0){
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1] = obj;
		}
	}
	//���ֵл���С�۷���ָ��ʣ���������
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
		JFrame frame = new JFrame("�ɻ���ս");
		ShootGame panel = new ShootGame();
		panel.setBackground(Color.BLACK);
		frame.add(panel);
		frame.setSize(WIDTH,HEIGHT);
		frame.setAlwaysOnTop(true);//���ϲ���ʾ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//���þ�����ʾ
		frame.setVisible(true);
		panel.action();//��Ҫ�Ķ����߼�
	}
}
