package commonFunctions;

import utils.AnyFormula;
import utils.Formula;

public class Equals  extends AnyFormula {
	public final static String tag = "="; 
	
	public Equals(Formula left, Formula right) {
		super(tag);
		formulas.add(left);
		formulas.add(right);
		isLeaf = false;
		whatFix = OperatorFix.INFIX;
	}


}
