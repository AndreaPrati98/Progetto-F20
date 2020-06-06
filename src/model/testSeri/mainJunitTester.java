package model.testSeri;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class mainJunitTester {

	public static void main(String[] args) {
	
		Result result = JUnitCore.runClasses(MaxConstraintTest.class);
		for (Failure elem : result.getFailures()) {
			System.out.println(elem.toString());
		}
		
		System.out.println("Test effettuati: "+ result.getRunCount());
		System.out.println("Test falliti: " + result.getFailureCount());
		System.out.println("Test con successo " + (result.getRunCount() - result.getFailureCount()));
		
	}

}
