package controller;

import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IApplicationState;
import model.interfaces.IShape;

import java.awt.*;

public class SelectShapeCommand implements ICommand{
    private ShapeList shapeList;
    private IApplicationState appState;
    private ShapeInfo shapeInfo;
    private boolean shapesSelected = false;
    private Point start;
    private Point end;
    private Point fixstart;
    private Point fixend;

    public SelectShapeCommand(ShapeList shapeList, IApplicationState applicationState, ShapeInfo shapeInfo){
        this.shapeList = shapeList;
        this.appState = applicationState;
        this.shapeInfo = shapeInfo;
        this.start = appState.getStart();
        this.end = appState.getEnd();
    }

    public void fixCollisionStart(){
        int x = (int) Math.min(start.getX(),end.getX());
        int y = (int) Math.min(start.getY(),end.getY());
        this.fixstart = new Point(x,y);
    }

    public void fixCollisionEnd(){
        int x = (int) Math.max(start.getX(),end.getX());
        int y = (int) Math.max(start.getY(),end.getY());
        this.fixend = new Point(x,y);
    }

    public boolean hasPoint(Point shapestart, Point shapeend) {
        if(!(shapeend.getX() <= fixstart.getX() || shapeend.getY() <= fixstart.getY() || shapestart.getX() >= fixend.getX() || shapestart.getY() >= fixend.getY())){
            System.out.println("THERE IS A COLLISION");
            return true;
        }
        System.out.println("THERE IS NO TOUCHING");
        return false;
    }

    @Override
    public void run() {
        shapeList.getSelectList().clear();
        fixCollisionStart();
        fixCollisionEnd();
        for(IShape shape : shapeList.getCurrList()){
            if(hasPoint(shape.getFixedStart(), shape.getFixedEnd())){
                shapeList.addSelectedShape(shape);
                System.out.println("Shape added to selected shape list");
                shapesSelected = true;
            }
        }
        if(!shapesSelected){
            shapeList.getSelectList().clear();
            System.out.println("Emptying the selected shape list");
        }
    }
}
