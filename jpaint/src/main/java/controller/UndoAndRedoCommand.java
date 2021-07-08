package controller;

import model.ShapeList;
import model.interfaces.IShape;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class UndoAndRedoCommand {
    private static ArrayList<IShape> shapeList = new ArrayList<>();
    private static ArrayList<IShape> removedList = new ArrayList<>();
    private static PaintCanvasBase paintcanvas = new PaintCanvas();
    private static Graphics2D g;
    public static void undo(){
        shapeList = ShapeList.getCurrList();
        removedList = ShapeList.getRemList();
        g = ShapeList.getG();
        if(!shapeList.isEmpty()){ //We only have something to undo if we have drawn already
            System.out.println("UNDO COMMAND TEST");
            //Adding the removed shape to the removed list
            removedList.add(shapeList.get(shapeList.size()-1));
            //Removing the removed shape from the shape list
            shapeList.remove(shapeList.size() - 1);
            paintcanvas.repaint();
        }

    }

    public static void redo(){
        shapeList = ShapeList.getCurrList();
        removedList = ShapeList.getRemList();
        g = ShapeList.getG();
        if(!removedList.isEmpty()){ //If we haven't undone anything this would be empty/nothing to redo
            System.out.println("ReDO COMMAND TEST");
            shapeList.add(removedList.get(removedList.size()-1));
            removedList.remove(removedList.size() - 1);
            paintcanvas.repaint();
        }
    }
}
