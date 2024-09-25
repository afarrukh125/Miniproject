package me.afarrukh.miniproject.utils;

import java.io.*;
import java.util.Random;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.tiles.Tile;

/**
 *
 * @author Abdullah
 * A file of helper functions which can help us in our game
 */
public class UtilityTasks {

    public static String loadFileAsString(InputStream mapPath) {
        StringBuilder builder = new StringBuilder(); // Add characters to string easily using stringbuilder

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mapPath));
            String line;

            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString(); // Returns our built string
    }

    public static void generateRandomMap() {
        int mapNum = getLatestMapIndexAndUpdate();
        try {
            String path = "res/maps/map" + mapNum + ".map";
            PrintWriter mapWriter = new PrintWriter(new FileWriter(path));

            /*We first want to specify the dimensions of the map as well as the spawnpoint
            and the coordinates where the player spawns.*/
            mapWriter.println(
                    Constants.MAP_WIDTH + " " + Constants.MAP_HEIGHT); // Defining the dimensions in tile coordinates
            mapWriter.println((Tile.TILEWIDTH * Constants.MAP_WIDTH) / 2 + " "
                    + (Tile.TILEHEIGHT * Constants.MAP_HEIGHT) / 2); // Defining spawnpoint in pixels

            for (int x = 0; x < Constants.MAP_HEIGHT; x++) { // There are as many rows as the map height
                StringBuilder builder = new StringBuilder();
                String content = "";
                for (int y = 0; y < Constants.MAP_WIDTH; y++) { // There are as many columns as the map width
                    if (x == 0) builder.append(content).append(1).append(" ");
                    else if (y == 0) builder.append(content).append(1).append(" ");
                    else if (x == Constants.MAP_WIDTH - 1)
                        builder.append(content).append(1).append(" ");
                    else if (y == Constants.MAP_HEIGHT - 1)
                        builder.append(content).append(1).append(" ");
                    else {
                        Random random = new Random();
                        int deciderForGrass = random.nextInt(100);

                        if (deciderForGrass > Constants.GRASS_PERCENTAGE) {
                            int rng = random.nextInt(Constants.numTiles);
                            builder.append(content).append(rng).append(" ");
                        } else {
                            builder.append(content).append(0).append(" ");
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
                    new PrintWriter(new FileWriter("res/maps/numMaps.txt", false)); // Set to false to overwrite

            numFile.println(increment);
            numFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }

    public static int getLatestMapIndex() {
        try {
            var mapsResource = UtilityTasks.class.getResourceAsStream("/maps/numMaps.txt");
            BufferedReader mapCountReader = new BufferedReader(new InputStreamReader(mapsResource));

            int count = parseInt(mapCountReader.readLine());
            mapCountReader.close();
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
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
