package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeList {
    public static ArrayList<IShape> shapes;
    public static ArrayList<IShape> removedShapes;
    private static Graphics2D graphics2D;

    public ShapeList() {
        this.shapes = new ArrayList<IShape>();
        this.removedShapes = new ArrayList<IShape>();
    }

    public static void addList(IShape shape) {
        shapes.add(shape);
    }
    public static void giveSList(ArrayList<IShape> list){
        shapes = list;
    }

    public static void giveRList(ArrayList<IShape> list){
        removedShapes = list;
    }


    public static ArrayList<IShape> getCurrList(){
        return shapes;
    }
    public static ArrayList<IShape> getRemList(){
        return removedShapes;
    }
    public static Graphics2D getG(){return graphics2D;}
    public static void setG(Graphics2D G){
        graphics2D = G;
    }

}
