package td_game.view.helper;

/**
 * A class to hold data about towers for view purposes.
 */
public class TowerViewData {

    private final String[][] towerKeys;
    private final int[][] towerRanges;

    public TowerViewData(String[][] tileKeys, int[][] towerRanges){
        this.towerKeys = tileKeys;
        this.towerRanges = towerRanges;

    }

    public String[][] getTowerKeys() {
            return towerKeys;
    }

    public int[][] getTowerRanges() {
        return towerRanges;
    }
}
