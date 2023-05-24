package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.jupiter.api.Test;

public class EnumTest {
	
	@Test
	public void testOrdinal() {
		assertEquals(0, NORD.ordinal());
		assertEquals(1, EST.ordinal());
		assertEquals(2, SUD.ordinal());
		assertEquals(3, OVEST.ordinal());
	}
}
