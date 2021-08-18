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
    private ArrayList<IShape> tempUngrouped;

    public UngroupCommand(ShapeList shapelist, PaintCanvasBase paintcanvas){
        this.shapelist = shapelist;
        this.paintcanvas = paintcanvas;
        this.tempselect = new ArrayList<IShape>();
        this.tempUngrouped = new ArrayList<IShape>();

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
            s.ungroup(shapelist.getCurrList(),shapelist.getSelectList(), tempUngrouped);
            CommandHistory.add(this);
        }
        paintcanvas.repaint();
    }

    @Override
    public void undo() {
        shapelist.getCurrList().removeAll(tempUngrouped);
        shapelist.getSelectList().removeAll(tempUngrouped);
        for(IShape s: tempselect){
            shapelist.add(s);
            shapelist.addSelectedShape(s);
        }
        paintcanvas.repaint();
    }

    @Override
    public void redo() {
        shapelist.getCurrList().removeAll(tempselect);
        shapelist.getSelectList().removeAll(tempselect);
        for(IShape s : tempUngrouped){
            shapelist.add(s);
            shapelist.addSelectedShape(s);
        }
        paintcanvas.repaint();
    }
}
