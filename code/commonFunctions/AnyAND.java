package commonFunctions;

import utils.AnyFormula;
import utils.Formula;

public class AnyAND extends AnyFormula {
	public final static String tag = "AND"; 
	
	public AnyAND(Formula left, Formula right) {
		super(tag);
		formulas.add(left);
		formulas.add(right);
		isLeaf = false;
		whatFix = OperatorFix.INFIX;
	}

}
