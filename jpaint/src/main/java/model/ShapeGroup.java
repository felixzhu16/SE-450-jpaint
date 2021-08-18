package model;

import model.interfaces.ICollection;
import model.interfaces.IShape;
import model.interfaces.IShapeGroupIterator;

import java.awt.*;
import java.util.ArrayList;

public class ShapeGroup implements IShape, ICollection{
    private ArrayList<IShape> groupedShapes;
    private Point fixedStart, fixedEnd;
    private ShapeInfo shapeInfo;

    public ShapeGroup(){
        this.groupedShapes = new ArrayList<IShape>();
        this.shapeInfo = new ShapeInfo();
    }

    public void group(ArrayList<IShape> selected){
        for(IShape s : selected){
            groupedShapes.add(s);
        }
        setShapeInfo(groupedShapes);
    }

    public ArrayList<IShape> getGroup(){
        return groupedShapes;
    }

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
        ShapeGroupIterator iterator = createIterator();
        while(iterator.hasNext()){
            iterator.getNext();
            iterator.currShape.draw(g);
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
        ShapeGroupIterator iterator = createIterator();
        while(iterator.hasNext()){
            iterator.getNext();
            Point incStart = new Point();
            Point incEnd = new Point();
            IShape cShape;
            incStart.setLocation(iterator.currShape.getFixedStart().getX() + 20, iterator.currShape.getFixedStart().getY() + 20);
            incEnd.setLocation(iterator.currShape.getFixedEnd().getX() + 20, iterator.currShape.getFixedEnd().getY() + 20);
            cShape = new DrawShape(incStart, incEnd,iterator.currShape.getShapeInfo().getPrimColor(), iterator.currShape.getShapeInfo().getSecColor(),
                    iterator.currShape.getShapeInfo().getShapeType(), iterator.currShape.getShapeInfo().getShadingType());
            copy.getGroup().add(cShape);
        }
        copy.setShapeInfo(copy.groupedShapes);
        return copy;
    }

    @Override
    public void ungroup(ArrayList<IShape> current, ArrayList<IShape> selected, ArrayList<IShape> temp) {
        ShapeGroupIterator iterator = createIterator();
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

    @Override
    public ShapeGroupIterator createIterator() {
        return new ShapeGroupIterator();
    }


    public class ShapeGroupIterator implements IShapeGroupIterator{
        private ArrayList<IShape> list;
        private IShape currShape;
        private int i;

        ShapeGroupIterator() {
            this.list = groupedShapes;
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
