package ParserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ TestInfix.class })
public class Test {
	
	public static void main (String[] args) {
		
		TestInfix testInfix = new TestInfix (false);
		
	}

}
