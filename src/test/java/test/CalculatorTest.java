package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	Calculator cal;

	@Before
	public void setup() {
		System.out.println("setup");
		cal = new Calculator();
	}
	
	@Test
	public void add() {
		System.out.println("add");
		int result = cal.add(1, 1);
		
		assertThat(result, is(2));
		//assertEquals(2, result);
	}
	
	@Test
	public void minus() throws Exception {
		System.out.println("minus");
		int result = cal.minus(4, 3);
		
		assertEquals(1, result);
	}
	
	@After
	public void teardown() {
		System.out.println("teardown");
	}

}
