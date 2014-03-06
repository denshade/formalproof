package proof;

import axioms.*;
import utils.*;

public class NaturalProofs 
{
	
	public static void main(String[] args) throws Exception
	{
		//System.out.println(NaturalProofs.cubicproof(new Formula("a"), new Formula("b")));
		System.out.println(NaturalProofs.cubicproof2(new Formula("a"), new Formula("b")));
	}
	
	/**
	 * Prove that there is an infinite amount of primes.
	 * 
	 * ForEach(a in primes : (ThereExists(b in prime):b > a))
	 * needs proof that product of all primes + 1 is also a prime. 
	 * 
	 */
	public static Formula infinitePrimes()
	{
		/**
		 * Foreach (a in primes : CartesianProduct(b in (primes<=a)) + 1 is prime)
		 */
		
		/**
		 * Foreach (a in primes : (CartesianProduct(b in (primes<=a)) + 1 > a ^ CartesianProduct(b in (primes<=a)) + 1 is prime))
		 */
		/**
		 * End with Foreach (a in primes : (ThereExists(b in primes): b > a))
		 */
		return null;
	}
	/**
	 * 
	 * @return
	 */
	public static Formula CartesianProductPrimesPlusOneIsPrime()
	{
		/**
		 * Foreach (a in primes : Foreach ( c in (primes <= a) : ( CartesianProduct(b in (primes<=a)) + 1 % c != 0))
		 */
		/**
		 * Foreach (a in primes : CartesianProduct(b in (primes<=a)) + 1 is prime)
		 */
		return null;
	}
	
	//2*a + 2*b = 2(a+b) or a + a + b + b = a + b + a + b
	public static Formula sumOfTwoEvenNumbersIsEven(Formula a, Formula b) throws Exception
	{
		AnyFormula apa = new AnyFormula(  a,"+", a);
		AnyFormula bpb = new AnyFormula( b,"+",b);
		AnyFormula sumAB = new AnyFormula( apa, "+",bpb);
		Formula equal = EqualityAxiom.reflect(sumAB);
		equal = NaturalAxioms.doubleSumProduct(equal, new int[] {0,0});
		equal = NaturalAxioms.doubleSumProduct(equal, new int[] {0,1});
		equal = NaturalAxioms.associativeSum(equal, new int[] {1});
		equal = NaturalAxioms.commutativitySum(equal, new int[] {1,1});
		equal = NaturalAxioms.associativeSum(equal, new int[] {1,1});
		equal = NaturalAxioms.associativeSumRight(equal, new int[] {1});
		equal = NaturalAxioms.commutativitySum(equal, new int[] {1,1});
		equal = NaturalAxioms.doubleSumProduct(equal, new int[] {1});
		
		return equal;
	}
	
	// (a - b)(a + b) = a² - b² 
	public static Formula cubicproof2(Formula a, Formula b) throws Exception
	{
		//(a - b)(a + b) = (a - b)(a + b)
		AnyFormula combine = new AnyFormula(a,"+",b);
		
		AnyFormula combine2 = new AnyFormula(a,"-",b);
		AnyFormula combineProduct = new AnyFormula(combine,"*", combine2); 
		Formula f = EqualityAxiom.reflect(combineProduct);
		int[] rightside = {1}; 
		f = NaturalAxioms.distributeSumProduct(f, rightside);
		// (  (  ( a + b ) *  ( a - b ) ) =  (  ( a *  ( a - b ) ) +  ( b *  ( a - b ) ) ) )
		int[] rightsidel0r1 = {1,0}; 
		
		f= NaturalAxioms.commutativityProduct(f, rightsidel0r1 );
		NaturalAxioms.distributeSubtractProduct(f, rightsidel0r1);
		int[] rightsidel1r1 = {1,1}; 
		f= NaturalAxioms.commutativityProduct(f, rightsidel1r1 );
		NaturalAxioms.distributeSubtractProduct(f, rightsidel1r1);
		NaturalAxioms.replaceSubtractionBySum(f, rightsidel1r1);
		NaturalAxioms.replaceSubtractionBySum(f, rightsidel0r1);
		int[] rightsider1l1r1 = {1,0,1,1}; 
		NaturalAxioms.commutativityProduct(f, rightsider1l1r1);
		NaturalAxioms.associativeSum(f, rightside);
		//(  ( a * a ) +  (  ( -1 *  ( a * b ) ) +  (  ( a * b ) +  ( -1 *  ( b * b ) ) ) ) ) )
		int[] r10 = {1,0}; 
		int[] r11 = {1,1}; 
		int[] r111 = {1,1,1};
		int[] r1101 = {1,1,0,1};
		NaturalAxioms.commutativitySum(f, r111);
		NaturalAxioms.commutativitySum(f, r11);
		
		NaturalAxioms.associativeSum(f, r11);
		NaturalAxioms.sumOfInverseIsZero(f, r111);
		NaturalAxioms.removeZero(f,  r11);
		NaturalAxioms.productToPower2(f, r1101);
		NaturalAxioms.productToPower2(f, r10);
		return f;
	}
	
	//(a + b)*(a + b) = a*a + 2 * a * b + b * b 
	public static Formula cubicproof(Formula a, Formula b) throws Exception
	{
		AnyFormula combine = new AnyFormula("+");
		combine.whatFix = Formula.OperatorFix.INFIX;
		combine.isLeaf = false;
		combine.formulas.add(a);
		combine.formulas.add(b);
		AnyFormula combineProduct = new AnyFormula("*"); 
		combineProduct.whatFix = Formula.OperatorFix.INFIX;
		combineProduct.isLeaf = false;
		combineProduct.formulas.add(combine);
		combineProduct.formulas.add(combine);
		
		Formula f = EqualityAxiom.reflect(combineProduct);

		int[] leftpart = {0};
		f = NaturalAxioms.productToPower2(f, leftpart);
		//	=
		int[] rightpart = {1};
		f = NaturalAxioms.distributeSumProduct(f,rightpart);// (  (  ( a + b ) *  ( a + b ) ) =  (  ( a *  ( a + b ) ) +  ( b *  ( a + b ) ) ) )
		int[] rightpart2 = {1,0};
		f = NaturalAxioms.commutativityProduct(f, rightpart2);
		f = NaturalAxioms.distributeSumProduct(f,rightpart2);
		int[] rightpart3 = {1,1};
		f = NaturalAxioms.commutativityProduct(f, rightpart3);
		f = NaturalAxioms.distributeSumProduct(f,rightpart3);//(  (  ( a * a ) +  ( b * a ) ) +  (  ( a * b ) +  ( b * b ) ) ) )
		
		int[] rightpart4 = {1,0, 1};
		f = NaturalAxioms.commutativityProduct(f, rightpart4);
		int[] rightpart5 = {1};
		f = NaturalAxioms.associativeSum(f,rightpart5);//(  ( a * a ) +  (  ( a * b ) +  (  ( a * b ) +  ( b * b ) ) ) ) )
		int[] rightpart6 = {1, 1};
		f = NaturalAxioms.commutativitySum(f,rightpart6);//(  ( a * a ) +  (  (  ( a * b ) +  ( b * b ) ) +  ( a * b ) ) ) )
		int[] rightpart7 = {1, 1, 0};
		
		f = NaturalAxioms.commutativitySum(f,rightpart7);//(  ( a * a ) +  (  (  ( b * b ) +  ( a * b ) ) +  ( a * b ) ) ) )
		
		f = NaturalAxioms.associativeSum(f,rightpart6); //(  ( a * a ) +  (  ( b * b ) +  (  ( a * b ) +  ( a * b ) ) ) ) )
		int[] rightpart8 = {1, 1, 1};
		f = NaturalAxioms.doubleSumProduct(f,rightpart8); 
		f = NaturalAxioms.commutativitySum(f, rightpart6);
		int[] rightpart9 = {1, 0};
		
		f = NaturalAxioms.productToPower2(f, rightpart9);
		f = NaturalAxioms.productToPower2(f, rightpart8);
		
		return f;
	}
	
	
}
