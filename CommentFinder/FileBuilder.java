/**
Builder for File
Checks if each value in hashmap is a valid java file
Puts invalid absolute paths in a directory
Takes a hashmap of files, checks each file and counts commented lines and non commented lines
puts values in a nested hashmap
@author Shyam Sudhakaran
**/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class FileBuilder {
	//check if each absolute path is valid and returns a hasmap of the valid ones
	/**
	Checks if java file is valid by scanning and finding first instance of public class
	If file is valid, add to the valid hashmap
	**/
	public HashMap<String, Integer> isValid(HashMap<String, Integer> input) {
		HashMap<String, Integer> valid =  new HashMap<String, Integer>();
		for(String key: input.keySet()) 
		{
			int x = 0; //for checking class name vs title
			String name = key; //absolute path
		 	String ending = name.substring(name.lastIndexOf("/")+1, name.lastIndexOf(".")); //class name
			File inputFile = new File(name);
			String testline = "";
			try(Scanner in = new Scanner(inputFile)) 
			{ 
				boolean checkml = false;
				while(in.hasNextLine() && x == 0) 
				{
				String line = in.nextLine();
				line = line.trim();
				boolean check = false;
				//tests if line contains ending name
				
				//if line is a comment or in multi line comment
					if(line.startsWith("//")) 
					{
						check = true;
					}
					else if(line.startsWith("/*") && checkml == false) 
					{
						checkml = true;
					}
					else if(line.endsWith("*/") && checkml == true) 
					{
						checkml = false;
						check = true;
					}

				if(line.contains("class ")) 
				{
					if(check == false && checkml == false) 
					{
					if (line.contains(" extends ") && line.contains("{"))
					{						
						line = line.split("class")[1].substring(1);
						line = line.trim();
						line = line.substring(0,line.lastIndexOf(" extends"));
						line = line.trim();
						if(!line.equals(ending))
						{
							x = 1;
						} 
					}
					else if (line.contains(" implements "))
					{
						line = line.split("class")[1].substring(1);
						line = line.trim();
						line = line.substring(0,line.lastIndexOf(" implements"));
						line = line.trim();
						if(!line.equals(ending)){
							x = 1;
						} 
					}

					else if (line.contains("class ") && line.endsWith("{") && line.contains(ending))
					{
						line = line.split("class")[1].substring(1);
						line = line.trim();
						if(line.contains("{")) 
						{
						line = line.substring(0,line.lastIndexOf("{"));
						}
						line = line.trim();
						if(!line.equals(ending))
						{
							x = 1;
						} 
					}
					}
				}
				}
			} 
			catch(FileNotFoundException fnf) 
			{
		 	System.out.println("File not found.");
		 	System.out.println(fnf.getMessage());
		 	return null;
		 	}
		 	if(x == 0) 
		 	{
		 		valid.put(name, 0);
		 	}
		}
		return valid;
	}

	/**
	Checks if java file is invalid by scanning and finding first instance of public class
	If file is invalid, add to the invalid arraylist
	**/
	public ArrayList<String> inValid(HashMap<String, Integer> input) {
		ArrayList<String> invalid = new ArrayList<String>();
		for(String key: input.keySet()) 
		{
			int x = 0; //for checking class name vs title
			String name = key; //absolute path
		 	String ending = name.substring(name.lastIndexOf("/")+1, name.lastIndexOf(".")); //class name
			File inputFile = new File(name);
			String testline = "";
			try(Scanner in = new Scanner(inputFile)) 
			{ 
				boolean checkml = false;
				while(in.hasNextLine() && x == 0) 
				{
				String line = in.nextLine();
				line = line.trim();
				boolean check = false;
				//tests if line contains ending name
					if(line.startsWith("//")) 
					{
						check = true;
					}
					else if(line.startsWith("/*") && checkml == false) 
					{
						checkml = true;
					}
					else if(line.endsWith("*/") && checkml == true) 
					{
						checkml = false;
						check = true;
					}
					if(line.contains("class ")) 
					{
						if(check == false && checkml == false) 
						{
							if (line.contains(" extends "))
							{						
								line = line.split("class")[1].substring(1);
								line = line.trim();
								line = line.substring(0,line.lastIndexOf(" extends"));
								line = line.trim();
								if(!line.equals(ending)){
									x = 1;
								} 
							}
							else if (line.contains(" implements "))
							{
								line = line.split("class")[1].substring(1);
								line = line.trim();
								line = line.substring(0,line.lastIndexOf(" implements"));
								line = line.trim();
								if(!line.equals(ending)){
									x = 1;
								} 
							}

							else if (line.contains("class ") && line.contains("{") && line.contains(ending))
							{
								line = line.split("class")[1].substring(1);
								line = line.trim();
								if(line.contains("{")) 
								{
									line = line.substring(0,line.lastIndexOf("{"));
								}
								line = line.trim();
								if(!line.equals(ending))
								{
									x = 1;
								} 
							}
						}
					}
			}
				if(x == 1) 
				{
					invalid.add(name);
				}
			}catch(FileNotFoundException fnf) {
		 	System.out.println("File not found.");
		 	System.out.println(fnf.getMessage());
		 	return null;
		 	}

		}
		return invalid;
	}
	/**
	Count number of comments and number of lines excluding comments
	**/
	public FileList buildFileList(HashMap<String, Integer> result) {

		//hashmap within hashmap
		HashMap<String, Integer> total = new HashMap<String, Integer>();
		String xsloc = "SLOC";
		String xexsloc = "SLOC (excluding comments)";
		HashMap<String, HashMap<String, Integer>> newhash = new HashMap<String, HashMap<String, Integer>>();
		int totalcount = 0; //total count
		int totalcomment = 0; //count of comments
		for(String key: result.keySet()) {
			//assigns variables for the count and new hash maps
			int slocw = 0;
			int x = 0;
			int countcomment = 0;
			int count = 0;
			HashMap<String, Integer> slochash = new HashMap<String, Integer>();
			String sloc = "SLOC";
			String exsloc = "SLOC (excluding comments)";
			slochash.put(sloc, count);
			slochash.put(exsloc, count);
			boolean isML = false;
		 	String name = key;
		 	String ending = name.substring(name.lastIndexOf("/")+1, name.lastIndexOf(".")); //class name
		 	String printName = "Class: " + ending + " " + "[" + name + "]"; //output
			File inputFile = new File(name);

			try(Scanner in = new Scanner(inputFile)) { 
				while(in.hasNextLine()) {
					String line = in.nextLine();
					line = line.trim();
					//tests if line starts multicomment
					if (isML == false && line.startsWith("/*")) {
						isML = true;
						countcomment++;
					}
					//if ends with multicomment
					else if ((isML == true && line.endsWith("*/")) || (isML == true && line.startsWith("*/")) ) {
						isML = false;
					}
					//if in multicomment
					else if (isML == true && !line.startsWith("/*") && !line.endsWith("*/")) {
						countcomment++;
					}
					//if comment
					else if(line.startsWith("//")) {
						countcomment++;
					}
					count++; 				
				}

			} 
			catch(FileNotFoundException fnf) {
		 		System.out.println("File not found.");
		 		System.out.println(fnf.getMessage());
		 		return null;
		 	}
			if(x == 0) {

				slocw = count - countcomment - 1; //account for undercount of multiline comments		 
				slochash.put(sloc, count);
				slochash.put(exsloc, slocw);
				//put in hashmap
				newhash.put(printName,slochash);
			}
			totalcount = totalcount + count;
			totalcomment = totalcomment + countcomment;		
		}
			totalcomment = totalcount - totalcomment - newhash.size(); //account for overcounting
			total.put(xsloc, totalcount);
			total.put(xexsloc, totalcomment);
			newhash.put("Total", total);
			//create FileList
			FileList returnfile = new FileList(newhash);
			return returnfile;
	}
}