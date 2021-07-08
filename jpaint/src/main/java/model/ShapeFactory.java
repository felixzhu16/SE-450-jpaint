package model;

import model.ShapeType;
import model.Shapes.Rectangle;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class ShapeFactory {
    public IShape drawShape(ShapeType shapeType, Point start, Point end, ShapeColor color){
        IShape shape = null;
        switch(shapeType){
            case RECTANGLE:
                shape = new Rectangle(start, end, color);


            case ELLIPSE:

            case TRIANGLE:
        }
        return shape;
    }
}
