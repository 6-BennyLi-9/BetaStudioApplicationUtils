import org.betastudio.application.action.Action;
import org.betastudio.application.action.packages.TaggedActionPackage;

public class MainTestingArea {
	public static class PrintAction implements Action {
		private final long printVal;

		public PrintAction(){
			printVal=0;
		}
		public PrintAction(final long printVal){
			this.printVal=printVal;
		}

		@Override
		public boolean run() {
			System.out.println(printVal);
			return false;
		}
	}

	public static void main(final String[] args) {
		final TaggedActionPackage thread=new TaggedActionPackage();
		thread.add("print1",new PrintAction(10));
		thread.add("print2",new PrintAction(20));

		System.out.println(thread.run());
	}
}
