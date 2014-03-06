package commonFunctions;

import utils.AnyFormula;
import utils.Formula;

public class Sum extends AnyFormula
{
	public final static String sumTag = "+"; 
	public Sum(Formula  left, Formula right) {
		super(sumTag);
		formulas.add(left);
		formulas.add(right);
		isLeaf = false;
		whatFix = OperatorFix.INFIX;
	}
	
}
