package axioms;


import org.junit.jupiter.api.Test;

public class PeanoAxiomsTest {

	@Test
	public void testReflexiveEq() {
		System.out.println(PeanoAxioms.reflexiveEq("x"));
		System.out.println(PeanoAxioms.symmetricalEq("x", "y"));
		System.out.println(PeanoAxioms.transitiveEq("x", "y", "z"));
		
	}

}
