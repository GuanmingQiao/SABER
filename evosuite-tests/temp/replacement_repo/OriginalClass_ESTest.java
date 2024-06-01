/*
 * This file was automatically generated by EvoSuite
 * Sun Mar 08 16:45:52 GMT 2020
 */

package temp.replacement_repo;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import temp.replacement_repo.CustomObject;
import temp.replacement_repo.OriginalClass;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class OriginalClass_ESTest extends OriginalClass_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      OriginalClass originalClass0 = new OriginalClass();
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = Integer.valueOf(209);
      linkedList0.add(integer0);
      CustomObject customObject0 = new CustomObject();
      linkedList0.add(integer0);
      String string0 = originalClass0.objectMethod(linkedList0, customObject0);
      assertEquals("0_2", string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      OriginalClass originalClass0 = new OriginalClass();
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      int int0 = originalClass0.originalMethod(linkedList0, 0);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      OriginalClass originalClass0 = new OriginalClass();
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      int int0 = originalClass0.originalMethod(linkedList0, 472);
      assertEquals(472, int0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      OriginalClass originalClass0 = new OriginalClass();
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      linkedList0.offerFirst((Integer) null);
      // Undeclared exception!
      try { 
        originalClass0.originalMethod(linkedList0, (-2774));
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("temp.replacement_repo.OriginalClass", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      OriginalClass originalClass0 = new OriginalClass();
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      // Undeclared exception!
      try { 
        originalClass0.objectMethod(linkedList0, (CustomObject) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("temp.replacement_repo.OriginalClass", e);
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      OriginalClass originalClass0 = new OriginalClass();
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      CustomObject customObject0 = new CustomObject();
      Integer integer0 = new Integer((-22));
      linkedList0.add(integer0);
      linkedList0.push(integer0);
      linkedList0.add(integer0);
      String string0 = originalClass0.objectMethod(linkedList0, customObject0);
      assertEquals("list size greater than 2", string0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      OriginalClass originalClass0 = new OriginalClass();
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-22));
      linkedList0.add(integer0);
      int int0 = originalClass0.originalMethod(linkedList0, (-22));
      assertEquals((-44), int0);
  }
}
