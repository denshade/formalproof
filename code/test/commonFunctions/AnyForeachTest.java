package test.commonFunctions;

import utils.Formula;
import commonFunctions.Foreach;
import commonSets.Naturals;

import junit.framework.TestCase;

public class AnyForeachTest extends TestCase {

	public void testToString() {
		Foreach f = new Foreach("x", new Naturals(), new Formula("x=x"));
		assertEquals("foreach(x element_of Naturals proposition x=x)"
				, f.toString());
	}

}
