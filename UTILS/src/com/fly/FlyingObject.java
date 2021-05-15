package com.fly;

import java.awt.image.BufferedImage;

//飞行物的父类
public abstract class FlyingObject{
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected BufferedImage image;//定义图片的属性
	//飞行物移动步数的方法
	public abstract void step();
	//判断敌机被子弹击中
	public boolean shootBy(Bullet b){
		int x = b.x - this.x;
		int y = b.y - this.y;
		return x>0&&x<width&&y>0&&y<height;
	}
	//判断出界的方法
	public abstract boolean outOfBounds();
}
