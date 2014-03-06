package commonFunctions;

import utils.Formula;

public class NOT extends Formula {
	public final static String tag = "NOT"; 
	
	public NOT(Formula left) {
		super(tag);
		formulas.add(left);
		isLeaf = false;
		whatFix = OperatorFix.PREFIX;
	}

}
