package com.fly;
//�ӵ�
public class Bullet extends FlyingObject{
	private int speed = 3;//�ٶ�
	public Bullet(int x,int y){
		//����ӵ�ͼƬ
		image = ShootGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}
	//�ӵ��ƶ�����
	public void step(){
		y-=speed;//y=y-speed
	}
	public boolean outOfBounds(){
		return y<-height;
	}
}
