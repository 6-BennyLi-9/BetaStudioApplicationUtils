package org.betastudio.application.action.utils;

import org.betastudio.application.action.Action;
import org.jetbrains.annotations.NotNull;

public final class RepetitionAction implements Action {
	private final long times;
	private long ptr;
	private Action argument;

	public RepetitionAction(final Action repeatArgument, final long times){
		this.times=times;
		argument=repeatArgument;
	}

	@Override
	public boolean run() {
		boolean res=argument.run();
		return res && ptr++<times;
	}

	@Override
	@NotNull
	public String paramsString() {
		return "/"+times+"/"+argument.paramsString();
	}
}
