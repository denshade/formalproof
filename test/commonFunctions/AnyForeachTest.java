package commonFunctions;

import commonSets.Naturals;
import org.junit.jupiter.api.Test;
import utils.Formula;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AnyForeachTest  {

	@Test
	public void testToString() {
		Foreach f = new Foreach("x", new Naturals(), new Formula("x=x"));
		assertEquals("foreach(x element_of Naturals proposition x=x)"
				, f.toString());
	}

}
