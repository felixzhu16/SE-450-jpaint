package model;

import model.Shapes.Ellipse;
import model.Shapes.Rectangle;
import model.Shapes.Triangle;
import model.interfaces.IShape;

import java.awt.*;

public class ShapeFactory {
    public IShape drawShape(ShapeInfo shapeInfo){
        ShapeType shapeType = shapeInfo.getShapeType();
        IShape shape = null;
        if(shapeType.equals(ShapeType.RECTANGLE)){
            shape = new Rectangle(shapeInfo);
        } else if(shapeType.equals(ShapeType.ELLIPSE)){
            shape = new Ellipse(shapeInfo);
        } else if (shapeType.equals(ShapeType.TRIANGLE)){
            shape = new Triangle(shapeInfo);
        }
        return shape;
    }
}
