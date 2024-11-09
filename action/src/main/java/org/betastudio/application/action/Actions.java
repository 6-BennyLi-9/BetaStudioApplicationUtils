package org.betastudio.application.action;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum Actions {
	;

	/**
	 * 标志 {@code Action} 块的最后一个节点
	 */
	public static class FinalNodeAction implements Action{
		@Override
		public boolean run() {return false;}
	}

	public static void runAction(final Action actionBlock){
		Action recent=actionBlock;
		while(true){
			if(!recent.run()) {
				if (recent.next() instanceof FinalNodeAction) {
					break;
				}
				recent = recent.next();
			}
		}
	}

	/**
	 * 在规定时间内，如果该 {@code Action} 块仍没有结束，将会强制停止
	 */
	public static void runTimedAllottedAction(final Action actionBlock, final long allottedMilliseconds){
		Action recent=actionBlock;
		double start=System.nanoTime()/1e6;
		while (!(System.nanoTime()/1e6-start >= allottedMilliseconds)) {
			if (!recent.run()) {
				if (recent.next() instanceof FinalNodeAction) {
					break;
				}
				recent = recent.next();
			}
		}
	}

	@NotNull
	@Contract(value = "_, _ -> new", pure = true)
	public static Action connectBlocking(final Action previous, final Action next){
		return new Action() {
			@Override
			public boolean run() {
				return previous.run();
			}

			@Override
			public Action next() {
				return next;
			}
		};
	}

	@NotNull
	@Contract(value = "_ -> new", pure = true)
	public static PriorityAction asPriority(final Action action){
		return asPriority(action,0);
	}
	@Contract(value = "_, _ -> new", pure = true)
	@NotNull
	public static PriorityAction asPriority(final Action action, long priorityGrade){
		return new PriorityAction() {
			@Override
			public long getPriorityCode() {
				return priorityGrade;
			}

			@Override
			public boolean run() {
				return action.run();
			}

			@Override
			public Action next() {
				return action.next();
			}
		};
	}
}
