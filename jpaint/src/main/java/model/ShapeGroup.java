package model;

import model.interfaces.IShape;
import model.interfaces.IShapeGroupIterator;
import model.persistence.ApplicationState;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeGroup implements IShape{
    private ArrayList<IShape> groupedShapes;
    private Point fixedStart, fixedEnd;
    private ShapeInfo shapeInfo;
    private ArrayList<IShape> ungroupedShapes;

    public ShapeGroup(){
        this.groupedShapes = new ArrayList<IShape>();
        this.shapeInfo = new ShapeInfo();
        this.ungroupedShapes = new ArrayList<IShape>();
    }

    public void group(ArrayList<IShape> selected){
        ShapeGroupIterator iterator = new ShapeGroupIterator(selected);
        while(iterator.hasNext()){
            iterator.getNext();
            groupedShapes.add(iterator.currShape);
        }
        setShapeInfo(groupedShapes);
    }

    public ArrayList<IShape> getGroup(){
        return groupedShapes;
    }
    public ArrayList<IShape> getUngrouped(){return ungroupedShapes;}

    public void setShapeInfo(ArrayList<IShape> list){
        calcFixedStart(list);
        calcFixedEnd(list);
        shapeInfo.setShapeType(ShapeType.RECTANGLE);
        shapeInfo.setFixedStart(fixedStart);
        shapeInfo.setFixedEnd(fixedEnd);
        shapeInfo.setShadingType(ShapeShadingType.OUTLINE);
        shapeInfo.setSecColor(ShapeColor.BLACK);
        shapeInfo.setPrimColor(ShapeColor.BLACK);
    }

    public void calcFixedStart(ArrayList<IShape> list){
        IShape firstShape = list.get(0);
        int x = (int) firstShape.getFixedStart().getX();
        int y = (int) firstShape.getFixedStart().getY();
        for(IShape s : list){
            x = (int) Math.min(x,s.getFixedStart().getX());
            y = (int) Math.min(y,s.getFixedStart().getY());
        }
        this.fixedStart = new Point(x,y);
    }

    public void calcFixedEnd(ArrayList<IShape> list){
        IShape firstShape = list.get(0);
        int x = (int) firstShape.getFixedEnd().getX();
        int y = (int) firstShape.getFixedEnd().getY();
        for(IShape s : list){
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
    public IShape paste() {
        ShapeGroup copy = new ShapeGroup();
        for(IShape s: groupedShapes){
            Point incStart = new Point();
            Point incEnd = new Point();
            IShape cShape;
            incStart.setLocation(s.getFixedStart().getX() + 20, s.getFixedStart().getY() + 20);
            incEnd.setLocation(s.getFixedEnd().getX() + 20, s.getFixedEnd().getY() + 20);
            cShape = new DrawShape(incStart, incEnd, s.getShapeInfo().getPrimColor(), s.getShapeInfo().getSecColor(),
                    s.getShapeInfo().getShapeType(), s.getShapeInfo().getShadingType());
            copy.getGroup().add(cShape);
        }
        copy.setShapeInfo(copy.groupedShapes);
        return copy;
    }

    @Override
    public void ungroup(ArrayList<IShape> current, ArrayList<IShape> selected, ArrayList<IShape> temp) {
        ShapeGroupIterator iterator = new ShapeGroupIterator(groupedShapes);
        while(iterator.hasNext()){
            iterator.getNext();
            current.add(iterator.currShape);
            selected.add(iterator.currShape);
            temp.add(iterator.currShape);
        }
        current.remove(this);
        selected.remove(this);
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return shapeInfo;
    }

    class ShapeGroupIterator implements IShapeGroupIterator{
        private ArrayList<IShape> list;
        private IShape currShape;
        private int i;

        ShapeGroupIterator(ArrayList<IShape> list) {
            this.list = list;
            this.currShape = list.get(0);
            this.i = 0;
        }

        @Override
        public boolean hasNext() {
            if(i > list.size()-1 || list.get(i) == null){
                return false;
            }
            else{
                return true;
            }
        }

        @Override
        public IShape getNext() {
            currShape = list.get(i);
            i++;
            return currShape;
        }
    }
}
