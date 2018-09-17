package at.emuhub.games.screen;

import at.emuhub.games.EmuhubGame;
import at.emuhub.games.infos.EmuhubGameInfo;
import javafx.scene.control.ListCell;

import java.util.Optional;

class EmuhubGamesScreenListCell extends ListCell<EmuhubGame> {

    @Override
    protected void updateItem(EmuhubGame game, boolean empty) {
        super.updateItem(game, empty);
        if (!empty && game != null) {
            Optional<EmuhubGameInfo> gameInfo = game.getGameInfo();
            if (gameInfo.isPresent()) {
                setText(gameInfo.get().getName());
            } else {
                setText(game.getRomPath().getFileName().toString());
            }
            return;
        }
        setText(null);
    }
}