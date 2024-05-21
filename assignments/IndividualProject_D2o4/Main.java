package edu.gatech.seclass.txted;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;
/*
From ~/Projects/cs6300_A/6300Fall21dstrube3/IndividualProject/txted:

javac -d classes src/edu/gatech/seclass/txted/Main.java
java -cp classes edu.gatech.seclass.txted.Main

alternatively (to compile everything and run the tests):
javac -cp lib/\* -d classes src/edu/gatech/seclass/txted/*.java test/edu/gatech/seclass/txted/*.java
java -cp classes:lib/\* org.junit.runner.JUnitCore edu.gatech.seclass.txted.MyMainTest

https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
https://docs.oracle.com/javase/8/docs/api/java/nio/file/StandardOpenOption.html

https://edstem.org/us/courses/8344/discussion/792892

*/

public class Main {

    // Main class for compiling Individual Project.
    // DO NOT COMMIT THIS CLASS
    
    //some code that might be helpful:
    //BigFileMaker
    //DeliciousScraper
    //EmailParser
    private static final List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
    	if(args == null || args.length == 0){
        	usage();
        	return;
        }
        
        final String filePath = args[args.length-1];
        final File file = new File(filePath);
		try {
			if (!file.exists()){
				usage();
				return;
			}
			//Write data to file
			Files.write(file, lines, Charset.forName("UTF-16"));
		} catch (FileNotFoundException fileNotFoundException) {
        	usage();
        	return;
		} catch(IOException ioException){
        	usage();
        	return;
		}
    }

    private static void usage() {
        System.err.println("Usage: txted [ -f | -i | -s integer | -e string | -r | -x string | -n integer ] FILE");
    }
    
    
}