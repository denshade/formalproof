package proof;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LogicsProofTest  {

	@Test
	public void testSubsetImply() {
		try {
			assertEquals(" ( a =>  ( b => a ) )", LogicsProof.subsetImply().toString());
		} catch (Exception e) {
			fail("Exception thrown:" +e.toString());
		}
	}

}
