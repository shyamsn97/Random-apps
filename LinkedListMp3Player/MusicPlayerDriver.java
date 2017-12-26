import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javazoom.jl.player.Player;
/**
@author Shyam Sudhakaran
Driver for SongList
takes in a directory from the user and uses methods in songlist to display a menu
**/
public class MusicPlayerDriver {
	public static void main(String[] args) {
		if((args.length != 1)) 
		{
			System.out.println("Enter a valid directory");
			System.exit(1);
		}
		String directory = args[0];
		FileFinder file = new FileFinder();
		ArrayList<String> newresult = file.findFiles(directory); //arraylist of paths for mp3 files
		SongList newlist = new SongList(newresult); //song list
		Player player = null; //initializes the player
		HashMap<String,String> result = newlist.constructSongList(); //hashmap with song names and paths
		String songname = ""; //initializes song names that are displayed
		LinkedList stringlist = new LinkedList(); //songs
		for(String key : result.keySet()) 
		{
			stringlist.addAtHead(key);
		}
		newlist.menu(result, "-----Songs-----",stringlist,player,songname,false); //calls the menu
	}
}