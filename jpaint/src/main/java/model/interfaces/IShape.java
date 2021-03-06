package model.interfaces;

import model.*;

import java.awt.*;
import java.util.ArrayList;

public interface IShape {
    void draw(Graphics2D g);
    Point getFixedStart();
    Point getFixedEnd();
    void setFixedStart(Point x);
    void setFixedEnd(Point x);
    void modXYCoords(double x, double y);
    IShape paste();
    void ungroup(ArrayList<IShape> current, ArrayList<IShape> selected, ArrayList<IShape> temp);
    ShapeInfo getShapeInfo();
}
