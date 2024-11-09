package org.betastudio.utils.mathematics;

import org.betastudio.utils.geometric.Position2d;
import org.betastudio.utils.geometric.Rectangles2d;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum Geometrics {
	;
	@Contract(pure = true)
	public static double distance(@NotNull Position2d pose1, @NotNull Position2d pose2){
		return Math.sqrt((pose1.x-pose2.x)*(pose1.x-pose2.x)+(pose1.y-pose2.y)*(pose1.y-pose2.y));
	}
	@Contract(pure = true)
	public static boolean containsPoint(@NotNull Rectangles2d entity, @NotNull Position2d pose){
		return entity.leftTop.x<= pose.x&&entity.rightDown.x>= pose.x
				&& entity.leftTop.y>=pose.y&&entity.rightDown.y<=pose.y;
	}
	public static boolean faceTouchedFace(@NotNull Rectangles2d face1, @NotNull Rectangles2d face2){
		return face1.containsPosition(face2.leftTop)||face1.containsPosition(face2.rightDown);
	}
}
