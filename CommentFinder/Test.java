import java.io.File;
import java.util.HashMap;

/**
@author Shyam Sudhakaran
FileFinder takes input a directory
checks in each subdirectory and checks each file, assigns to a key and counts the number of files
public class Test {
**/

private class Test { 1
	public static void main(String[] args) {
	String x = "Test";
    String line = "public class FileFinder {";
    line = line.trim();
    line = line.split("class")[1].substring(1);
    line = line.substring(0,line.lastIndexOf(" {"));
    System.out.println(line);
    System.out.println(line.length());
	}

}