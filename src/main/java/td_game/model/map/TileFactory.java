package td_game.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TileFactory {
    private final Map<Character, Supplier<TileBase>> registry = new HashMap<>();

    public TileFactory(){
        registerTiles();
    }

    private void registerTiles(){
        register('1', GrassTile::new);
        register('2', WaterTile::new);
        register('0', ()-> new PathTile(PathType.NORMAL));
        register('S', ()-> new PathTile(PathType.START));
        register('E', ()-> new PathTile(PathType.END));
    }

    public void register(char symbol, Supplier<TileBase> tile){
        registry.put(symbol,tile);
    }

    public TileBase create(char symbol){
        Supplier<TileBase> supplier = registry.get(symbol);
        if(supplier ==null){
            throw new IllegalArgumentException("Unkown tile symbol");
        }
        return supplier.get();
    }

}
