package org.betastudio.application.action.utils;


import org.betastudio.application.action.Action;

public class StatementAction implements Action {
	public interface StatementNode{
		void run();
	}
	private final StatementNode node;

	/**
	 * 语句式 {@code Action} 块
	 * <p>
	 * 示例： {@code ... new StatementAction( () -> yourMethodHere() ); ...}
	 */
	public StatementAction(final StatementNode meaning){
		node=meaning;
	}

	@Override
	public boolean run() {
		node.run();
		return false;
	}
}
