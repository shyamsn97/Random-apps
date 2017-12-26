import java.io.File;
import java.util.ArrayList;

/**
@author Shyam Sudhakaran
FileFinder takes input a directory
recursively checks in each subdirectory and checks each java file, puts it in an arraylist
returns arraylist
**/
public class FileFinder {

	public static ArrayList<String> findFiles(String directory) {
		//create arraylist
		ArrayList<String> result = new ArrayList<String>();
		findFiles(new File(directory), result);
		return result;
	}

	private static void findFiles(File input, ArrayList<String> result) {

		if(input.isFile() && input.getName().endsWith(".mp3")) 
		{
			String path = input.getAbsolutePath();
			result.add(path);  //adds to path
		}

		else if(input.isDirectory()) 
		{
			//put files into array of files
			File[] children = input.listFiles();
			for(int i = 0; i < children.length; i++)  
			{
				findFiles(children[i], result);
			}
		}
	}
}
