package td_game.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The TileFactory class manages the creation of Tile objects based on character symbols.
 * It uses a registry of suppliers to instantiate tiles dynamically.
 * This allows for decoupling the map parsing logic from specific Tile implementations.
 */
public class TileFactory {
    private final Map<Character, Supplier<Tile>> registry = new HashMap<>();

    /**
     * Constructs a new TileFactory and initializes the default tile registry.
     */
    public TileFactory(){
        registerTiles();
    }

    /**
     * Registers the default set of tiles available in the game.
     * Maps specific characters (e.g., '1', '2', '0') to their corresponding Tile types.
     */
    private void registerTiles(){
        register('1', () -> new Tile("Grass"));
        register('2', () -> new Tile("Water"));
        register('0', ()-> new Tile("Path"));

    }

    /**
     * Registers a new tile type associated with a specific symbol.
     *
     * @param symbol The character symbol used to represent the tile in map data.
     * @param tile   A Supplier that creates new instances of the specified Tile.
     */
    public void register(char symbol, Supplier<Tile> tile){
        registry.put(symbol,tile);
    }

    /**
     * Creates a new Tile instance corresponding to the provided symbol.
     *
     * @param symbol The character symbol looking up the tile type.
     * @return A new instance of the Tile associated with the symbol.
     * @throws IllegalArgumentException If the provided symbol is not found in the registry.
     */
    public Tile create(char symbol){
        Supplier<Tile> supplier = registry.get(symbol);
        if(supplier ==null){
            throw new IllegalArgumentException("Unkown tile symbol");
        }
        return supplier.get();
    }

}