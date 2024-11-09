package org.betastudio.utils.geometric;

import org.betastudio.utils.mathematics.Geometrics;

public class Position2d {
	public long x,y;

	public Position2d(){}
	public Position2d(long x,long y){
		this.x=x;
		this.y=y;
	}

	public double distanceTo(Position2d heading){
		return Geometrics.distance(this,heading);
	}
	public boolean touchedFace(Rectangles2d entity){
		return Geometrics.containsPoint(entity,this);
	}
}
