package com.fly;
//子弹
public class Bullet extends FlyingObject{
	private int speed = 3;//速度
	public Bullet(int x,int y){
		//添加子弹图片
		image = ShootGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}
	//子弹移动方法
	public void step(){
		y-=speed;//y=y-speed
	}
	public boolean outOfBounds(){
		return y<-height;
	}
}
