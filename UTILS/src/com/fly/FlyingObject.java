package com.fly;

import java.awt.image.BufferedImage;

//������ĸ���
public abstract class FlyingObject{
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected BufferedImage image;//����ͼƬ������
	//�������ƶ������ķ���
	public abstract void step();
	//�жϵл����ӵ�����
	public boolean shootBy(Bullet b){
		int x = b.x - this.x;
		int y = b.y - this.y;
		return x>0&&x<width&&y>0&&y<height;
	}
	//�жϳ���ķ���
	public abstract boolean outOfBounds();
}
