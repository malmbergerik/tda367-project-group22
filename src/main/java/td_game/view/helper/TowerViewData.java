package td_game.view.helper;

public class TowerViewData {

    private final String[][] towerKeys;

    public TowerViewData(String[][] tileKeys) {
        this.towerKeys = tileKeys;
    }

    public String[][] getTowerKeys() {
            return towerKeys;
    }

}
