package org.betastudio.application.action.utils;


import org.betastudio.application.action.Action;
import org.betastudio.application.action.Timer;

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
