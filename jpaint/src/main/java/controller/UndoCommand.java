package controller;

import model.ShapeList;
import view.interfaces.PaintCanvasBase;

public class UndoCommand implements ICommand {
    private ShapeList shapelist;
    private PaintCanvasBase paintcanvas;

    public UndoCommand(ShapeList shapelist, PaintCanvasBase paintcanvas) {
        this.shapelist = shapelist;
        this.paintcanvas = paintcanvas;
    }

    public void run() {
        CommandHistory.undo();
    }
}