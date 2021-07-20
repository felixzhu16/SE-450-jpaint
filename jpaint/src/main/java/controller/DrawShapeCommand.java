package controller;

import controller.IUndoable;
import model.interfaces.IApplicationState;
import controller.MouseListener;
import model.*;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class DrawShapeCommand{
    private Point start;
    private Point end;
    private ShapeType shapetype;
    private Graphics2D g;
    private ShapeColor color;
    private IShape shape;
    private ShapeInfo shapeInfo;

    public DrawShapeCommand(Graphics2D g, IApplicationState iappstate, PaintCanvasBase canvas, ShapeInfo shapeInfo){
        this.start = iappstate.getStart();
        this.end = iappstate.getEnd();
        this.shapetype = iappstate.getActiveShapeType();
        this.color = iappstate.getActivePrimaryColor();
        this.g = g;
        this.shapeInfo = shapeInfo;
    }

    public void run(){
        ShapeFactory shapeFact = new ShapeFactory();
        this.shape = shapeFact.drawShape(shapeInfo);
    }
    public IShape returnshape(){
        return shape;
    }
}
