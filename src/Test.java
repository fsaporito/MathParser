
public class Test {

	
	public static void main(String[] args) throws WrongInputException {
		
		
		System.out.println ("*** Test 1");
		String test1 = "2 + 1";
		System.out.println (test1);		
		MathParser parser1 = new MathParser (test1, "infix");
		
		System.out.println ("\n\n\n");
		
		System.out.println ("*** Test 2");
		String test2 = "2 - 1 + 2 - 3";
		System.out.println (test2);	
		MathParser parser2 = new MathParser (test2, "infix");
		
		System.out.println ("\n\n\n");
		
		System.out.println ("*** Test 3");
		String test3 = "3 - 4 * 2";
		System.out.println (test3);	
		MathParser parser3 = new MathParser (test3, "infix");
		
		
		System.out.println ("\n\n\n");
		
		System.out.println ("*** Test 4");
		String test4 = "3 + 4 + 5 + 6 + 7 + 8 + 9 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 * 2";
		System.out.println (test4);	
		MathParser parser4 = new MathParser (test4, "infix");
		
		
	

	}

}
