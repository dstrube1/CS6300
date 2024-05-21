package edu.gatech.seclass.txted;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyMainTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();
    private final Charset charset = StandardCharsets.UTF_8;
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private final String USAGE_ERROR = "Usage: txted [ -f | -i | -s integer | -e string | -r | -x string | -n integer ] FILE";

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    /*
     *  TEST UTILITIES
     */

    // Create File Utility
    private File createTmpFile(final String... filename) throws Exception {
        File tmpfile = null;
        if (filename == null){
        	tmpfile = temporaryFolder.newFile();
        }else{
        	tmpfile = temporaryFolder.newFile(filename[0]);
        }
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input, final String... filename) throws Exception {
        File file = createTmpFile(filename);
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        fileWriter.write(input);
        fileWriter.close();
        return file;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = Files.readString(Paths.get(filename), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /*
     *   TEST CASES
     */
	
	// Frame #:  1
	// Presence of f option :  One f
	@Test
	public void txtedTest1() throws Exception {
		// Based off of Instructors Example 1
        String input = "alphanumeric123foobar" + System.lineSeparator();
        String expected = "alphanumeric123foobar" + System.lineSeparator();

		String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-f", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("File differs from expected", expected, getFileContent(inputFile.getPath()));
	}

	// Frame #: 2
	// Value of s param :  0 s param
	@Test
	public void txtedTest2() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator() +
                "line 3" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-s", "0", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 3
	// Value of s param :  1 s param
	@Test
	public void txtedTest3() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = "line 2" + System.lineSeparator() +
                "line 4" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-s", "1", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 4
	// Value of s param :  Not 0 not 1 s param
	@Test
	public void txtedTest4() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-s", "2", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 5
	// Value of s param :  Empty s param
	@Test
	public void txtedTest5() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-s", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 6
	// Length of the e param :  Empty e param
	@Test
	public void txtedTest6() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 7
	// Length of the e param :  Many e param
	@Test
	public void txtedTest7() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "e 2", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 8
	// One r without file
	@Test
	public void txtedTest8() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-r"};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 9 //duplicate of 17?
	// One x with file
	@Test
	public void txtedTest9() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = "line 1!" + System.lineSeparator() +
                "line 2!" + System.lineSeparator() +
                "line 3!" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-x", "!", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 10
	// One x without file
	@Test
	public void txtedTest10() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-x", "?"};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 11
	// One e without file
	@Test
	public void txtedTest11() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String[] args = {"-e"};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
	}

	// Frame #: 12
	// Presence of i option :  One i with e
	@Test
	public void txtedTest12() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line with a Mix of cases" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "mix", "-i", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 13
	// -f on empty file
	@Test
	public void txtedTest13() throws Exception {
	    String input = System.lineSeparator();
        String expected = ""; 

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-f", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 14
	// Presence of i option :  i without e
	@Test
	public void txtedTest14() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-i", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 15
	// -e larger string than file content
	@Test
	public void txtedTest15() throws Exception {
		//from https://edstem.org/us/courses/8344/discussion/842538
	    String input = "a" + System.lineSeparator();
        String expected = "0000001 a!!" + System.lineSeparator() ; 

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", "7", "-s", "0", "-x", "!!", "-r", "-e", 
        	"0000001abc0000001abc0000001abc0000001abc!!", "-i", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}


	// Frame #: 16
	// Length of the x param :  Empty x param
	@Test
	public void txtedTest16() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-x", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 17
	// Length of the x param :  Many x param
	@Test
	public void txtedTest17() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = "line 1? !" + System.lineSeparator() +
                "line 2? !" + System.lineSeparator() +
                "line 3? !" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-x", "? !", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 18
	// Length of the x param :  X_length > the file
	@Test
	public void txtedTest18() throws Exception {
        String input = "1" + System.lineSeparator();
        String expected = "1!!!" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-x", "!!!", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 19
	// Presence of r option :  Many r
	@Test
	public void txtedTest19() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = "line 3" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 1" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-r", "-r", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 20
	// Presence of n option :  Many n
	@Test
	public void txtedTest20() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = "01 line 1" + System.lineSeparator() +
                "02 line 2" + System.lineSeparator() +
                "03 line 3" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", "10", "-n", "2", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 21
	// N Value :  <0 n param
	@Test
	public void txtedTest21() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", "-1", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 22
	// N Value :  0 n param
	@Test
	public void txtedTest22() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = " line 1" + System.lineSeparator() +
                " line 2" + System.lineSeparator() +
                " line 3" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", "0", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 23
	// N Value :  1 n param
	@Test
	public void txtedTest23() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator() +
                "line 5" + System.lineSeparator() +
                "line 6" + System.lineSeparator() +
                "line 7" + System.lineSeparator() +
                "line 8" + System.lineSeparator() +
                "line 9" + System.lineSeparator() +
                "line 10" + System.lineSeparator() +
                "line 11" + System.lineSeparator();
        String expected = "1 line 1" + System.lineSeparator() +
                "2 line 2" + System.lineSeparator() +
                "3 line 3" + System.lineSeparator() +
                "4 line 4" + System.lineSeparator() +
                "5 line 5" + System.lineSeparator() +
                "6 line 6" + System.lineSeparator() +
                "7 line 7" + System.lineSeparator() +
                "8 line 8" + System.lineSeparator() +
                "9 line 9" + System.lineSeparator() +
                "0 line 10" + System.lineSeparator() +
                "1 line 11" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", "1", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 24
	// N Value :  >Maxint n param
	@Test
	public void txtedTest24() throws Exception {
		String input = "line 1" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", "4048943152", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 25
	// N Value :  Empty n param
	@Test
	public void txtedTest25() throws Exception {
		String input = "line 1" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();
        

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 26
	// Existence of a file :  File not exists
	@Test
	public void txtedTest26() throws Exception {
        String expected = USAGE_ERROR + System.lineSeparator();
        String[] args = {"unlikelyFilename.txt"};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
	}

	// Frame #: 27
	// File Size :  Empty file
	@Test
	public void txtedTest27() throws Exception {
        String input = "";
        String expected = "";

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-f", "-i", "-s", "0", "-e", "exclude", "-x", "!", "-r",
        	"-n", "1", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 28
	// Number of occurrences of the e parameter in the file :  No e in file
	@Test
	public void txtedTest28() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "exclude", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 29
	// Number of occurrences of the e parameter in the file :  One e in file
	@Test
	public void txtedTest29() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"Line 2" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "Line", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 30
	// Number of occurrences of the e parameter in the file :  Many e in file
	@Test
	public void txtedTest30() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"Line 2" + System.lineSeparator() +
        	"something else" + System.lineSeparator();
        String expected = "something else" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "Line", "-i", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 31
	// Number of occurrences of the pattern in one line :  One e in line
	@Test
	public void txtedTest31() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "e2", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 32
	// Number of occurrences of the pattern in one line :  Many e in line
	@Test
	public void txtedTest32() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"liiiine 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "ii", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 33
	// Presence of a filename :  Filename not present
	@Test
	public void txtedTest33() throws Exception {
        String expected = USAGE_ERROR + System.lineSeparator();
        String[] args = {"-f"};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
	}

	// Frame #: 34
	// Presence of special characters :  Special characters
	@Test
	public void txtedTest34() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator();

        File inputFile = createInputFile(input, "tmp_File.txt");
        String[] args = {inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 35
	// Presence of spaces :  One filename space
	@Test
	public void txtedTest35() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator();

        File inputFile = createInputFile(input, "tmp file.txt");
        String[] args = {inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 36
	// Presence of spaces :  Many filename space
	@Test
	public void txtedTest36() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator();

        File inputFile = createInputFile(input, "t m p f i l e.txt");
        String[] args = {inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 37
	// What if String[] args is null?
	@Test
	public void txtedTest37() throws Exception {
        String expected = USAGE_ERROR + System.lineSeparator();

        Main.main(null);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
	}
	
	// Frame #: 38
	// What if String[] args is empty?
	@Test
	public void txtedTest38() throws Exception {
        String expected = USAGE_ERROR + System.lineSeparator();

        String[] args = new String[0];
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
	}
	
	// Frame #: 39
	// What if -s is not an integer?
	@Test
	public void txtedTest39() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-s", "not_an_integer", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 40
	// What if -n is not an integer?
	@Test
	public void txtedTest40() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-n", "not_an_integer", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 41
	// What if everything is removed and output to outStream?
	@Test
	public void txtedTest41() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "line", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 42
	// What if everything is removed and output to file?
	@Test
	public void txtedTest42() throws Exception {
        String input = "line 1" + System.lineSeparator();
        String expected = System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-f", "-e", "line", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file differs from expected", expected, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 43
	// What if parameter after f?
	@Test
	public void txtedTest43() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-f", "param", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 44
	// What if parameter after i?
	@Test
	public void txtedTest44() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "param", "-i", "param", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 45
	// What if parameter after r?
	@Test
	public void txtedTest45() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-r", "param", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 46
	// What if invalid e parameter format?
	@Test
	public void txtedTest46() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", "-e", "-e", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 47
	// What if invalid s parameter format?
	@Test
	public void txtedTest47() throws Exception {
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-s", "-s", "0", inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	
	// Frame #: 48
	// One r with file
	@Test
	public void txtedTest48() throws Exception {
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator();
        String expected = "line 3" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 1" + System.lineSeparator();

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-r", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}

	// Frame #: 49
	// What if String[] args only contains empty string
	@Test
	public void txtedTest49() throws Exception {
        String expected = USAGE_ERROR + System.lineSeparator();

        String[] args = {""};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
	}
	
	// Frame #: 50
	//using all options
	@Test
	public void txtedTest50() throws Exception {
	    String input = "a" + System.lineSeparator() + 	//1
	    	"b" + System.lineSeparator() + 				//2
	    	"c" + System.lineSeparator() + 				//3
	    	"d" + System.lineSeparator() + 				//4
	    	"E" + System.lineSeparator() + 				//5
	    	"f" + System.lineSeparator();  				//6
        String expected = "0000001 c!!" + System.lineSeparator() +
        	"0000002 a!!" + System.lineSeparator(); 

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-x", "!!", "-s", "0", "-r", "-n", "7", "-i", "-f", "-e", "e", 
        	inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file differs from expected", expected, getFileContent(inputFile.getPath()));
	}
	
/*
	// Frame #: 52
	@Test
	public void txtedTest52() throws Exception {
	}

	// Frame #: 54
	// empty file 2 of 2
	@Test
	public void txtedTest54() throws Exception {
	    String input = "";
        String expected = ""; 

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));
	}
	*/
/*

	// -e " "
	    String input = "line 1" + System.lineSeparator();
        String expected = System.lineSeparator() ; 
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-e", " ", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));

	// -x " "
	    String input = "line 1" + System.lineSeparator();
        String expected = "line 1 " + System.lineSeparator() ; 
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-x", " ", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));

	// empty file 1 of 2
	    String input = System.lineSeparator();
        String expected = System.lineSeparator(); 
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));

	// -f -s 1
        String input = "line 1" + System.lineSeparator() +
                "line 2" + System.lineSeparator() +
                "line 3" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String expected = "line 2" + System.lineSeparator() +
                "line 4" + System.lineSeparator();
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-s", "1", "-f", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file differs from expected", expected, getFileContent(inputFile.getPath()));

		// -e larger string than file content, output to file
	    String input = "a" + System.lineSeparator();
        String expected = "0000001 a!!" + System.lineSeparator() ; 
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"-f", "-n", "7", "-s", "0", "-x", "!!", "-r", "-e", 
        	"0000001abc0000001abc0000001abc0000001abc!!", "-i", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file differs from expected", expected, getFileContent(inputFile.getPath()));

	// What if -0 s parameter?
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
		//String expected = USAGE_ERROR + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
	    //Should fail but doesn't:
        String[] args = {"-s", "-0", inputFile.getPath()};
        Main.main(args);
		//assertEquals("Err differs from expected", expected, errStream.toString());
		//assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));

	// What if String[] args contains empty and non-empty strings
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
        String[] args = {"",inputFile.getPath()};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));

	// What if -0 n parameter?
        String input = "line 1" + System.lineSeparator() +
        	"line 2" + System.lineSeparator() +
        	"line 3" + System.lineSeparator();
		//String expected = USAGE_ERROR + System.lineSeparator();
        String expected = " line 1" + System.lineSeparator() +
        	" line 2" + System.lineSeparator() +
        	" line 3" + System.lineSeparator();
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + ".txt");
	    //Should fail but doesn't:
        String[] args = {"-n", "-0", inputFile.getPath()};
        Main.main(args);
		//assertEquals("Err differs from expected", expected, errStream.toString());
		//assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertEquals("Output differs from expected", expected, outStream.toString());
        assertEquals("input file modified", input, getFileContent(inputFile.getPath()));

	//-f with characters outside UTF8
        String input = "line 1" + System.lineSeparator();
        String expected = "line 1" + System.lineSeparator();
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        File inputFile = createInputFile(input, name + "€…™.txt");
        String[] args = {"-f", inputFile.getPath()};
        Main.main(args);
        assertTrue("Unexpected stderr output", errStream.toString().isEmpty());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
        assertEquals("input file differs from expected", expected, getFileContent(inputFile.getPath()));

	// filename too big
	    String input = System.lineSeparator();
        String expected = USAGE_ERROR + System.lineSeparator();		
		//Some OSs can't handle a filename that is 300 chars
        String filename = "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        String[] args = {filename};
        Main.main(args);
        assertEquals("Err differs from expected", expected, errStream.toString());
        assertTrue("Unexpected stdout output", outStream.toString().isEmpty());
	*/

}