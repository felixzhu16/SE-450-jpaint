package controller;

public class RedoCommand implements ICommand{
    public void run(){
        CommandHistory.redo();
        UndoAndRedoCommand.redo();
    }
}
