package axioms;

import commonFunctions.Product;
import commonFunctions.Sum;

import utils.Formula;

public class NaturalAxioms 
{
	//1 = 1
	//(a + b) * c = a * c + a * b
	//the index should point to the product.
	public static Formula distributeSumProduct(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("*")) 
		{
			throw new Exception("Bad formula, need a product *!");
		}
		Formula left = temp.formulas.get(0);
		Formula c = temp.formulas.get(1);
		if (!left.name.equals("+")) 
		{
			throw new Exception("Bad formula, need a product +!");
		}	
		Formula a = left.formulas.get(0);
		Formula b = left.formulas.get(1);
		
		temp.formulas.clear();//clean out the current location.
		temp.isLeaf = true;
		Formula leftcombo = new Formula("*"); //build a * c
		leftcombo.isLeaf = false;
		leftcombo.whatFix = Formula.OperatorFix.INFIX;
		leftcombo.formulas.add(a);
		leftcombo.formulas.add(c);
		
		Formula rightcombo = new Formula("*"); //build a * b
		rightcombo.isLeaf = false;
		rightcombo.whatFix = Formula.OperatorFix.INFIX;
		rightcombo.formulas.add(b);
		rightcombo.formulas.add(c);
		
		temp.name = "+";
		temp.whatFix = Formula.OperatorFix.INFIX;
		temp.formulas.add(leftcombo);
		temp.isLeaf = false;
		temp.formulas.add(rightcombo);
		return form;
	}
	//(a - b) * c = a * c - a * b
	public static Formula distributeSubtractProduct(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("*")) 
		{
			throw new Exception("Bad formula, need a product *!");
		}
		Formula left = temp.formulas.get(0);
		Formula c = temp.formulas.get(1);
		if (!left.name.equals("-")) 
		{
			throw new Exception("Bad formula, need a subtraction -!");
		}	
		Formula a = left.formulas.get(0);
		Formula b = left.formulas.get(1);
		
		temp.formulas.clear();//clean out the current location.
		temp.isLeaf = true;
		Formula leftcombo = new Formula("*"); //build a * c
		leftcombo.isLeaf = false;
		leftcombo.whatFix = Formula.OperatorFix.INFIX;
		leftcombo.formulas.add(a);
		leftcombo.formulas.add(c);
		
		Formula rightcombo = new Formula("*"); //build a * b
		rightcombo.isLeaf = false;
		rightcombo.whatFix = Formula.OperatorFix.INFIX;
		rightcombo.formulas.add(b);
		rightcombo.formulas.add(c);
		
		temp.name = "-";
		temp.whatFix = Formula.OperatorFix.INFIX;
		temp.formulas.add(leftcombo);
		temp.isLeaf = false;
		temp.formulas.add(rightcombo);
		return form;
	}
	
	
	///a * b => b * a
	public static Formula commutativityProduct(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("*"))
		{
			throw new Exception("Bad formula, need a product *!");
		}
		
		Formula a = temp.formulas.get(0);
		Formula b = temp.formulas.get(1);
		temp.formulas.clear();
		temp.formulas.add(b);
		temp.formulas.add(a);
		return form;
	}
	
	public static Formula productToPower2(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("*"))
		{
			throw new Exception("Bad formula, need a product *!");
		}
		if (!temp.formulas.get(0).equals(temp.formulas.get(1)))
		{
			throw new Exception("To combine a product make sure both formulas are equal.");
		}
		Formula cur = temp.formulas.get(0);
		temp.formulas.clear();
		temp.name = "²";
		temp.whatFix = Formula.OperatorFix.SUFFIX;
		temp.formulas.add(cur);
		return form;
	}
	
	public static Formula commutativitySum(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("+"))
		{
			throw new Exception("Bad formula, need a sum +!");
		}
		
		Formula a = temp.formulas.get(0);
		Formula b = temp.formulas.get(1);
		temp.formulas.clear();
		temp.formulas.add(b);
		temp.formulas.add(a);
		return form;
	}
	
	public static Formula doubleSumProduct(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);	
		if (temp.name != "+")
		{
			throw new Exception("Bad formula, need a sum +!"); 
		}
		if (!temp.formulas.get(0).toString().equals(temp.formulas.get(1).toString()))
		{
			throw new Exception("To combine two sums to 2* formula you'll need the two formulas to be the same.");
		}
		Formula a = temp.formulas.get(0);
		
		temp.formulas.clear();
		temp.name = "*";
		temp.formulas.add(new Formula("2"));
		temp.formulas.add(a);
		return form;
	}
	/**
	 * Converts (a + b) + c into a + (b + c)
	 * @param form
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public static Formula associativeSum(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("+"))
		{
			throw new Exception("Bad formula, need a sum +!");
		}
		Formula left = temp.formulas.get(0); // a + b
		Formula c = temp.formulas.get(1); // c
		if (!left.name.equals(Sum.sumTag))
		{
			throw new Exception("Bad formula, need a sum +!" + left.toString());
		}
		Formula a = left.formulas.get(0);
		Formula b = left.formulas.get(1);
		
		temp.formulas.clear();
		temp.formulas.add(a);
		Formula combine = new Sum(b, c);
		temp.formulas.add(combine);
		return form;
	}
	
	/**
	 * Converts a <+> (b + c) into (a + b) + c
	 * @param form
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public static Formula associativeSumRight(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("+"))
		{
			throw new Exception("Bad formula, need a sum +!");
		}
		Formula a = temp.formulas.get(0); // a 
		Formula right = temp.formulas.get(1); // b + c
		if (!right.name.equals(Sum.sumTag))
		{
			throw new Exception("Bad formula, need a sum +!" + right.toString());
		}
		Formula b = right.formulas.get(0);
		Formula c = right.formulas.get(1);
		
		temp.formulas.clear();
		Formula combine = new Sum(a, b);
		temp.formulas.add(combine);
		temp.formulas.add(c);
		return form;
	}
	
	//replace a - b by a + (-1 * b)
	// index should point at -. 
	public static Formula replaceSubtractionBySum(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("-"))
		{
			throw new Exception("Bad formula, need a subtraction -!");
		}
		Formula left = temp.formulas.get(0);
		Formula right = temp.formulas.get(1);
		
		//ok from now on we get a sum . 
		
		temp.name = "+";
		temp.formulas.clear();
		temp.formulas.add(left);
		Formula newRight = new Product(new Formula("-1"),right );
		temp.formulas.add(newRight);
		return form;
	}
	
	/**
	 * a + (-1 * a ) = 0
	 * @param form
	 * @param index
	 * @return
	 * @throws Exception 
	 */
	public static Formula sumOfInverseIsZero(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		Formula left = temp.formulas.get(0);
		Formula right = temp.formulas.get(1);
		
		if (!temp.name.equals("+"))
		{
			throw new Exception("Bad formula, need a sum +!");
		}
		/**
		 * @todo verify that a and -a are equal no cheating allowed! 
		 */
		temp.isLeaf = true;
		temp.formulas.clear();
		temp.name= "0";
		
		return form;
	}
	
	public static Formula removeZero(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		Formula left = temp.formulas.get(0);
		Formula right = temp.formulas.get(1);
		temp.formulas.clear();
		temp.formulas.add(left);
		return form;
	}
	
	public static Formula findFormulaPosition(Formula form, int[] index)
	{
		Formula temp = form;
		for (int i = 0; i < index.length;i++)
			temp = temp.formulas.get(index[i]);
		return temp;
	}
	
	
	
}
