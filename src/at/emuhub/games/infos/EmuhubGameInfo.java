package at.emuhub.games.infos;

public class EmuhubGameInfo {

    private final String name;
    private final String romName;

    EmuhubGameInfo(String name, String romName) {
        this.name = name;
        this.romName = romName;
    }

    public String getName() {
        return name;
    }

    public String getRomName() {
        return romName;
    }
}