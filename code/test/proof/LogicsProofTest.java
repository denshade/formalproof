package test.proof;

import proof.LogicsProof;
import junit.framework.TestCase;

public class LogicsProofTest extends TestCase {

	public void testSubsetImply() {
		try {
			assertEquals(" ( a =>  ( b => a ) )", LogicsProof.subsetImply().toString());
		} catch (Exception e) {
			fail("Exception thrown:" +e.toString());
		}
	}

}
