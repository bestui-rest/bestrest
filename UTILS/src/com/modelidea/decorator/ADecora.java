package com.modelidea.decorator;
/*
 * װ��������
 */
public abstract class ADecora implements IShape {
private IShape  iShape;
public ADecora(IShape iShape){
	this.iShape=iShape ;	
}

public void drow()
{
  this.iShape.drow();
}

}
