package edu.gatech.seclass;

import org.junit.Test;
import junit.framework.TestCase;

/*
from Assignment6 dir in 6300Fall21dstrube3 repository:
 
javac -cp bin:lib/hamcrest-core-1.3.jar:lib/junit-4.12.jar -d bin test/edu/gatech/seclass/SnafuClassTestBC1.java
java -cp bin:lib/hamcrest-core-1.3.jar:lib/junit-4.12.jar edu.gatech.seclass.SnafuClassTestBC1

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

public class SnafuClassTestBC1 extends TestCase {
	public static void main(String[] args)
	{
		Test1();
	}	

    @Test
    public static void Test1() { 
    	//Skip Branch 1
    	System.out.println("Hello from Test1");
    	SnafuClass.snafuMethod1(false); 
    }

}