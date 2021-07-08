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

    public DrawShapeCommand(Point start, Point end, Graphics2D g, IApplicationState iappstate, PaintCanvasBase canvas){
        this.start = start;
        this.end = end;
        this.shapetype = iappstate.getActiveShapeType();
        this.color = iappstate.getActivePrimaryColor();
        this.g = g;
    }

    public void run(){
        ShapeFactory shapeFact = new ShapeFactory();
        this.shape = shapeFact.drawShape(shapetype,start,end,color);
    }
    public IShape returnshape(){
        return shape;
    }
}
