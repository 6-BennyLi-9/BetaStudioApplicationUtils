import org.betastudio.application.core.Timer;

public enum TimerTest {
	;
	public static Timer timer;

	public static void main(final String[] args) {
		init();

		do {
			loop();
		} while (1000 > Timer.getCurrentTime() - timer.getTimeTag("start"));
	}

	public static void init(){
		timer=new Timer();
		timer.pushMileageTimeTag("start");
	}

	public static void loop(){
		System.out.println("now TPS:"+1000/timer.restartAndGetDeltaTime());
		for(int i = 0; i< Math.random(); ++i){
			System.out.flush();
		}
	}
}
