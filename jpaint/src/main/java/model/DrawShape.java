package model;

import model.Shapes.Ellipse;
import model.Shapes.Rectangle;
import model.Shapes.Triangle;
import model.interfaces.IShape;

import java.awt.*;

public class DrawShape implements IShape {
    ShapeInfo shapeinfo;
    Point fixedstart, fixedend;

    public DrawShape(ShapeInfo shapeinfo, Point start, Point end){
        this.shapeinfo = shapeinfo;
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
    public ShapeInfo getShapeInfo() {
        return shapeinfo;
    }

    @Override
    public void setShapeInfo(ShapeInfo shapeinfo) {
        this.shapeinfo = shapeinfo;
    }

}
