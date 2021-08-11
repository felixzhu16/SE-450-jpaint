package controller;

import model.*;
import model.interfaces.IApplicationState;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class PasteCommand implements ICommand, IUndoable {
    ShapeList shapelist;
    IShape copyShape;
    PaintCanvasBase paintcanvas;
    IApplicationState appstate;
    Graphics2D g;
    ArrayList<IShape> tempPasteList;

    public PasteCommand(ShapeList shapelist, PaintCanvasBase paintcanvas, IApplicationState appstate){
        this.shapelist = shapelist;
        this.paintcanvas = paintcanvas;
        this.appstate = appstate;
        this.g = paintcanvas.getGraphics2D();
        this.tempPasteList = new ArrayList<IShape>();
    }
    @Override
    public void run() {
        for(IShape shape: shapelist.getCopiedList()){
            Point incStart = new Point();
            Point incEnd = new Point();
            incStart.setLocation(shape.getFixedStart().getX() + 20, shape.getFixedStart().getY() + 20);
            incEnd.setLocation(shape.getFixedEnd().getX() + 20, shape.getFixedEnd().getY() + 20);
            copyShape = new DrawShape(incStart, incEnd, shape.getShapeInfo().getPrimColor(), shape.getShapeInfo().getSecColor(),
                        shape.getShapeInfo().getShapeType(), shape.getShapeInfo().getShadingType());
            tempPasteList.add(copyShape);
            shapelist.addShape(copyShape);
        }
        CommandHistory.add(this);
        paintcanvas.repaint();
    }

    @Override
    public void undo() {
        for(IShape t : tempPasteList){
            shapelist.removeShape(t);
        }
        paintcanvas.repaint();
    }

    @Override
    public void redo() {
        for(IShape t : tempPasteList){
            shapelist.addShape(t);
        }
        paintcanvas.repaint();
    }
}
