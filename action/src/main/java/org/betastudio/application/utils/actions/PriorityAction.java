package org.betastudio.application.utils.actions;

public interface PriorityAction extends Action{
	/**
	 * @return 优先级，数值越大优先级越高
	 */
	long getPriorityCode();
}