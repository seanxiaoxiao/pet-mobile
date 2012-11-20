package edu.cmusv.lions.petmobile;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

public class PetTestSuite extends TestSuite {

	public static Test suite() {
        return new TestSuiteBuilder(PetTestSuite.class).includeAllPackagesUnderHere().build();
    }
}
