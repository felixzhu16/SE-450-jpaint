package model;

import model.Shapes.Ellipse;
import model.Shapes.Rectangle;
import model.Shapes.Triangle;
import model.interfaces.IShape;

import java.awt.*;

public class DrawShape implements IShape {
    ShapeInfo shapeinfo;
    Point fixedstart, fixedend, start, end;

    public DrawShape(ShapeInfo shapeinfo, Point start, Point end){
        this.shapeinfo = shapeinfo;
        this.start = start;
        this.end = end;
        this.fixedstart = shapeinfo.getFixedStart();
        this.fixedend = shapeinfo.getFixedEnd();
    }

    @Override
    public void draw(Graphics2D g) {
        ShapeType shapeType = shapeinfo.getShapeType();
        IShape shape = null;
        if(shapeType.equals(ShapeType.RECTANGLE)){
            shape = ShapeFactory.drawRectangle(shapeinfo);
        } else if(shapeType.equals(ShapeType.ELLIPSE)){
            shape = ShapeFactory.drawEllipse(shapeinfo);
        } else if (shapeType.equals(ShapeType.TRIANGLE)){
            shape = ShapeFactory.drawTriangle(shapeinfo);
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
    public void modXYCoords(double x, double y) {
        fixedstart.setLocation(fixedstart.getX() + x, fixedstart.getY() + y);
        fixedend.setLocation(fixedend.getX() + x, fixedend.getY() + y);
    }
}
