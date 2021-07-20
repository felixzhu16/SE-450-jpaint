package model.Shapes;

import model.ShapeColorTranslator;
import model.ShapeInfo;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;

import java.awt.*;

public class Triangle implements IShape {
    private ShapeInfo shapeInfo;
    private Color primColor;
    private Color secColor;
    private Point start;
    private Point end;
    private Point fixedstart;
    private Point fixedend;
    private ShapeShadingType shadingType;
    private ShapeType shapeType;
    private int[] x = new int[3];
    private int[] y = new int[3];

    public Triangle(ShapeInfo shapeInfo){
        this.shapeInfo = shapeInfo;
        this.primColor = ShapeColorTranslator.translate(shapeInfo.getPrimColor());
        this.secColor = ShapeColorTranslator.translate(shapeInfo.getSecColor());
        this.start = shapeInfo.getStart();
        this.end = shapeInfo.getEnd();
        this.fixedstart = shapeInfo.getFixedStart();
        this.fixedend = shapeInfo.getFixedEnd();
        this.shadingType = shapeInfo.getShadingType();
        this.shapeType = shapeInfo.getShapeType();
        //Starting vertex coordinates
        //Making a right triangle
        this.x[0] = (int) fixedstart.getX();
        this.y[0] = (int) fixedstart.getY();
        //Second vertex
        this.x[1] = (int) fixedend.getX();
        this.y[1] = (int) fixedend.getY();
        //Third vertex
        this.x[2] = (int) fixedstart.getX();
        this.y[2] = (int) fixedend.getY();
    }

    public void draw(Graphics2D g){
        if(shadingType.equals(ShapeShadingType.OUTLINE)){
            g.setColor(primColor);
            g.setStroke(new BasicStroke(3));
            g.drawPolygon(x,y, 3);

        }
        else if(shadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(primColor);
            g.setStroke(new BasicStroke(3));
            g.drawPolygon(x,y, 3);
            g.setColor(secColor);
            g.fillPolygon(x,y, 3);
        }
        else if(shadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(secColor);
            g.drawPolygon(x,y, 3);
        }

    }

}
