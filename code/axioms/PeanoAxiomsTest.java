package axioms;

import junit.framework.TestCase;

public class PeanoAxiomsTest extends TestCase {

	public void testReflexiveEq() {
		System.out.println(PeanoAxioms.reflexiveEq("x"));
		System.out.println(PeanoAxioms.symmetricalEq("x", "y"));
		System.out.println(PeanoAxioms.transitiveEq("x", "y", "z"));
		
	}

}
