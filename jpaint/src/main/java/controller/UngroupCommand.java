package controller;

import model.ShapeGroup;
import model.ShapeList;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class UngroupCommand implements ICommand, IUndoable{
    private ShapeList shapelist;
    private PaintCanvasBase paintcanvas;
    private ArrayList<IShape> tempselect;
    private ShapeGroup shapeGroup;

    public UngroupCommand(ShapeList shapelist, PaintCanvasBase paintcanvas){
        this.shapelist = shapelist;
        this.paintcanvas = paintcanvas;
        this.tempselect = new ArrayList<IShape>();
        this.shapeGroup = new ShapeGroup();

    }

    public void copySelected(){
        for(IShape s : shapelist.getSelectList()){
            tempselect.add(s);
        }
    }

    @Override
    public void run() {
        shapelist.getCurrList().removeAll(shapelist.getSelectList());
        copySelected();
        for(IShape s: tempselect){
            if(s instanceof ShapeGroup){
                for(IShape b : ((ShapeGroup) s).getGroup()){
                    shapelist.getCurrList().add(b);
                    shapelist.getSelectList().add(b);
                }
                shapelist.getSelectList().remove(s);
            }
            else{
                shapelist.getCurrList().add(s);
                shapelist.getSelectList().add(s);
            }
        }
        CommandHistory.add(this);
        paintcanvas.repaint();
    }

    @Override
    public void undo() {
        tempselect.clear();
        copySelected();
        shapeGroup.group(tempselect);
        shapelist.getCurrList().removeAll(shapelist.getSelectList());
        shapelist.getCurrList().add(shapeGroup);
        shapelist.getSelectList().clear();
        shapelist.getSelectList().add(shapeGroup);
        paintcanvas.repaint();
    }

    @Override
    public void redo() {
        shapelist.getCurrList().remove(shapeGroup);
        shapelist.getSelectList().remove(shapeGroup);
        for(IShape s : shapeGroup.getGroup()){
            shapelist.getCurrList().add(s);
            shapelist.getSelectList().add(s);
        }
        paintcanvas.repaint();
    }
}
