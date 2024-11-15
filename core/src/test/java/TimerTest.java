import org.betastudio.application.core.Timer;

public class TimerTest {
	public static Timer timer;

	public static void main(String[] args) {
		init();

		do {
			loop();
		} while (Timer.getCurrentTime() - timer.getTimeTag("start") < 1000);
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
