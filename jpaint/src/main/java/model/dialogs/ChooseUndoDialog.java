package model.dialogs;

import model.ShapeShadingType;
import model.Undo;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseUndoDialog implements IDialogChoice<Undo> {
    private final IApplicationState applicationState;
    public ChooseUndoDialog(IApplicationState applicationState){
        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Undo";
    }

    @Override
    public String getDialogText() {
        return "Do you want to undo the last drawing?";
    }

    @Override
    public Undo[] getDialogOptions() {
        return Undo.values();
    }

    @Override
    public Undo getCurrentSelection() {
        return applicationState.getUndo();
    }
}
