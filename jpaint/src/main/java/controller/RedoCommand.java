package controller;

import model.ShapeList;
import view.interfaces.PaintCanvasBase;

public class RedoCommand implements ICommand{
    private ShapeList shapelist;
    private PaintCanvasBase paintcanvas;
    public RedoCommand(ShapeList shapelist, PaintCanvasBase paintcanvas){
        this.shapelist = shapelist;
        this.paintcanvas = paintcanvas;
    }
    public void run(){
        CommandHistory.redo();
    }

}
