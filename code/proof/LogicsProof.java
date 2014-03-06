package proof;

import utils.Formula;
import utils.AnyFormula;
import axioms.*;



public class LogicsProof {

	public static void main(String[] args) throws Exception {
		subsetImply();
	}
	
	//showing that a => (b => a ) is always true.
	public static Formula subsetImply() throws Exception
	{
		Formula f = LogicsAxiom.notOr(new Formula("a"));
		AnyFormula k = new AnyFormula("b");
		k = LogicsAxiom.AnyNot(k);
		f = LogicsAxiom.ProvenORAny(f, k);
		int[] arr = {}; 
		f = LogicsAxiom.commutativityOr(f,  arr);	//( NOT ( b ) OR  ( NOT ( a ) OR a ) )	
		int[] tarr = {1};
		int[] tzero = {0};
		f = LogicsAxiom.commutativityOr(f,  tarr);
		f = LogicsAxiom.OrAssoc(f,arr);//(  ( NOT ( b ) OR a ) OR  ( NOT ( a ) ) )
		f = LogicsAxiom.definitionOrToImply(f, tzero);//(  ( NOT ( NOT ( b ) ) => a ) OR  ( NOT ( a ) ) )
		int[] t2zero = {0,0};
		f = LogicsAxiom.removeDoubleNegative(f, t2zero);//(  ( b => a ) OR NOT ( a ) )
		f = LogicsAxiom.commutativityOr(f,  arr); //( NOT ( a ) OR  ( b => a ) )
		f = LogicsAxiom.definitionOrToImply(f, arr); //( NOT ( NOT ( a ) ) =>  ( b => a ) )
		f = LogicsAxiom.removeDoubleNegative(f, tzero); //( a =>  ( b => a ) )
		return f;
	}
	//	build 
	
	
}
