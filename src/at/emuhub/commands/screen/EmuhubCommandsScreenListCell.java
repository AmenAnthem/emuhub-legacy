package at.emuhub.commands.screen;

import at.emuhub.commands.EmuhubCommand;
import javafx.scene.control.ListCell;

class EmuhubCommandsScreenListCell extends ListCell<EmuhubCommand> {

    @Override
    protected void updateItem(EmuhubCommand command, boolean empty) {
        super.updateItem(command, empty);
        if (!empty && command != null) {
            setText(command.getName());
            return;
        }
        setText(null);
    }
}