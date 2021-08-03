package model;

import java.awt.*;

public class ShapeInfo {
    private ShapeColor primColor;
    private ShapeColor secColor;
    private Point start;
    private Point end;
    private Point fixedStart;
    private Point fixedEnd;
    private ShapeShadingType shadingType;
    private ShapeType shapeType;

    public void setPrimColor(ShapeColor primColor){
        this.primColor = primColor;
    }

    public void setSecColor(ShapeColor secColor){
        this.secColor = secColor;
    }

    public void setStart(Point start){
        this.start = start;
    }

    public void setEnd(Point end){
        this.end = end;
    }

    public void setFixedStart(Point x){
        this.fixedStart = x;
    }

    public void setFixedEnd(Point x){
        this.fixedEnd = x;
    }

    public void calcFixedStart(){
        int x = (int) Math.min(start.getX(),end.getX());
        int y = (int) Math.min(start.getY(),end.getY());
        this.fixedStart = new Point(x,y);
    }

    public void calcFixedEnd(){
        int x = (int) Math.max(start.getX(),end.getX());
        int y = (int) Math.max(start.getY(),end.getY());
        this.fixedEnd = new Point(x,y);
    }

    public void setShadingType(ShapeShadingType shadingType){
        this.shadingType = shadingType;
    }

    public void setShapeType(ShapeType shapeType){
        this.shapeType = shapeType;
    }

    public ShapeColor getPrimColor(){
        return primColor;
    }

    public ShapeColor getSecColor(){
        return secColor;
    }

    public Point getStart(){
        return start;
    }

    public Point getEnd(){
        return end;
    }

    public Point getFixedStart(){return fixedStart;}

    public Point getFixedEnd(){return fixedEnd;}

    public ShapeShadingType getShadingType(){
        return shadingType;
    }

    public ShapeType getShapeType(){
        return shapeType;
    }
}
