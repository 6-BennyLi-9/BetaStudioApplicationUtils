package org.betastudio.utils.geometric;

import org.betastudio.utils.mathematics.Geometrics;

public class Rectangles2d {
	public Position2d leftTop,rightDown;

	{
		leftTop=new Position2d();
		rightDown=new Position2d();
	}

	public Rectangles2d(){}
	public Rectangles2d(long leftTopX,long leftTopY,long rightDownX,long rightDownY){
		leftTop=new Position2d(leftTopX,leftTopY);
		rightDown=new Position2d(rightDownX,rightDownY);
	}

	public boolean containsPosition(Position2d pose){
		return Geometrics.containsPoint(this,pose);
	}
}
