package axioms;

import utils.Formula;

public class ProbabilityAxiom 
{
	public Formula ALLEVENTS;
	public Formula IMPOSSIBLEEVENT;
	
	public Formula probabilityMeasureOfAllEvents(Formula event, int[] index) throws Exception
	{
		Formula temp = NaturalAxioms.findFormulaPosition(event, index);
		temp.whatFix = Formula.OperatorFix.INFIX;
		temp.formulas.clear();
		temp.isLeaf = true;
		temp.name = "1";
		return event;
	}
	
}
