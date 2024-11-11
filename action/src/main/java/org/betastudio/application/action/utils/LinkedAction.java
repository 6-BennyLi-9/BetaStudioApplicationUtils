package org.betastudio.application.action.utils;


import org.betastudio.application.action.Action;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class LinkedAction implements Action{
	private final List<Action> actions;
	private int ptr=0;

	public LinkedAction(final List<Action> actions){
		this.actions=actions;
	}
	public LinkedAction(final Action... actions){
		this(Arrays.asList(actions));
	}

	@Override
	public boolean run() {
		if(actions.get(ptr).run()){
			return true;
		}else{
			ptr++;
			return ptr<actions.size();
		}
	}

	@NotNull
	@Override
	public String paramsString() {
		final StringBuilder stringBuilder=new StringBuilder("{");
		for(Action action:actions){
			stringBuilder.append(action.paramsString()).append(",");
		}
		return stringBuilder.append("}").toString();
	}
}
