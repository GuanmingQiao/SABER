/*
 * This file was automatically generated by EvoSuite
 * Sun Mar 08 16:45:16 GMT 2020
 */

package temp.replacement_repo;

import org.junit.Test;
import static org.junit.Assert.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import temp.replacement_repo.CustomObject;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CustomObject_ESTest extends CustomObject_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      CustomObject customObject0 = new CustomObject();
      customObject0.setValue(201);
      int int0 = customObject0.getValue();
      assertEquals(201, int0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      CustomObject customObject0 = new CustomObject();
      customObject0.setFoo("");
      String string0 = customObject0.getFoo();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      CustomObject customObject0 = new CustomObject();
      customObject0.setFoo("temp.replacement_repo.CustomObject");
      String string0 = customObject0.getFoo();
      assertEquals("temp.replacement_repo.CustomObject", string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      CustomObject customObject0 = new CustomObject();
      int int0 = customObject0.getValue();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      CustomObject customObject0 = new CustomObject();
      String string0 = customObject0.getFoo();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      CustomObject customObject0 = new CustomObject();
      customObject0.setValue((-1));
      int int0 = customObject0.getValue();
      assertEquals((-1), int0);
  }
}
