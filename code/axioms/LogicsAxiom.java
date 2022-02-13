package axioms;

import commonFunctions.AnyNOT;
import commonFunctions.Implies;
import commonFunctions.NOT;
import commonFunctions.OR;
import utils.AnyFormula;
import utils.Formula;

/*
 * Created on 28-sep-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


public class LogicsAxiom {

	public static void main(String[] args) {
		Formula f= new Formula("a");
		Formula fb= new Formula("b");
		Formula faorb = Or(And(f, Not(fb)),fb);
		System.out.println(faorb.toString());
	}
	private static Formula Not(Formula first)
	{		
		Formula f = new Formula("NOT");		
		f.formulas.add(first);
		f.isLeaf = false;
		return f;
	}
	
	public static AnyFormula AnyNot(Formula first)
	{		
		AnyFormula f = new AnyNOT(first);		
		return f;
	}
	
	
	//Formula constructors for internal use only.
	private static Formula Or(Formula first, Formula second)
	{		
		return glueinfix("OR", first, second);
	}
	private static Formula trueFormula()
	{
		return new Formula("true");
	}
	
	private static Formula NotFalseFormula()
	{
		return Not(new Formula("true"));
	}
	
	private static Formula And(Formula first, Formula second)
	{		
		return new commonFunctions.AnyAND(first, second);
	}
	private static Formula Imply(Formula first, Formula second)
	{		
		return new Implies(first, second);
	}
	
	//constructors for any formulas.
	public static AnyFormula AnyOr(Formula first, Formula second)
	{		
		return (AnyFormula)glueinfix("OR", first, second);
	}
	public static AnyFormula AnytrueFormula()
	{
		return (AnyFormula)new Formula("true");
	}
	
	public static AnyFormula AnyNotFalseFormula()
	{
		return (AnyFormula)Not(new Formula("true"));
	}
	
	public static AnyFormula AnyAnd(Formula first, Formula second)
	{		
		return (AnyFormula)glueinfix("AND", first, second);
	}
	public static AnyFormula AnyImply(Formula first, Formula second)
	{		
		return anyglueinfix("=>", first, second);
	}
	/**
	 * 
	 * NOT 
	 * @throws Exception 
	 * 
	 */
	
	public static Formula removeDoubleNegative(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		
		if (!temp.name.equals("NOT"))
			throw new Exception("Bad formula, need a negation!");
		Formula f2 = temp.formulas.get(0);
		if (!f2.name.equals("NOT"))
			throw new Exception("Bad formula, need a negation!");
		Formula f3 =  f2.formulas.get(0);		
		temp.formulas = f3.formulas;
		temp.isLeaf = f3.isLeaf;
		temp.name = f3.name;
		temp.whatFix = f3.whatFix;		
		return form;
	}
	
	public static Formula addDoubleNegative(Formula form)
	{
		return Not(Not(form));
	}
	//axioms 
	/**
	 * OR 
	 * @throws Exception 
	 */
	//proven statement OR any formula is true.
	
	public static Formula ProvenORAny(Formula proven, AnyFormula form) throws Exception
	{
		if ( proven instanceof AnyFormula)
			throw new Exception("proven should not be anyFormula.");
		return new OR(proven, (Formula)form); //we convert the AnyFormula 
	}
	// A OR (B OR C) => (A OR B) OR C
	public static Formula OrAssoc(Formula form, int[] index)
	{
		Formula temp = findFormulaPosition(form, index);
		
		Formula forma = temp.formulas.get(0);
		Formula formb = temp.formulas.get(1).formulas.get(0);
		Formula formc = temp.formulas.get(1).formulas.get(1);
		Formula formab = Or(forma, formb);
		temp.formulas.get(1).formulas.clear();
		temp.formulas.set(1, formc);//.formulas.add(formc);
		temp.formulas.set(0, formab);
		return temp;
	}
	
	// NOT f OR f is always true
	public static Formula notOr(Formula form)
	{
		return new OR(new NOT(form), form);
	}
	
	public static Formula commutativityOr(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("OR"))
			throw new Exception("Bad formula, need a disjunction!");
		
		Formula left = temp.formulas.get(0);
		Formula right = temp.formulas.get(1);
		temp.formulas.set(0,right);temp.formulas.set(1,left);
		return form;		
	}

	private static Formula findFormulaPosition(Formula form, int[] index)
	{
		Formula temp = form;
		for (int i = 0; i < index.length;i++)
			temp = temp.formulas.get(index[i]);
		return temp;
	}
	
//	 a => b equals not a or b
	public static Formula definitionOrToImply(Formula form, int[] index) throws Exception
	{
		Formula temp = findFormulaPosition(form, index);
		if (!temp.name.equals("OR"))
			throw new Exception("Bad formula, need a disjunction!");
		temp.isLeaf = false;
		temp.formulas.set(0, Not(temp.formulas.get(0)));
		temp.formulas.set(1, temp.formulas.get(1));
		temp.name = "=>";
		return form;
	}
	
	/*
	 *  IMPLICATION
	 * */
	// a => a
	public static Formula reflectiveImply(AnyFormula name)
	{
		return Imply((Formula)name,(Formula)name);
	}
	// a => b equals not a or b
	public static Formula definitionImplyToOr(Formula form) throws Exception
	{
		if (!form.name.equals("=>"))
			throw new Exception("Bad formula, need an implication!");
		return Or(Not(form.formulas.get(0)), form.formulas.get(1));
	}
	// NOT (1 => 0)
	public static Formula notTrueimplyFalse()
	{
		return Imply(trueFormula(), Not(trueFormula()));		
	}
	
	private static AnyFormula anyglueinfix(String name, Formula first, Formula second)
	{
		AnyFormula f = new AnyFormula(name);		
		f.whatFix = Formula.OperatorFix.INFIX;
		f.formulas.add(first);
		f.formulas.add(second);
		f.isLeaf = false;
		return f;		
	}
	
	private static Formula glueinfix(String name, Formula first, Formula second)
	{
		Formula f = new Formula(name);		
		f.whatFix = Formula.OperatorFix.INFIX;
		f.formulas.add(first);
		f.formulas.add(second);
		f.isLeaf = false;
		return f;		
	}
}
