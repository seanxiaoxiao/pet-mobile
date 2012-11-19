package edu.cmusv.lions.petmobile;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {

	boolean valid;

	@Before
	public void setUp() {
		valid = true;
	}

	@After
	public void tearDown() {
	}

	@Test
	 public void someTest() {
		 assertEquals(valid, true);
	 }
}
