package model.Shapes;

import model.ShapeColorTranslator;
import model.ShapeInfo;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;

import java.awt.*;

public class Ellipse implements IShape {
    private int x, y, width, height;
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
    }

    public void draw(Graphics2D g){
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
}

