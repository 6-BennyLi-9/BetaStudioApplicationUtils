package org.betastudio.application.action.utils;

import org.betastudio.application.action.Action;
import org.betastudio.application.action.PriorityAction;
import org.betastudio.application.action.Timer;

import java.util.*;

/**
 * 根据 {@code PriorityAction} 的优先级排序后进行执行操作，如果超时后将强制退出执行链
 */
public class TimedAllottedPriorityAction implements Action {
	public final List<PriorityAction> actions;
	private final Timer timer=new Timer();
	private final long allottedMilliseconds;

	public TimedAllottedPriorityAction(final long allottedMilliseconds,final List<PriorityAction> actions){
		this.actions=new ArrayList<>();
		this.actions.addAll(actions);
		this.actions.sort(Comparator.comparingLong(x -> -x.getPriorityCode()));
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
