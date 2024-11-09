package org.betastudio.appliation.utils.actions.utils;


import org.betastudio.appliation.utils.actions.Action;
import org.betastudio.appliation.utils.actions.Actions;

import java.util.Arrays;
import java.util.List;

public final class LinkedAction implements Action{
	private final List<Action> actions;

	public LinkedAction(final List<Action> actions){
		this.actions=actions;
	}
	public LinkedAction(final Action... actions){
		this(Arrays.asList(actions));
	}

	@Override
	public boolean run() {
		Actions.runAction(actions.getFirst());
		actions.removeFirst();
		return !actions.isEmpty();
	}
}
