package td_game.view.helper;

/**
 * A class to hold data about towers for view purposes.
 */
public class TowerViewData {

    private final String[][] towerKeys;

    public TowerViewData(String[][] tileKeys) {
        this.towerKeys = tileKeys;
    }

    public String[][] getTowerKeys() {
            return towerKeys;
    }

}
