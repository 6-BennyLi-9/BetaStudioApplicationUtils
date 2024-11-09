package org.betastudio.appliation.utils.actions.utils;


import org.betastudio.appliation.utils.actions.Action;
import org.betastudio.appliation.utils.actions.Timer;

public final class SleepingAction implements Action {
	private final Timer timer=new Timer();
	private final long sleepMilliseconds;

	public SleepingAction(final long sleepMilliseconds){
		this.sleepMilliseconds = sleepMilliseconds;
		timer.restart();
	}

	@Override
	public boolean run() {
		return timer.stopAndGetDeltaTime()< sleepMilliseconds;
	}
}
