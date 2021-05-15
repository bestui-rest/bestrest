package com.fly;

import java.util.Random;

//�л�
public class Airplane extends FlyingObject implements Enemy{
	private int speed = 2;
	public Airplane(){
		//��ȡ�л�ͼƬ
		image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		Random rd = new Random();
		x = rd.nextInt(ShootGame.WIDTH-width);
	}
	//�жϳ���
	public boolean outOfBounds(){
		return this.y>ShootGame.HEIGHT;
	}
	//�л������ߵĲ���
	public void step(){
		y+=speed;
	}
	//���һ���ɻ���5��
	public int getScore(){
		return 5;
	}
}
