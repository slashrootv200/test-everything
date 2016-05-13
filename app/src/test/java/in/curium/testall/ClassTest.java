package in.curium.testall;

import in.curium.testall.activities.FragmentLifeCycleActivity;
import org.junit.Test;

public class ClassTest {
  @Test
  public void testClassName() {
    System.out.println(FragmentLifeCycleActivity.class.getSimpleName());
    System.out.println(FragmentLifeCycleActivity.class.getCanonicalName());
    System.out.println(FragmentLifeCycleActivity.class.getName());
  }
}
