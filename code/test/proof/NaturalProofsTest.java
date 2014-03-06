package test.proof;

import junit.framework.TestCase;
import proof.*;
import utils.Formula;

public class NaturalProofsTest extends TestCase {

	public void testCubicproof2() {
		try {
			Formula f = NaturalProofs.cubicproof2(new Formula("a"), new Formula("b"));
			assertEquals(" (  (  ( a + b ) *  ( a - b ) ) =  (  ( a )² +  (  ( -1 *  ( b )² ) ) ) )", f.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown "+e.getMessage());
		}
	}

	public void testCubicproof() {
		try {
			Formula f = NaturalProofs.cubicproof(new Formula("a"), new Formula("b"));
			assertEquals(" (  (  ( a + b ) )² =  (  ( a )² +  (  ( 2 *  ( a * b ) ) +  ( b )² ) ) )", f.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown "+e.getMessage());
		}
	
	}
	public void testsumOfTwoEvenNumbersIsEven() throws Exception
	{
		Formula f = NaturalProofs.sumOfTwoEvenNumbersIsEven(new Formula("a"), new Formula("b"));
		assertEquals(" (  (  ( 2 * a ) +  ( 2 * b ) ) =  ( 2 *  ( a + b ) ) )", f.toString());
	
	}
}
