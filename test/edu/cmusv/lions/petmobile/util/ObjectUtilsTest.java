package edu.cmusv.lions.petmobile.util;

import java.util.List;

import android.test.AndroidTestCase;

public class ObjectUtilsTest extends AndroidTestCase {

	public static class CatLover {
		public static final String CAT1 = "Charlie";
		public static final String CAT2 = "Felix";
		public static final String CAT3 = "Muffins";
	}
	
	public void testGetStringConstants() {
		List<String> cats = ObjectUtils.getStringConstants(CatLover.class);
		assertTrue(cats.size() == 3);
		assertTrue(cats.contains(CatLover.CAT1));
		assertTrue(cats.contains(CatLover.CAT2));
		assertTrue(cats.contains(CatLover.CAT3));
	}
}
