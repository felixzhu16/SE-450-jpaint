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

public class DrawShapeCommand implements ICommand, IUndoable{
    private Point start;
    private Point end;
    private ShapeType shapetype;
    private Graphics2D g;
    private ShapeColor primColor, secColor;
    private IShape shape;
    private ShapeInfo shapeInfo;
    private ShapeList shapelist;
    private PaintCanvasBase canvas;
    private ShapeShadingType shading;

    public DrawShapeCommand(Graphics2D g, IApplicationState iappstate, PaintCanvasBase canvas, ShapeInfo shapeInfo, ShapeList shapelist){
        this.start = iappstate.getStart();
        this.end = iappstate.getEnd();
        this.shapetype = iappstate.getActiveShapeType();
        this.primColor = iappstate.getActivePrimaryColor();
        this.secColor = iappstate.getActiveSecondaryColor();
        this.shading = iappstate.getActiveShapeShadingType();
        this.g = g;
        this.shapeInfo = shapeInfo;
        this.shapelist = shapelist;
        this.canvas = canvas;
    }

    public void run(){
        this.shape = new DrawShape(shapeInfo.getFixedStart(), shapeInfo.getFixedEnd(), primColor, secColor, shapetype, shading);
        shape.draw(g);
        shapelist.addShape(shape);
        CommandHistory.add(this);
    }

    public IShape getShape(){
        return shape;
    }

    @Override
    public void undo() {
        shapelist.removeLast();
        canvas.repaint();
    }

    @Override
    public void redo() {
        shapelist.addLast();
        canvas.repaint();
    }
}
