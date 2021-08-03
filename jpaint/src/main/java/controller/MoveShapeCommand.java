package controller;

import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IApplicationState;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class MoveShapeCommand implements ICommand, IUndoable{
    private ShapeList shapeList;
    private IApplicationState appState;
    private ShapeInfo shapeInfo;
    private ArrayList<IShape> tempMovedShapes;
    private IShape modshape;
    private PaintCanvasBase canvas;
    double deltax, deltay;

    public MoveShapeCommand(ShapeList shapeList, IApplicationState appstate, ShapeInfo shapeInfo, PaintCanvasBase canvas){
        this.shapeList = shapeList;
        this.appState = appstate;
        this.shapeInfo = shapeInfo;
        this.canvas = canvas;
    }

    public void run(){
        tempMovedShapes = new ArrayList<IShape>();
        Point start = appState.getStart();
        Point end = appState.getEnd();
        deltax = end.getX() - start.getX();
        deltay = end.getY() - start.getY();
        for(IShape shape : shapeList.getSelectList()){
            shapeList.removeShape(shape);
            modshape = shape;
            modshape.modXYCoords(deltax, deltay);
            tempMovedShapes.add(modshape);
            shapeList.addShape(modshape);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for(IShape shape : tempMovedShapes){
            shapeList.removeShape(shape);
            shape.modXYCoords(-deltax, -deltay);
            shapeList.addShape(shape);
        }
        canvas.repaint();
    }

    @Override
    public void redo() {
        for(IShape shape : tempMovedShapes){
            shapeList.removeShape(shape);
            shape.modXYCoords(deltax, deltay);
            shapeList.addShape(shape);
        }
        canvas.repaint();
    }
}
