import java.io.File;
import java.util.HashMap;

/**
@author Shyam Sudhakaran
JavaClassAnalyzer
Driver class that takes a console arguement, a directory
**/

public class JavaClassAnalyzer {
	public static void main(String[] args) {
		if((args.length != 1)) 
		{
			System.out.println("Enter a valid directory");
			System.exit(1);
		}
		String directory = args[0];
		FileFinder file = new FileFinder();
		HashMap<String, Integer> result = file.findFiles(directory);
		HashMap<String, Integer> newresult = file.findFiles(directory);
		FileBuilder builder = new FileBuilder();
		//isValid
		newresult = builder.isValid(result);
		FileList newfile = builder.buildFileList(newresult);
		System.out.println(newfile.printString());
		//invalid classes
		if(builder.inValid(result).size() > 0) 
		{
			System.out.println("Invalid Classes:");
			for(int i = 0; i < builder.inValid(result).size(); i++)
			{
				System.out.println(builder.inValid(result).get(i));		 
			}
		}
		else 
		{
			System.out.println("Invalid Classes: None");
		}
	}

}