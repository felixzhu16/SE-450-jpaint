package model;

import model.Shapes.Ellipse;
import model.Shapes.Rectangle;
import model.Shapes.Triangle;
import model.interfaces.IShape;

import java.awt.*;

public class ShapeFactory {
    public static IShape drawRectangle(ShapeInfo shapeinfo){
        return new Rectangle(shapeinfo);
    }
    public static IShape drawEllipse(ShapeInfo shapeinfo){
        return new Ellipse(shapeinfo);
    }
    public static IShape drawTriangle(ShapeInfo shapeinfo){
        return new Triangle(shapeinfo);
    }
}
