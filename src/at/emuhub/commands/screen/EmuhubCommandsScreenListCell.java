package at.emuhub.commands.screen;

import at.emuhub.systems.EmuhubSystemCommand;
import javafx.scene.control.ListCell;

class EmuhubCommandsScreenListCell extends ListCell<EmuhubSystemCommand> {

    @Override
    protected void updateItem(EmuhubSystemCommand command, boolean empty) {
        super.updateItem(command, empty);
        if (!empty && command != null) {
            setText(command.getName());
            return;
        }
        setText(null);
    }
}