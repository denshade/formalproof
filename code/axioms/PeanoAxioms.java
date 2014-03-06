package axioms;

import commonFunctions.Equals;
import commonFunctions.Foreach;
import commonFunctions.Implies;

import utils.Formula;

public class PeanoAxioms 
{
	/**
	 * For each x element of N. x = x.
	 */
	public static Formula reflexiveEq(String x)
	{
		Formula eq = new Formula("=");
		eq.isLeaf = false;
		eq.whatFix = Formula.OperatorFix.INFIX;
		eq.formulas.add(new Formula(x));
		eq.formulas.add(new Formula(x));
		Formula fore = new Foreach(x, new commonSets.Naturals(), eq );
		return fore;
	}
	/**
	 * For each x, y element of N. x = y => y = x.
	 */
	public static Formula symmetricalEq(String x, String y)
	{
		Formula eq1 = new Equals(new Formula(x), new Formula(y));
		Formula eq2 = new Equals(new Formula(y), new Formula(x));
		Formula implies = new Implies(eq1,eq2);
		Formula forx = new Foreach(x, new commonSets.Naturals(), implies );
		Formula fory = new Foreach(y, new commonSets.Naturals(), forx );
		return fory;
	}
	
	/**
	 * For each x, y, z element of N. x = y AND y = z => x = z.
	 */
	public static Formula transitiveEq(String x, String y, String z)
	{
		Formula eq1 = new Equals(new Formula(x), new Formula(y));
		Formula eq2 = new Equals(new Formula(y), new Formula(z));
		Formula eq3 = new Equals(new Formula(x), new Formula(z));
		Formula and = new commonFunctions.AnyAND(eq1,eq2);
		Formula implies = new Implies(and,eq3);
		Formula forx = new Foreach(x, new commonSets.Naturals(), implies );
		Formula fory = new Foreach(y, new commonSets.Naturals(), forx );
		return fory;
	}
	
}
