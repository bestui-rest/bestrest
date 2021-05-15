package com.fly;

import java.util.Random;

//敌机
public class Airplane extends FlyingObject implements Enemy{
	private int speed = 2;
	public Airplane(){
		//获取敌机图片
		image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		Random rd = new Random();
		x = rd.nextInt(ShootGame.WIDTH-width);
	}
	//判断出界
	public boolean outOfBounds(){
		return this.y>ShootGame.HEIGHT;
	}
	//敌机向下走的步数
	public void step(){
		y+=speed;
	}
	//打掉一个飞机给5分
	public int getScore(){
		return 5;
	}
}
