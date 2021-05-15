package com.fly;

import java.util.Random;

//小蜜蜂
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
		awardType = rd.nextInt(2);//[0,1]随机一个奖励类型
	}
	//判断出界(高度上的出界)
	public boolean outOfBounds(){
		return this.y>ShootGame.HEIGHT;
	}
	//蜜蜂走的方式
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
	//获取奖励类型
	public int getType(){
		return awardType;
	}
}
