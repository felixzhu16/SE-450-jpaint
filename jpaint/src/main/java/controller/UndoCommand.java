package controller;

import model.ShapeList;
import view.interfaces.PaintCanvasBase;

public class UndoCommand implements ICommand, IUndoable{
    private ShapeList shapelist;
    private PaintCanvasBase paintcanvas;
    public UndoCommand(ShapeList shapelist, PaintCanvasBase paintcanvas){
        this.shapelist = shapelist;
        this.paintcanvas = paintcanvas;
    }

    public void run(){
        CommandHistory.add(this);
        if(CommandHistory.undo()){
            shapelist.removeLast();
            paintcanvas.repaint();
        }

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {
    }
}
