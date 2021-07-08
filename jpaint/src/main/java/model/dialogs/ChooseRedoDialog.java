package model.dialogs;

import model.Redo;
import model.Undo;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseRedoDialog implements IDialogChoice<Redo> {
    private final IApplicationState applicationState;
    public ChooseRedoDialog(IApplicationState applicationState){
        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Redo";
    }

    @Override
    public String getDialogText() {
        return "Do you want to Redo the last drawing?";
    }

    @Override
    public Redo[] getDialogOptions() {
        return Redo.values();
    }

    @Override
    public Redo getCurrentSelection() {
        return applicationState.getRedo();
    }

}
