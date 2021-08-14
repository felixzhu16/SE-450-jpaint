package model.Shapes;

import model.*;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle implements IShape {
    private int x, y, x2, y2, width, height;
    private ShapeInfo shapeInfo;
    private Color primColor;
    private Color secColor;
    private Point start;
    private Point end;
    private Point fixedstart;
    private Point fixedend;
    private ShapeShadingType shadingType;
    private ShapeType shapeType;

    public Rectangle(ShapeInfo shapeInfo){
        this.shapeInfo = shapeInfo;
        this.primColor = ShapeColorTranslator.translate(shapeInfo.getPrimColor());
        this.secColor = ShapeColorTranslator.translate(shapeInfo.getSecColor());
        this.start = shapeInfo.getStart();
        this.end = shapeInfo.getEnd();
        this.fixedstart = shapeInfo.getFixedStart();
        this.fixedend = shapeInfo.getFixedEnd();
        this.shadingType = shapeInfo.getShadingType();
        this.shapeType = shapeInfo.getShapeType();
        this.width = (int)(fixedend.getX() - fixedstart.getX());
        this.height = (int)(fixedend.getY() - fixedstart.getY());
        this.x = (int) fixedstart.getX();
        this.y = (int) fixedstart.getY();
        this.x2 = (int) fixedend.getX();
        this.y2 = (int) fixedend.getY();
    }



    public void draw(Graphics2D g){
        x = (int) fixedstart.getX();
        y = (int) fixedstart.getY();
        x2 = (int) fixedend.getX();
        y2 = (int) fixedend.getY();
        if(shadingType.equals(ShapeShadingType.OUTLINE)){
            g.setColor(primColor);
            g.setStroke(new BasicStroke(3));
            g.drawRect(x,y, width, height);

        }
        else if(shadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(secColor);
            g.setStroke(new BasicStroke(3));
            g.drawRect(x,y,width,height);
            g.setColor(primColor);
            g.fillRect(x,y,width,height);
        }
        else if(shadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(primColor);
            g.fillRect(x,y,width,height);
        }
    }

    public Point getFixedStart(){
        return fixedstart;
    }

    public Point getFixedEnd(){
        return fixedend;
    }

    @Override
    public void setFixedStart(Point x) {
        fixedstart = x;
    }

    @Override
    public void setFixedEnd(Point x) {
        fixedend = x;
    }

    public void modXYCoords(double x, double y){
        fixedstart.setLocation(fixedstart.getX() + x, fixedstart.getY() + y);
        fixedend.setLocation(fixedend.getX() + x, fixedend.getY() + y);
    }

    @Override
    public IShape paste() {
        Point incStart = new Point();
        Point incEnd = new Point();
        IShape cShape;
        incStart.setLocation(getFixedStart().getX() + 20, getFixedStart().getY() + 20);
        incEnd.setLocation(getFixedEnd().getX() + 20, getFixedEnd().getY() + 20);
        cShape = new DrawShape(incStart, incEnd, getShapeInfo().getPrimColor(), getShapeInfo().getSecColor(),
                getShapeInfo().getShapeType(), getShapeInfo().getShadingType());
        return cShape;
    }

    @Override
    public void ungroup(ArrayList<IShape> current, ArrayList<IShape> selected, ArrayList<IShape> temp) {
        current.add(this);
        selected.add(this);
        temp.add(this);
    }

    public ShapeInfo getShapeInfo(){
        return shapeInfo;
    }


}
