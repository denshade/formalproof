package proof;

import org.junit.jupiter.api.Test;
import utils.Formula;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NaturalProofsTest {

	@Test
	public void testCubicproof2() {
		try {
			Formula f = NaturalProofs.cubicproof2(new Formula("a"), new Formula("b"));
			assertEquals(" (  (  ( a + b ) *  ( a - b ) ) =  (  ( a )� +  (  ( -1 *  ( b )� ) ) ) )", f.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown "+e.getMessage());
		}
	}

	@Test
	public void testCubicproof() {
		try {
			Formula f = NaturalProofs.cubicproof(new Formula("a"), new Formula("b"));
			assertEquals(" (  (  ( a + b ) )� =  (  ( a )� +  (  ( 2 *  ( a * b ) ) +  ( b )� ) ) )", f.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown "+e.getMessage());
		}
	
	}

	@Test
	public void testsumOfTwoEvenNumbersIsEven() throws Exception
	{
		Formula f = NaturalProofs.sumOfTwoEvenNumbersIsEven(new Formula("a"), new Formula("b"));
		assertEquals(" (  (  ( 2 * a ) +  ( 2 * b ) ) =  ( 2 *  ( a + b ) ) )", f.toString());
	
	}
}
