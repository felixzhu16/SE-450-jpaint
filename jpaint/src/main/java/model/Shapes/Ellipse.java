package model.Shapes;

import model.ShapeColorTranslator;
import model.ShapeInfo;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;

import java.awt.*;

public class Ellipse implements IShape {
    private int x, y, width, height;
    int x2, y2;
    private ShapeInfo shapeInfo;
    private Color primColor;
    private Color secColor;
    private Point start;
    private Point end;
    private Point fixedstart;
    private Point fixedend;
    private ShapeShadingType shadingType;
    private ShapeType shapeType;

    public Ellipse(ShapeInfo shapeInfo){
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
            g.drawOval(x,y, width, height);

        }
        else if(shadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(primColor);
            g.setStroke(new BasicStroke(3));
            g.drawOval(x,y,width,height);
            g.setColor(secColor);
            g.fillOval(x,y,width,height);
        }
        else if(shadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(secColor);
            g.fillOval(x,y,width,height);
        }

    }

    public Point getFixedStart(){
        return fixedstart;
    }

    public Point getFixedEnd(){
        return fixedend;
    }

    public void modXYCoords(double x, double y){
        fixedstart.setLocation(fixedstart.getX() + x, fixedstart.getY() + y);
        fixedend.setLocation(fixedend.getX() + x, fixedend.getY() + y);
    }

}

