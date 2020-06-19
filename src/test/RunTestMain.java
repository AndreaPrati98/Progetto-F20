package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * 
 * @author Andrea
 *
 */
public class RunTestMain {

	public static void main(String[] args) {
		
		Result res = JUnitCore.runClasses(AbstractConstraintTestCouples.class);
		 
		for (Failure failure : res.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println("Tested "+ AbstractConstraintTestCouples.class);	
		System.out.println("Success: "+ (res.getRunCount() - res.getFailureCount()));
		System.out.println("Failures: "+ res.getFailureCount());
		System.out.println("Number of runned tests: "+ res.getRunCount());
		System.out.println("Number of ignored test: "+ res.getIgnoreCount());
		System.out.println("Was successful? "+ res.wasSuccessful());
		System.out.println();
		
		res = JUnitCore.runClasses(AbstractConstraintTestTriple.class);

		for (Failure failure : res.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println("Tested "+ AbstractConstraintTestTriple.class);
		System.out.println("Success: "+ (res.getRunCount() - res.getFailureCount()));
		System.out.println("Failures: "+ res.getFailureCount());
		System.out.println("Number of runned tests: "+ res.getRunCount());
		System.out.println("Number of ignored test: "+ res.getIgnoreCount());
		System.out.println("Was successful? "+ res.wasSuccessful());
		System.out.println();
		
	}
	
}
