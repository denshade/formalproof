package commonFunctions;

import utils.AnyFormula;
import utils.Formula;

public class AnyNOT extends AnyFormula {
	public final static String tag = "NOT"; 
	
	public AnyNOT(Formula left) {
		super(tag);
		formulas.add(left);
		isLeaf = false;
		whatFix = OperatorFix.PREFIX;
	}

}
