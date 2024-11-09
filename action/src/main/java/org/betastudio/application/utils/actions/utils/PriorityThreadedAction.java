package org.betastudio.application.utils.actions.utils;


import org.betastudio.application.utils.actions.Action;
import org.betastudio.application.utils.actions.PriorityAction;

import java.util.*;

/**
 * 根据 {@code PriorityAction} 的优先级排序后进行执行操作
 */
public class PriorityThreadedAction implements Action {
	public final List<PriorityAction> actions;

	public PriorityThreadedAction(final List<PriorityAction> actions){
		this.actions=new ArrayList<>();
		this.actions.addAll(actions);
		this.actions.sort(Comparator.comparingLong(x -> -x.getPriorityCode()));
	}
	public PriorityThreadedAction(final PriorityAction... actions){
		this(Arrays.asList(actions));
	}

	@Override
	public boolean run() {
		final Set<PriorityAction> removes=new HashSet<>();
		for(final PriorityAction action:actions){
			if(!action.run()){
				removes.add(action);
			}
		}
		actions.removeAll(removes);
		return !actions.isEmpty();
	}
}