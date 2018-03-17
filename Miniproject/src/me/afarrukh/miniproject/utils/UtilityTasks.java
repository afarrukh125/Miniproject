package me.afarrukh.miniproject.utils;

import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.tiles.Tile;

import java.io.*;
import java.util.Random;

/**
 * 
 * @author Abdullah
 * A file of helper functions which can help us in our game
 */

public class UtilityTasks {
	
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder(); //Add characters to string easily using stringbuilder
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = br.readLine())!= null) {
				builder.append(line + "\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString(); //Returns our built string
	}

	public static void generateRandomMap() {
		int mapNum = getLatestMapIndexAndUpdate();
		try {
			String path = "res/maps/map" +mapNum+ ".map";
			PrintWriter mapWriter = new PrintWriter(new FileWriter(path));

			/*We first want to specify the dimensions of the map as well as the spawnpoint
			  and the coordinates where the player spawns.*/
			mapWriter.println(Constants.MAP_WIDTH + " " + Constants.MAP_HEIGHT); //Defining the dimensions in tile coordinates
			mapWriter.println((Tile.TILEWIDTH * Constants.MAP_WIDTH)/2 + " " + (Tile.TILEHEIGHT * Constants.MAP_HEIGHT)/2); //Defining spawnpoint in pixels

			for(int x = 0; x < Constants.MAP_HEIGHT; x++) { //There are as many rows as the map height
				StringBuilder builder = new StringBuilder();
				String content = "";
				for(int y = 0; y < Constants.MAP_WIDTH; y++) { //There are as many columns as the map width
					if(x == 0)
						builder.append(content + 1 + " ");
					else if(y == 0)
						builder.append(content + 1 + " ");
					else if(x == Constants.MAP_WIDTH -1 )
						builder.append(content + 1 + " ");
					else if(y == Constants.MAP_HEIGHT -1)
						builder.append(content + 1 + " ");
					else {
						Random random = new Random();
						int deciderForGrass = random.nextInt(100);

						if(deciderForGrass > Constants.GRASS_PERCENTAGE) {
							int rng = random.nextInt(Constants.numTiles);
							builder.append(content + rng + " ");
						} else {
							builder.append(content + 0 + " ");
						}
					}
				}
				mapWriter.println(builder);
			}
			mapWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method increments a file that stores how many maps are currently in the game. It also returns how many there currently are
	 * before it increments the number in the file.
	 * @return an integer representing the latest free integer that can be used as a map number
	 */
	private static int getLatestMapIndexAndUpdate() {

		int index = getLatestMapIndex();
		int increment = index + 1;

		try {
			PrintWriter numFile =
					new PrintWriter(new FileWriter("res/maps/numMaps.txt", false)); //Set to false to overwrite

			numFile.println(increment);
			numFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return index;
	}

	public static int getLatestMapIndex() {
		try {
			BufferedReader mapCountReader = new BufferedReader(new FileReader("res/maps/numMaps.txt"));

			int count = parseInt(mapCountReader.readLine());
			mapCountReader.close(); //Closing the file as we no longer need it.
			return count;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File could not be read.");
		}
		return -1;
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
