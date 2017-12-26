import java.io.File;
import java.util.HashMap;

/**
@author Shyam Sudhakaran
FileFinder takes input a directory
checks in each subdirectory and checks each java file, puts it in hashmap
returns hashmap
**/
public class FileFinder {

	public static HashMap<String, Integer> findFiles(String directory) {
		//create map
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		findFiles(new File(directory), result);
		return result;
	}

	private static void findFiles(File input, HashMap<String, Integer> result) {
		
		if(input.isFile() && input.getName().endsWith(".java")) 
		{
			//if not first file seen
			String name = input.getName();
			String path = input.getAbsolutePath();
			String ending = name.substring(name.lastIndexOf(".")+1, name.length()); //ending of file/type of file
			if(result.containsKey(ending)) 
			{
				
				int value = result.get(ending);
				result.put(path, value + 1 ); //puts into map
			} 

			else 
			{
				result.put(path, 1);
			}

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
