package org.betastudio.application.action.utils;


import org.betastudio.application.action.Action;

public final class SleepingAction implements Action {
	private final long sleepMilliseconds;
	private double startTime;
	private boolean initialized=false;

	public SleepingAction(final long sleepMilliseconds){
		this.sleepMilliseconds = sleepMilliseconds;
	}

	@Override
	public boolean run() {
		if(!initialized){
			startTime=System.nanoTime()/1e6;
			initialized=true;
		}
		return System.nanoTime()/1e6-startTime < sleepMilliseconds;
	}
}
