package controller;

import model.ShapeGroup;
import model.ShapeList;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

public class GroupCommand implements ICommand, IUndoable{
    private ShapeGroup shapeGroup;
    private ShapeList shapeList;
    private PaintCanvasBase paintcanvas;

    public GroupCommand(ShapeList shapelist, PaintCanvasBase paintcanvas){
        this.shapeList = shapelist;
        this.shapeGroup = new ShapeGroup();
        this.paintcanvas = paintcanvas;
    }

    @Override
    public void run() {
        if(!shapeList.getSelectList().isEmpty()){
            shapeGroup.group(shapeList.getSelectList());
            shapeList.getSelectList().clear();
            shapeList.addSelectedShape(shapeGroup);
            //Removing all grouped shapes from the current list
            for(IShape shape: shapeGroup.getGroup()){
                shapeList.remove(shape);
            }
            shapeList.add(shapeGroup);
            CommandHistory.add(this);
            paintcanvas.repaint();
        }
    }

    @Override
    public void undo() {
        shapeList.getSelectList().clear();
        for(IShape s : shapeGroup.getGroup()){
            shapeList.add(s);
            shapeList.addSelectedShape(s);
        }
        shapeList.remove(shapeGroup);
        shapeList.removeSelectedShape(shapeGroup);
        paintcanvas.repaint();
    }

    @Override
    public void redo() {
        shapeList.getSelectList().clear();
        shapeList.addSelectedShape(shapeGroup);
        for(IShape shape: shapeGroup.getGroup()){
            shapeList.remove(shape);
        }
        shapeList.add(shapeGroup);
        paintcanvas.repaint();
    }
}
