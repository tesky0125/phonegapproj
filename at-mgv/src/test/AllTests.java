package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BlockTest.class, 
	ClientTest.class, 
	LoginTest.class,
	MediaTest.class, 
	MGV_All_Test.class, 
	PlaylistTest.class,
	ScheduleTest.class })
public class AllTests {

}
