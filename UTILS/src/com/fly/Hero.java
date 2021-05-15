package com.fly;

import java.awt.image.BufferedImage;

//Ӣ�ۻ�
public class Hero extends FlyingObject{
	private int doubleFire;
	private int life;
	private BufferedImage[] images = {};
	private int index;
	public Hero(){
		image = ShootGame.hero0;
		width = image.getWidth();
		height = image.getHeight();
		x = 150;
		y = 450;
		doubleFire = 0;
		life = 3;
		images = new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
	}
	//����������ͼƬ�ڱ任
	public void step(){
		int num = index++/10%images.length;//0,1
		image = images[num];
	}
	//�жϳ���
	public boolean outOfBounds(){
		return false;
	}
	//�����ӵ�(���ַ��䷽ʽ)
	public Bullet[] shoot(){
		int xstep = this.width/4;
		int ystep = 20;
		if(doubleFire>0){
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(this.x+1*xstep,this.y-ystep);
			bullets[1] = new Bullet(this.x+3*xstep,this.y-ystep);
			return bullets;
		}else{
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x+2*xstep,this.y-ystep);
			return bullets;
		}
	}
	//���˫�������ķ���
	public void addDoubleFire(){
		doubleFire+=40;
	}
	//�ƶ��ɻ�
	public void moveTo(int x,int y){
		this.x = x - this.width/2;
		this.y = y - this.height/2;
	}
	//������
	public void addLife(){
		life++;
	}
	//��ȡ��
	public int getLife(){
		return life;
	}
	//�ж�Ӣ�ۻ��Ƿ�����˷�����ײ
	public boolean hit(FlyingObject other){
		int x1 = other.x - this.width/2;
		int x2 = other.x + other.width + this.width/2;
		int y1 = other.y - this.height/2;
		int y2 = other.y + other.height + this.height/2;
		int heroX = this.x + this.width/2;
		int heroY = this.y + this.height/2;
		return heroX>x1&&heroX<x2&&heroY>y1&&heroY<y2;
	}
	//����
	public void subtractLife(){
		life--;
	}
	//���û���
	public void setDoubleFire(int doubleFire){
		this.doubleFire = doubleFire;
	}
}
