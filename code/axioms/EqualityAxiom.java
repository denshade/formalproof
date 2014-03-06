package axioms;


import utils.AnyFormula;
import utils.Formula;



public class EqualityAxiom {
	public static void main(String[] args)
	{
		AnyFormula f = new AnyFormula("a");
		AnyFormula b = new AnyFormula("b");
		Formula eq = new Formula("=");
		eq.isLeaf = false;
		eq.whatFix = Formula.OperatorFix.INFIX;
		eq.formulas.add(f);
		eq.formulas.add(b);
		System.out.println(eq.toString());
		EqualityAxiom.commutative(eq, new int[0]);
		
		
		System.out.println(eq.toString());
	}
	
	public static Formula commutative(Formula form, int[] index)
	{
		Formula temp = findFormulaPosition(form, index);
		
		Formula a = temp.formulas.get(0);
		Formula b = temp.formulas.get(1);
		
		temp.formulas.clear();
		
		temp.formulas.add(b);
		temp.formulas.add(a);
		
		return form;
	}
	
	private static Formula findFormulaPosition(Formula form, int[] index)
	{
		Formula temp = form;
		for (int i = 0; i < index.length;i++)
			temp = temp.formulas.get(index[i]);
		return temp;
	}
	
	public static Formula reflect(AnyFormula f)
	{
		Formula r = new Formula(new Formula(f),"=",new Formula(f));
		return r;
	}

}
