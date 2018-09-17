package at.emuhub.games;

import at.emuhub.core.VisibleForTesting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EmuhubGames {

    private final ObservableList<EmuhubGame> games;

    @VisibleForTesting
    EmuhubGames(List<EmuhubGame> games) {
        this.games = FXCollections.observableList(games);
    }

    public ObservableList<EmuhubGame> observableList() {
        return games;
    }
}