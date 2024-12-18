package org.betastudio.application.core;

/**
 * {@link Math} 的扩展版
 */
@SuppressWarnings("SpellCheckingInspection")
public enum MathAddition {
	;

	/**
	 * @param a 角度制变量，{@code a' ∈ [-180,180]}
	 * @return a 的 sine 值
	 */
	public static double sind(final double a){
		return Math.sin(Math.toRadians(a));
	}
	/**
	 * @param a 角度制变量，{@code a' ∈ [-180,180]}
	 * @return a 的 cosine 值
	 */
	public static double cosd(final double a){
		return Math.cos(Math.toRadians(a));
	}
	/**
	 * @param a 角度制变量，{@code a' ∈ [-180,180]}
	 * @return a 的 tangent 值
	 */
	public static double tand(final double a){
		return Math.tan(Math.toRadians(a));
	}

	/**
	 * @param a 目标值
	 * @param min 目标最小值
	 * @param max 目标最大值
	 * @return 将目标强制分至 {@code [min,max]} 的集合内
	 */
	public static double intervalClip(final double a, final double min, final double max){
		return Math.min(Math.max(a,min),max);
	}
	/**
	 * @param a 目标值
	 * @param min 目标最小值
	 * @param max 目标最大值
	 * @return 将目标滚动分至 {@code [min,max]} 的集合内
	 */
	public static double roundClip(double a, final double min, final double max){
		while (a<min) a+=(max-min);
		while (a>max) a-=(max-min);
		return a;
	}
}
