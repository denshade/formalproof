package commonFunctions;

import utils.Formula;

public class OR extends Formula {
	public final static String tag = "OR"; 
	
	public OR(Formula left, Formula right) {
		super(tag);
		formulas.add(left);
		formulas.add(right);
		isLeaf = false;
		whatFix = OperatorFix.INFIX;
	}

}
