import java.io.File;
import java.util.HashMap;

/**
@author Shyam Sudhakaran
FileList gets nested hashmap from builder
has print method that print oout total, valid classes, and sloc
**/

//create another HashMap<String, HashMap<String, Integer>
public class FileList {
	private HashMap<String, HashMap<String,Integer>> other;
	public FileList(HashMap<String, HashMap<String,Integer>> other) {
		this.other = other;
	}

	public String printString() {
		String returnVal = "";
		String inner = "";
		System.out.println("Valid Classes - Total: " + (other.size() - 1)); //account for key with total values
		for (String name : other.keySet()) {
			if(!name.equals("Total")) { //print class names and sloc
				returnVal = returnVal + name + "\n";
            	for (String c : other.get(name).keySet()) {
            		if(c.equals("SLOC")) {
            			returnVal = returnVal + "       " + c + ": " + other.get(name).get(c) + "\n"; 
            		}
            	}
            	for (String c : other.get(name).keySet()) {
         			if(c.equals("SLOC (excluding comments)")) {
         				returnVal = returnVal + "       " + c + ": " + other.get(name).get(c) + "\n";
         			}
           		}
        	} 
    	}
        for (String sname : other.keySet()) {
        	if(sname.equals("Total")) { //print total sloc
            	for (String x : other.get(sname).keySet()) {
            		if(x.equals("SLOC")) {
            			returnVal = returnVal + sname + " " + x + ": " + other.get(sname).get(x) + "\n"; 
            		}
            	}
            	for (String x : other.get(sname).keySet()) {
            		if(x.equals("SLOC (excluding comments)")) {
            			returnVal = returnVal + sname + " " + x + ": " + other.get(sname).get(x); 
            		}
            	}
       	    }
        }
    	
		return returnVal;
	}

}