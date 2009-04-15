package edu.teco.automata.generator.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

   public static Test suite() {
      TestSuite suite = new TestSuite(
            "Test for edu.teco.automata.generator");
      //$JUnit-BEGIN$
      //new StandaloneSetup().setPlatformUri("../"); 
      suite.addTestSuite(XSD2Automata.class);
      suite.addTestSuite(DecEnc.class);
      suite.addTestSuite(DecEncSAX.class);
//      ResourcesPlugin.getWorkspace().getRoot();
      //$JUnit-END$
      return suite;
   }

}
