package com.fly;

import java.util.Random;

//С�۷�
public class Bee extends FlyingObject implements Award{
	private int xspeed = 1;
	private int yspeed = 2;
	private int awardType;
	public Bee(){
		image = ShootGame.bee;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		Random rd = new Random();
		x = rd.nextInt(ShootGame.WIDTH-width);
		awardType = rd.nextInt(2);//[0,1]���һ����������
	}
	//�жϳ���(�߶��ϵĳ���)
	public boolean outOfBounds(){
		return this.y>ShootGame.HEIGHT;
	}
	//�۷��ߵķ�ʽ
	public void step(){
		x+=xspeed;
		y+=yspeed;
		if(x>ShootGame.WIDTH-width){
			xspeed = -1;
		}
		if(x<0){
			xspeed = 1;
		}
	}
	//��ȡ��������
	public int getType(){
		return awardType;
	}
}
