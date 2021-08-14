package model;

import model.Shapes.Ellipse;
import model.Shapes.Rectangle;
import model.Shapes.Triangle;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class DrawShape implements IShape {
    ShapeInfo shapeinfo;
    Point fixedstart, fixedend;

    public DrawShape(Point start, Point end, ShapeColor primColor, ShapeColor secColor, ShapeType type, ShapeShadingType shading){
        this.shapeinfo = new ShapeInfo();
        shapeinfo.setFixedEnd(end);
        shapeinfo.setFixedStart(start);
        shapeinfo.setPrimColor(primColor);
        shapeinfo.setSecColor(secColor);
        shapeinfo.setShapeType(type);
        shapeinfo.setShadingType(shading);
        this.fixedstart = start;
        this.fixedend = end;
    }


    @Override
    public void draw(Graphics2D g) {
        ShapeType shapeType = shapeinfo.getShapeType();
        IShape shape = null;
        switch(shapeType.toString()){
            case "RECTANGLE":
                shape = ShapeFactory.drawRectangle(shapeinfo);
                break;
            case "ELLIPSE":
                shape = ShapeFactory.drawEllipse(shapeinfo);
                break;
            case "TRIANGLE":
                shape = ShapeFactory.drawTriangle(shapeinfo);
                break;
        }
        shape.draw(g);
    }


    @Override
    public Point getFixedStart() {
        return fixedstart;
    }

    @Override
    public Point getFixedEnd() {
        return fixedend;
    }


    @Override
    public void setFixedStart(Point x) {
        fixedstart = x;
    }

    @Override
    public void setFixedEnd(Point x) {
        fixedend = x;
    }

    @Override
    public void modXYCoords(double x, double y) {
        fixedstart.setLocation(fixedstart.getX() + x, fixedstart.getY() + y);
        fixedend.setLocation(fixedend.getX() + x, fixedend.getY() + y);
    }

    @Override
    public IShape paste() {
        Point incStart = new Point();
        Point incEnd = new Point();
        incStart.setLocation(getFixedStart().getX() + 20, getFixedStart().getY() + 20);
        incEnd.setLocation(getFixedEnd().getX() + 20, getFixedEnd().getY() + 20);
        IShape cShape = new DrawShape(incStart, incEnd, getShapeInfo().getPrimColor(), getShapeInfo().getSecColor(),
                getShapeInfo().getShapeType(), getShapeInfo().getShadingType());
        return cShape;
    }

    @Override
    public void ungroup(ArrayList<IShape> current, ArrayList<IShape> selected, ArrayList<IShape> temp) {
        current.add(this);
        selected.add(this);
        temp.add(this);
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return shapeinfo;
    }

}
