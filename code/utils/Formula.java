package utils;
import java.util.ArrayList;
import java.util.List;


/*
 * Created on 28-sep-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author DS
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Formula {

	public static void main(String[] args) {
	}
	public enum OperatorFix
	{
		PREFIX,
		INFIX,
		SUFFIX
	}
	/**
	 * Clone constructor
	 * @param obj
	 */
	public Formula(Formula obj)
	{
		this.name = obj.name;
		formulas = new ArrayList<Formula>();
		for(Formula f : obj.formulas)
		{
			formulas.add(new Formula(f));
		}
//		formulas.addAll(obj.formulas);//This doesn't deep copy new objects with children.
		whatFix = obj.whatFix;
		isLeaf = obj.isLeaf;
	}
	
	
	public Formula( Formula a, String name, Formula b)
	{
		this(name, OperatorFix.INFIX, a, b);
	}
	
	public Formula(String name, OperatorFix fix, Formula a, Formula b)
	{
		this.name = name;
		formulas = new ArrayList<Formula>();
		formulas.add(new Formula(a));
		formulas.add(new Formula(b));
		whatFix = fix;
		isLeaf = false;
	}
	
	public Formula(String name)
	{
		this.name = name;
		formulas = new ArrayList<Formula>();
		whatFix = OperatorFix.PREFIX;
		isLeaf = true;
	}
	public OperatorFix whatFix; 
	public boolean isLeaf = true;	
	public List<Formula> formulas;
	public String name; // name of the function or token if it is a leaf 
	public String toString()
	{
		String str = ""; 
		switch(whatFix)
		{
		case PREFIX:
			str = name ;
		    if (!isLeaf)
		    {
		    	str += " ( ";
		    	for (int i = 0; i < formulas.size()-1; i++)
		    		str += formulas.get(i) +", ";
		    	str += formulas.get(formulas.size()-1);
		    	str += " )"; 
		    }
			break;
		case INFIX:
			if (isLeaf)
				str += name;
			else
			{
				str += " ( ";
				for (int i = 0; i < formulas.size()-1; i++)
		    		str += formulas.get(i) +" " + name + " ";
				str += formulas.get(formulas.size()-1);
				str += " )";
			}			    
			break;
		case SUFFIX:
			str += " ( ";
			for (int i = 0; i < formulas.size()-1; i++)
	    		str += formulas.get(i) +", ";
			str += formulas.get(formulas.size()-1);			
			str += " )";
			str += name;
			break;
		}
		
		
	    return str;
	}
	
	public boolean equals(Formula other)
	{
		if (this.name != other.name)
			return false;
		if (other.formulas.size() != formulas.size())
		{
			return false;
		}
		for (int a = 0; a < formulas.size(); a++)
		{
			if (!formulas.get(a).equals(other.formulas.get(a)))
			{
				return false;
			}
		}
		return true;
	}
}
