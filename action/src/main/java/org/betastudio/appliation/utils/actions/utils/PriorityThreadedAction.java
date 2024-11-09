package org.betastudio.appliation.utils.actions.utils;


import org.betastudio.appliation.utils.actions.Action;
import org.betastudio.appliation.utils.actions.PriorityAction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 根据 {@code PriorityAction} 的优先级排序后进行执行操作
 */
public class PriorityThreadedAction implements Action {
	public final List<PriorityAction> actions;

	public PriorityThreadedAction(final List<PriorityAction> actions){
		this.actions=actions;
		actions.sort(Comparator.comparingLong(x -> -x.getPriorityCode()));
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
