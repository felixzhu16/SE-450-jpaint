package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeList {
    public ArrayList<IShape> shapes;
    public ArrayList<IShape> removedShapes;
    public ArrayList<IShape> selectedShapes;
    public int selectedShapeCount;
    public ArrayList<IShape> movedShapes;


    public ShapeList() {
        this.shapes = new ArrayList<IShape>();
        this.removedShapes = new ArrayList<IShape>();
        this.selectedShapes = new ArrayList<IShape>();
        this.movedShapes = new ArrayList<IShape>();
    }

    public void setSelectShapeCount(int count){
        this.selectedShapeCount = count;
    }

    public int getSShapeCount(){
        return selectedShapeCount;
    }

    public void removeLast(){
        removedShapes.add(shapes.get(shapes.size()-1));
        shapes.remove(shapes.get(shapes.size()-1));
    }

    public void addLast(){
        shapes.add(removedShapes.get(removedShapes.size()-1));
        removedShapes.remove(removedShapes.get(removedShapes.size()-1));
    }

    public void removeShape(IShape shape){
        removedShapes.add(shape);
        shapes.remove(shape);
    }
    public void addShape(IShape shape){
        shapes.add(shape);
        removedShapes.remove(shape);
    }

    public void addSelectedShape(IShape shape){
        selectedShapes.add(shape);
    }
    public void emptySelectedList(){
        selectedShapes.clear();
    }

    public void moveShapetoMovedShapes(IShape shape){
        shapes.remove(shape);
        movedShapes.add(shape);
    }

    public void movedShapestoSList(IShape shape){
        movedShapes.remove(shape);
        shapes.add(shape);
    }

    public void giveSList(ArrayList<IShape> list){
        shapes = list;
    }

    public int size(){
        return shapes.size();
    }
    public ArrayList<IShape> getCurrList(){
        return shapes;
    }
    public ArrayList<IShape> getSelectList(){return selectedShapes;}
    public ArrayList<IShape> getMovedShapes(){return movedShapes;}
}
