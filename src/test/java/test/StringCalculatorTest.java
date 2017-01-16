package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculatorTest {

	@Test
	public void add() {
		StringCalculator cal = new StringCalculator();
		
		assertEquals(10, cal.add("1,2,3,4"));
	}
	
	@Test
	public void add_null() {
		StringCalculator cal = new StringCalculator();
		
		assertEquals(0, cal.add(null));
	}
	
	@Test(expected=RuntimeException.class)
	public void add_minus() {
		StringCalculator cal = new StringCalculator();
		
		assertEquals(2, cal.add("1,2,-1"));
	}
	
	@Test
	public void getDelimiter() {
		StringCalculator cal = new StringCalculator();
		
		assertEquals(";", cal.getDelimiter("//;\n1;2;3"));
	}
	
	@Test
	public void split_with_delimiter() {
		StringCalculator cal = new StringCalculator();
		
		String[] expected = {"1", "2", "3"};
		String[] actual = cal.split("//;\n1;2;3");
		
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

}
