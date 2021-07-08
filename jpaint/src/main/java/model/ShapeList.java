package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeList {
    public ArrayList<IShape> shapes;
    public ArrayList<IShape> removedShapes;

    public ShapeList() {
        this.shapes = new ArrayList<IShape>();
        this.removedShapes = new ArrayList<IShape>();
    }

    public void removeLast(){
        removedShapes.add(shapes.get(shapes.size()-1));
        shapes.remove(shapes.get(shapes.size()-1));
    }

    public void addLast(){
        shapes.add(removedShapes.get(removedShapes.size()-1));
        removedShapes.remove(removedShapes.get(removedShapes.size()-1));
    }

    public void addList(IShape shape) {
        shapes.add(shape);
    }

    public void giveSList(ArrayList<IShape> list){
        shapes = list;
    }
    public void giveRList(ArrayList<IShape> list){
        removedShapes = list;
    }


    public ArrayList<IShape> getCurrList(){
        return shapes;
    }
    public ArrayList<IShape> getRemList(){
        return removedShapes;
    }
}
