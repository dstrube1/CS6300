package edu.gatech.seclass;

import org.junit.Test;
import junit.framework.TestCase;

/*
from Assignment6 dir in 6300Fall21dstrube3 repository:
 
javac -cp bin:lib/hamcrest-core-1.3.jar:lib/junit-4.12.jar -d bin test/edu/gatech/seclass/SnafuClassTestPC1.java
java -cp bin:lib/hamcrest-core-1.3.jar:lib/junit-4.12.jar edu.gatech.seclass.SnafuClassTestPC1

*/

/**
 * This is a Georgia Tech provided code example for use in assigned
 * private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it
 * available on publicly viewable websites including repositories such
 * as GitHub and GitLab.  Such sharing may be investigated as a GT
 * honor code violation. Created for CS6300 Fall 2021.
 *
 * Junit test class provided for the White-Box Testing Assignment.
 * This class should not be altered.  Follow the directions to create
 * similar test classes when required.
 */

public class SnafuClassTestPC1 extends TestCase {

	public static void main(String[] args)
	{
		TestFalse();
		TestTrue();
	}	

    @Test
    public static void TestFalse() { 
    	System.out.println("Hello from TestFalse");
    	SnafuClass.snafuMethod1(false);
    }

    @Test
    public static void TestTrue() { 
    	System.out.println("Hello from TestTrue");
    	SnafuClass.snafuMethod1(true); 
    }

}