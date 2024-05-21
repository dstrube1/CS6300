package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 *
 * You should implement your tests in this class.
 */

public class MyStringTest {

    private MyStringInterface mystring;

    @Before
    public void setUp() {
        mystring = new MyString();
    }

    @After
    public void tearDown() {
        mystring = null;
    }

    @Test
    // Description: Instructor-provided test 1
    public void testCountNumbersS1() {
        mystring.setString("My numbers are 11, 96, and thirteen");
        assertEquals(2, mystring.countNumbers());
    }

    @Test
    // Description: Count numbers in an empty string
    public void testCountNumbersS2() {
        mystring.setString("");
        assertEquals(0, mystring.countNumbers());
    }

    @Test
    // Description: Testing with a non empty string
    public void testCountNumbersS3() { 
        mystring.setString("I don't handle real number such as 10.4.");
        assertEquals(2, mystring.countNumbers());
    }

     @Test(expected = NullPointerException.class)
    // Description: Testing for NullPointerException
    public void testCountNumbersS4() {
        mystring.setString(null);
        mystring.countNumbers();
    }

    @Test
    // Description: Instructor-provided test 2
    public void testAddNumberS1() {
        mystring.setString("hello 90, bye 2");
        assertEquals("hello 92, bye 4", mystring.addNumber(2, false));
    }

    @Test
    // Description: Testing with an empty string and n is 11 and inverse is false
    public void testAddNumberS2() {
        mystring.setString("");
        assertEquals("", mystring.addNumber(11, false));
    }

    @Test(expected = NullPointerException.class)
    // Description: Testing for NullPointerException
    public void testAddNumberS3() {
        mystring.setString(null);
        mystring.addNumber(11, false);
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: Testing for IllegalArgumentException
    public void testAddNumberS4() {
        mystring.setString("");
        mystring.addNumber(-1, false);
    }

    @Test
    // Description: Testing with a non empty string and n is 8 and inverse is true
    public void testAddNumberS5() { 
        mystring.setString("hello 90, bye 2");
        assertEquals("hello 89, bye 01", mystring.addNumber(8, true));
    }

    @Test
    // Description: Testing with a non empty string and n is 0 and inverse is true
    public void testAddNumberS6() {
        mystring.setString("12345");
        assertEquals("54321", mystring.addNumber(0, true));
    }

    @Test
    // Description: Instructor-provided test 3
    public void testConvertDigitsToNamesInSubstringS1() {
        mystring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mystring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZEROme dONESIX1ts in this 5tr1n6, right?", mystring.getString());
    }

    @Test(expected = NullPointerException.class)
    // Description: Testing for NullPointerException
    public void testConvertDigitsToNamesInSubstringS2() {
        mystring.setString(null);
        mystring.convertDigitsToNamesInSubstring(17, 23);
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: Testing for IllegalArgumentException condition 1
    public void testConvertDigitsToNamesInSubstringS3() {
        mystring.setString("");
        mystring.convertDigitsToNamesInSubstring(-1, 23);
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: Testing for IllegalArgumentException condition 2
    public void testConvertDigitsToNamesInSubstringS4() {
        mystring.setString("");
        mystring.convertDigitsToNamesInSubstring(24, 23);
    }

    @Test(expected = MyIndexOutOfBoundsException.class)
    // Description: Testing for MyIndexOutOfBoundsException
    public void testConvertDigitsToNamesInSubstringS5() {
        mystring.setString("x");
        mystring.convertDigitsToNamesInSubstring(1, 2);
    }

    @Test
    // Description: Testing with a non empty string and firstPosition is 0 and finalPosition is 3
    public void testConvertDigitsToNamesInSubstringS6() {
        mystring.setString("x101x");
        mystring.convertDigitsToNamesInSubstring(1, 3);
        assertEquals("xONEZERO1x", mystring.getString());
    }
}

