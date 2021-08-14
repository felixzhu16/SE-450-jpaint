package model;

import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class DrawDash implements IShape {
    Point start, end;
    IShape shape;
    ShapeType shapetype;
    Stroke stroke;
    ShapeInfo shapeinfo;
    public DrawDash(IShape shape){
        this.start = shape.getFixedStart();
        this.end = shape.getFixedEnd();
        this.shape = shape;
        this.shapetype = shape.getShapeInfo().getShapeType();
        this.stroke =  new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 2, new float[]{6}, 0);
        this.shapeinfo = shape.getShapeInfo();
    }

     public void drawDashShape(Graphics2D g){
         switch(shapetype.toString()){
             case "RECTANGLE":
                 int width = (int)(end.getX() - start.getX()) + 10;
                 int height = (int)(end.getY() - start.getY()) + 10;
                 int x = (int) start.getX() - 5;
                 int y = (int) start.getY() - 5;
                 g.setColor(Color.BLACK);
                 g.setStroke(stroke);
                 g.drawRect(x,y, width, height);
                 break;
             case "ELLIPSE":
                 int eW = (int)(end.getX() - start.getX()) + 10;
                 int eH = (int)(end.getY() - start.getY()) + 10;
                 int eX = (int) start.getX() - 5;
                 int eY = (int) start.getY() - 5;
                 g.setColor(Color.BLACK);
                 g.setStroke(stroke);
                 g.drawOval(eX,eY, eW, eH);
                 break;
             case "TRIANGLE":
                 int[] tX = new int[3];
                 int[] tY = new int[3];
                 tX[0] = (int) start.getX() - 8;
                 tY[0] = (int) start.getY() - 15;
                 tX[1] = (int) end.getX() + 15;
                 tY[1] = (int) end.getY() + 8;
                 tX[2] = (int) start.getX() - 8;
                 tY[2] = (int) end.getY() + 8;
                 g.setColor(Color.BLACK);
                 g.setStroke(stroke);
                 g.drawPolygon(tX,tY, 3);
                 break;
         }
         shape.draw(g);
     }

    @Override
    public void draw(Graphics2D g) {
       drawDashShape(g);
    }

    @Override
    public Point getFixedStart() {
        return start;
    }

    @Override
    public Point getFixedEnd() {
        return end;
    }


    @Override
    public void setFixedStart(Point x) {
        start = x;
    }

    @Override
    public void setFixedEnd(Point x) {
        end = x;
    }

    @Override
    public void modXYCoords(double x, double y) {
        start.setLocation(start.getX() - x,start.getY() + y);
        end.setLocation(end.getX() + x, end.getY() - y);
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
        current.add(shape);
        selected.add(shape);
        temp.add(shape);
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return shape.getShapeInfo();
    }


}
