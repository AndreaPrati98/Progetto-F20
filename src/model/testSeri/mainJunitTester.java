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
		
	}

}
