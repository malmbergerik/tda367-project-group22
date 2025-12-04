package td_game.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TileFactory {
    private final Map<Character, Supplier<Tile>> registry = new HashMap<>();

    public TileFactory(){
        registerTiles();
    }

    private void registerTiles(){
        register('1', () -> new Tile("Grass"));
        register('2', () -> new Tile("Water"));
        register('0', ()-> new Tile("Path"));

    }

    public void register(char symbol, Supplier<Tile> tile){
        registry.put(symbol,tile);
    }

    public Tile create(char symbol){
        Supplier<Tile> supplier = registry.get(symbol);
        if(supplier ==null){
            throw new IllegalArgumentException("Unkown tile symbol");
        }
        return supplier.get();
    }

}
