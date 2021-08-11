package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeGroup implements IShape {
    private ArrayList<IShape> groupedShapes;
    private Point fixedStart, fixedEnd;
    private ShapeInfo shapeInfo;

    public ShapeGroup(){
        this.groupedShapes = new ArrayList<IShape>();
        this.shapeInfo = new ShapeInfo();
    }

    public void group(ArrayList<IShape> selected){
        if(selected != null){
            for(IShape s : selected){
                groupedShapes.add(s);
            }
        }
        setShapeInfo();
    }

    public ArrayList<IShape> getGroup(){
        return groupedShapes;
    }

    public void setShapeInfo(){
        calcFixedStart();
        calcFixedEnd();
        shapeInfo.setShapeType(ShapeType.RECTANGLE);
        shapeInfo.setFixedStart(fixedStart);
        shapeInfo.setFixedEnd(fixedEnd);
//        shapeInfo.setShadingType(ShapeShadingType.OUTLINE);
//        shapeInfo.setSecColor(ShapeColor.BLACK);
//        shapeInfo.setPrimColor(ShapeColor.BLACK);
    }

    public void calcFixedStart(){
        IShape firstShape = groupedShapes.get(0);
        int x = (int) firstShape.getFixedStart().getX();
        int y = (int) firstShape.getFixedStart().getY();
        for(IShape s : groupedShapes){
            x = (int) Math.min(x,s.getFixedStart().getX());
            y = (int) Math.min(y,s.getFixedStart().getY());
        }
        this.fixedStart = new Point(x,y);
    }

    public void calcFixedEnd(){
        IShape firstShape = groupedShapes.get(0);
        int x = (int) firstShape.getFixedEnd().getX();
        int y = (int) firstShape.getFixedEnd().getY();
        for(IShape s : groupedShapes){
            x = (int) Math.max(x,s.getFixedEnd().getX());
            y = (int) Math.max(y,s.getFixedEnd().getY());
        }
        this.fixedEnd = new Point(x,y);
    }


    @Override
    public void draw(Graphics2D g) {
        for(IShape s : groupedShapes){
            s.draw(g);
        }
    }

    @Override
    public Point getFixedStart() {
        return fixedStart;
    }

    @Override
    public Point getFixedEnd() {
        return fixedEnd;
    }

    @Override
    public void setFixedStart(Point x) {
        fixedStart = x;
    }

    @Override
    public void setFixedEnd(Point x) {
        fixedEnd = x;
    }

    @Override
    public void modXYCoords(double x, double y) {
        for(IShape s : groupedShapes){
            s.modXYCoords(x,y);
        }
        fixedStart.setLocation(fixedStart.getX() + x, fixedStart.getY() + y);
        fixedEnd.setLocation(fixedEnd.getX() + x, fixedEnd.getY() + y);
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return shapeInfo;
    }

}
