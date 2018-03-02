package me.afarrukh.miniproject.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
