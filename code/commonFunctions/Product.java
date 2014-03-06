package commonFunctions;

import utils.AnyFormula;
import utils.Formula;

public class Product extends AnyFormula
{
	public final static String tag = "*"; 
	public Product(Formula  left, Formula right) {
		super(tag);
		formulas.add(left);
		formulas.add(right);
		isLeaf = false;
		whatFix = OperatorFix.INFIX;
	}

}
