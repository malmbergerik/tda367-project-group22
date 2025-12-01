package td_game.model.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    private final TileFactory tileFactory;

    public MapLoader(TileFactory tileFactory){
        this.tileFactory = tileFactory;
    }

    //Reads file and returns a gridmap
    public  GridMap loadMap (String filename, int tileSize) {
        try (InputStream is = MapLoader.class.getClassLoader().getResourceAsStream(filename)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

                List<String> lines = new ArrayList<String>();
                String line;

                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        lines.add(line.replace(" ", ""));
                    }
                }

                if (lines.isEmpty()) {
                    throw new IllegalArgumentException("The file is empty");
                }

                int height = lines.size();
                int width = lines.get(0).length();
                GridMap map = new GridMap(height, width, tileSize);

                for ( int row = 0; row < height; row++) {
                    String currentLine = lines.get(row);
                    for (int col = 0; col < width; col ++) {
                        char currentChar = currentLine.charAt(col);
                        map.setTile(row, col, tileFactory.create(currentChar));
                    }
                }

                return map;

            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find input file," + filename, e);
        }

    }

}


