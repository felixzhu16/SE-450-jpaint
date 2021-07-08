package controller;

public class UndoCommand implements ICommand{

    public void run(){
        CommandHistory.undo();
        UndoAndRedoCommand.undo();
    }
}
