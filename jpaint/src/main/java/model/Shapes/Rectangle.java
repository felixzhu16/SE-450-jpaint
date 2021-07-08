package model.Shapes;

import model.ShapeColor;
import model.ShapeColorTranslator;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle implements IShape {
    int x, y, width, height;
    Color color;

    public Rectangle(Point start, Point end, ShapeColor color){
        this.color = ShapeColorTranslator.translate(color);
        this.width = (int)(end.getX() - start.getX());
        this.height = (int)(end.getY() - start.getY());
        this.x = (int) start.getX();
        this.y = (int) start.getY();
    }

    public void draw(Graphics2D g){
        System.out.println(width + " and " + height);
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }
}
