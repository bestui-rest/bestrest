package com.modelidea.decorator;

/*
 * ����װ����
 */
public class RedDecorator extends ADecora {
public RedDecorator(IShape iShape){
	super(iShape);
}
//  װ��
@Override
	public void drow() {
		// TODO Auto-generated method stub
    	doother();
		super.drow();
	}

public void doother(){
	System.out.println("this.IShape.Color");
}


public static void main(String[] args) {
	ADecora ad=new RedDecorator(new Cir());
    ad.drow();
}

}
