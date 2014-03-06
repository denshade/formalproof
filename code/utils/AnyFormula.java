package utils;
import java.util.ArrayList;
import java.util.List;

import utils.Formula.OperatorFix;


//this is a formula that can be right or wrong, to be honest we don't really care...
//Don't use this in proofs. Only use this to create axioms.
public class AnyFormula  extends Formula {
	
	public AnyFormula(String name) {
		super(name);
	}
	
	
	public AnyFormula( Formula a, String name, Formula b) {
		super(name, Formula.OperatorFix.INFIX, a, b);
	}
	
	public AnyFormula(String name, OperatorFix fix, Formula a, Formula b) {
		super(name, fix, a, b);
	}
	
	public AnyFormula(AnyFormula name) {
		super(name);
	}
}
