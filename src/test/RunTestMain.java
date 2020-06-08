package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTestMain {

	public static void main(String[] args) {
		Result res = JUnitCore.runClasses(MaxConstraintTest.class);
		
		for (Failure failure : res.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println("Was successful? "+ res.wasSuccessful());
		
	}
	
}
