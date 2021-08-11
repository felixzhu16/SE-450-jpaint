package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeList {
    public ArrayList<IShape> shapes;
    public ArrayList<IShape> removedShapes;
    public ArrayList<IShape> selectedShapes;
    public ArrayList<IShape> movedShapes;
    public ArrayList<IShape> deletedShapes;
    public ArrayList<IShape> copiedShapes;
    public ArrayList<IShape> groupedShapes;


    public ShapeList() {
        this.shapes = new ArrayList<IShape>();
        this.removedShapes = new ArrayList<IShape>();
        this.selectedShapes = new ArrayList<IShape>();
        this.movedShapes = new ArrayList<IShape>();
        this.deletedShapes = new ArrayList<IShape>();
        this.copiedShapes = new ArrayList<IShape>();
        this.groupedShapes = new ArrayList<IShape>();
    }

    //shapes and removed shape list methods

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

    //Selected shape methods
    public void addSelectedShape(IShape shape){
        selectedShapes.add(shape);
    }


    //Delete List methods
    public void addDeleteShape(IShape shape){
        deletedShapes.add(shape);
    }
    public void deleteShape(IShape shape){
        System.out.println("DELETE SHAPE METHOD");
        shapes.remove(shape);
    }

    //Copied List method

    public void addCopyShape(IShape shape){
        copiedShapes.add(shape);
    }

    public void undoDelete(IShape shape){
        shapes.add(shape);
    }


    //Grouped Methods

    public void addGroup(IShape shape){
        groupedShapes.add(shape);
    }

    public int size(){
        return shapes.size();
    }
    public ArrayList<IShape> getDeleteList(){return deletedShapes;}
    public ArrayList<IShape> getCurrList(){
        return shapes;
    }
    public ArrayList<IShape> getSelectList(){return selectedShapes;}
    public ArrayList<IShape> getCopiedList(){return copiedShapes;}
    public ArrayList<IShape> getGroupList(){return groupedShapes;}
}
