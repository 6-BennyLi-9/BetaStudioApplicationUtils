package org.betastudio.application.action.utils;

import org.betastudio.application.action.Action;
import org.jetbrains.annotations.NotNull;

public final class ThreadSleepAction implements Action {
	private final long sleepMilliseconds;

	public ThreadSleepAction(final long sleepMilliseconds) {
		this.sleepMilliseconds = sleepMilliseconds;
	}

	@Override
	public boolean run() {
		try {
			Thread.sleep(sleepMilliseconds);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	@NotNull
	@Override
	public String paramsString() {
		return "t:" + sleepMilliseconds + "ms";
	}
}
