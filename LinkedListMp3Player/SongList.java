import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import java.util.Scanner; 
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.util.Random;

/**
@author Shyam Sudhakaran
SongList class
creates songlist object
Has methods to extract artist and song name information, and displays a menu
Uses jlayer to play the song as well
**/
public class SongList {
	private ArrayList<String> other;

	public SongList(ArrayList<String> other) {
		this.other = other;
	}

	public HashMap<String,String> constructSongList() { //creates the list of songs by artist and songname
		HashMap<String,String> returnhash = new HashMap<String,String>();
		for(int i = 0; i < other.size(); i++) 
		{
			try 
			{
				AudioFile f = AudioFileIO.read(new File(other.get(i)));
				//get the tag object
				Tag tag = f.getTag();
				//print the various fields from the tag
				if(tag != null) 
				{  //catches null pointer
					String artist = tag.getFirst(FieldKey.ARTIST);
					String title = tag.getFirst(FieldKey.TITLE);
					String complete = artist + " - " + title;
					returnhash.put(complete,other.get(i)); //puts artist-songname as an element in the hashmap and assigns the absolute path to it
				} 
			} 
			//exceptions
			catch(CannotReadException fnf) 
			{
				System.out.println(fnf.getMessage());
			}
			catch(IOException fnf) 
			{
				System.out.println(fnf.getMessage());
			}
			catch(TagException fnf) 
			{
				System.out.println(fnf.getMessage());
			}
			catch(ReadOnlyFileException fnf) 
			{
				System.out.println(fnf.getMessage());
			}
			catch(InvalidAudioFrameException fnf) 
			{
				System.out.println(fnf.getMessage());
			}
		}
		return returnhash;
	}

	public LinkedList sortByArtist(HashMap<String, String> input) {
		LinkedList returnVal = new  LinkedList();
		ArrayList<String> newstring = new ArrayList<String>();
		for(String key : input.keySet()) 
		{
			newstring.add(key);
			 //adds each value to arraylist
		}
		Collections.sort(newstring);
		for(int i = 0; i < newstring.size(); i++) {
			returnVal.addAtTail(newstring.get(i));
		}
		 //sorts the arraylist
		return returnVal;
	}

	public LinkedList sortBySongName(HashMap<String, String> input) {
		LinkedList test = new LinkedList();
		ArrayList<String> returnVal = new ArrayList<String>();
		for(String key : input.keySet()) 
		{
			String songname = key;
			String[] songarray = songname.split(" - "); //splits string
			String addvalue = songarray[1]; //gets song name
			returnVal.add(addvalue);
		}
		 //sorts
		Collections.sort(returnVal);
		for(int i = 0; i < returnVal.size(); i++) //while not at end of list
		{
			String name = returnVal.get(i);
			for(String key : input.keySet()) 
			{
				String songname = key;
				String[] songarray = songname.split(" - ");
				String compare = songarray[1];
				if(name.equals(compare)) 
				{
					test.addAtTail(key); //adds associated artist and song name from hashmap
				}
			}
		}
		return test;
	}

/**
Menu
Prompts user for number between 1 and 5,
After every number except 5, the menu is printed again
**/

	public void menu(HashMap<String, String> input, String breakline, LinkedList sort, Player player, String songname, boolean isplaying) {
		//menu items
		int inputchoice = 0;
			while(inputchoice != 5) 
			{
			
			System.out.println("---------------------------");
			if(!songname.equals("") && (player.isComplete() == false))  //checks if a song is Playing
			{
				
				System.out.println("Now Playing: " + songname);
				System.out.println("---------------------------");
			} 
			System.out.println("List Songs by Song Title - 1");	
			System.out.println("List Songs by Artist - 2");
			System.out.println("Play Song - 3");
			System.out.println("Stop Song - 4");
			System.out.println("Exit - 5");
			System.out.println(breakline);
			System.out.println("(Artist - Name of Song)");
			System.out.println(sort.toString());
			System.out.println("--------Enter a number between 1 & 5---------");
			Scanner in = new Scanner(System.in);
				try 
				{
					int choice = in.nextInt();
					//if not a valid number
					if(choice != (int)choice || choice < 1 || choice > 5) 
					{
						System.out.println("---------------------------");
						System.out.println("Enter a number between 1 & 5");
					}
				if (choice == 1) 
				{
					breakline = "-----Songs Sorted by Song Title-----";
					sort = sortBySongName(input); //sorts songs by title
				}
				if (choice == 2) 
				{
					breakline = "-----Songs Sorted by Artist-----";
					sort = sortByArtist(input); //sorts songs by artist
				}
				if(choice == 100) 
				{
					System.out.println("Enter a valid number");
				}
				if (choice == 3) 
				{
					isplaying = true;
					System.out.println("------------");
					System.out.println("Pick a Song:"); //new menu for songs to play
					System.out.println("------------");
					Node cur = sort.getHead();
					int count = 0;
					while(cur != null) //while not at end of list
					{
						System.out.println(cur.getData() + " - " + "[" + (count+1) + "]"); //assigns values
						cur = cur.getNext();
						count++;
					}

					System.out.println("--------Enter a number between 1 & " + sort.size() + "---------");
					Scanner in2 = new Scanner(System.in);
					int choicex = in2.nextInt(); //takes in an integer
					if(choicex > 0 && choicex <= sort.size()) 
					{
						String songchoice = sort.get(choicex); //song choice from menu
						String path = "";
						for(String key : input.keySet()) 
						{
							if(songchoice.equals(key)) 
							{
								path = input.get(key); //gets the associated path from hashmap
							}
						}
						if(player != null) //new song is picked so we must close song that is playing
						{
							player.close();
						}
						try 
						{
							player = new Player(new FileInputStream(path));	//new player
							final Player play = player; //must copy to final object because local variables cannot be accessed within the thread unless final
							Thread t = new Thread() //new threads for player
							{
					   			public void run() 
					   			{
					        		try 
					        		{
					        	   		play.play(); //plays
					    	    	} catch(Exception e) {
						            	e.printStackTrace();
						        	}
						    	}
							};           		
							//start the thread
							//only when start is called does the run method of the thread get called
							songname = songchoice;
							t.start();
						} catch(Exception e) {
					 		e.printStackTrace();
						} 
					}
					else 
					{
						System.out.println("Enter a valid number");	
					}
				}

				if(choice == 4) 
				{
					if(player!= null) //if a song is playing
					{
						player.close(); //close song
						player = null; //assing null to player
						songname = ""; //no song name
						isplaying = false;
					} 

					else 
					{
						System.out.println("---------------------------");
						System.out.println("Play a Song First!");
					}
				}
				if(choice == 5) 
				{
					if(player!= null) //if a song is playing
					{
						player.close();
					}
					isplaying = false;
					inputchoice = 5;
				}
			} 
			catch(Exception e) {
				//if exception
				System.out.println("---------------------------");
				System.out.println("Enter a valid number");	
			}
		}
	}
}