package commonFunctions;

import utils.AnyFormula;
import utils.Formula;

public class Implies extends AnyFormula
{
	public final static String tag = "=>"; 
	public Implies(Formula  left, Formula right) {
		super(tag);
		formulas.add(left);
		formulas.add(right);
		isLeaf = false;
		whatFix = OperatorFix.INFIX;
	}
}
