package controller;

import model.ShapeList;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

public class DeleteCommand implements ICommand, IUndoable{
    ShapeList shapelist;
    PaintCanvasBase paintcanvas;

    public DeleteCommand(ShapeList shapelist, PaintCanvasBase paintcanvas){
        this.shapelist = shapelist;
        this.paintcanvas = paintcanvas;
    }
    @Override
    public void run() {
        System.out.println("DELETE COMMAND RUNNING");
        System.out.println("SIZE OF SHAPELIST: " + shapelist.size());
        for(IShape shape: shapelist.getSelectList()){
            //Adds shape to deletelist and then removes shape from shapelist
            shapelist.deleteShape(shape);
            shapelist.addDeleteShape(shape);
        }
        System.out.println("SIZE OF SHAPELIST AFTER: " + shapelist.size());
        CommandHistory.add(this);
        paintcanvas.repaint();
    }

    @Override
    public void undo() {
        for(IShape shape : shapelist.getDeleteList()){
            shapelist.undoDelete(shape);
        }
        paintcanvas.repaint();

    }

    @Override
    public void redo() {
        for(IShape shape : shapelist.getDeleteList()){
            shapelist.deleteShape(shape);
        }
        paintcanvas.repaint();
    }
}
