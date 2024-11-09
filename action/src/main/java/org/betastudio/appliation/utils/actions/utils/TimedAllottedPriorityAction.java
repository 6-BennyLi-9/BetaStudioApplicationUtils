package org.betastudio.appliation.utils.actions.utils;

import org.betastudio.appliation.utils.actions.Action;
import org.betastudio.appliation.utils.actions.PriorityAction;
import org.betastudio.appliation.utils.actions.Timer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 根据 {@code PriorityAction} 的优先级排序后进行执行操作，如果超时后将强制退出执行链
 */
public class TimedAllottedPriorityAction implements Action {
	public final List<PriorityAction> actions;
	private final Timer timer=new Timer();
	private final long allottedMilliseconds;

	public TimedAllottedPriorityAction(final long allottedMilliseconds,final List<PriorityAction> actions){
		this.actions=actions;
		actions.sort(Comparator.comparingLong(x -> -x.getPriorityCode()));
		this.allottedMilliseconds=allottedMilliseconds;
	}
	public TimedAllottedPriorityAction(final long allottedMilliseconds,final PriorityAction... actions){
		this(allottedMilliseconds,Arrays.asList(actions));
	}

	@Override
	public boolean run() {
		timer.restart();
		final Set<PriorityAction> removes=new HashSet<>();

		for(final PriorityAction action:actions){
			if(!action.run()){
				removes.add(action);
			}
			if(timer.stopAndGetDeltaTime()>=allottedMilliseconds){
				break;
			}
		}

		actions.removeAll(removes);
		return !actions.isEmpty();
	}
}
