import org.betastudio.application.utils.actions.Actions;
import org.betastudio.application.utils.actions.utils.*;

public class ActionTest {
	public static void main(String[] args) {
		Actions.runAction(new ThreadedAction(
				new LinkedAction(
					new StatementAction(ActionTest::printCurrentTime),
					new SleepingAction(500),
					new StatementAction(ActionTest::printCurrentTime)
				),
				new TimedAllottedPriorityAction(
						600,
						Actions.asPriority(new StatementAction(()->print("tag1")),0),
						Actions.asPriority(new StatementAction(()->print("tag")),6),
						Actions.asPriority(new StatementAction(()->print("tag2")),4)
				)
		));
	}

	public static void printCurrentTime(){
		System.out.println(System.currentTimeMillis());
	}
	public static void print(Object o){
		System.out.println(o.toString());
	}
}
