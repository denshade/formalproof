package commonFunctions;

import commonSets.FormalSet;

import utils.AnyFormula;
import utils.Formula;


//this is a formula that can be right or wrong, to be honest we don't really care...
public class Foreach  extends AnyFormula {
	protected String elementName;
	protected FormalSet set;
	
	public Foreach(String elementName, FormalSet set, Formula proposition) {
		super("foreach");
		formulas.add(proposition);
		this.elementName = elementName;
		this.set = set;
	}
	
	public String toString()
	{
		String str = new String("foreach(");
		str += elementName + " element_of ";
		str += set.getName() + " proposition " + formulas.get(0).toString() + ")";
		return str;
	}
}
