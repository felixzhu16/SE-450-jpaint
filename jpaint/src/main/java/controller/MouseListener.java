package controller;

import model.ShapeList;
import model.interfaces.IApplicationState;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import model.MouseMode;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class MouseListener extends MouseAdapter {
    private Point start;
    private Point end;
    private PaintCanvasBase paintCanvasBase;
    private IApplicationState IapplicationState;
    private MouseMode mMode = MouseMode.DRAW;
    private Graphics2D g;
    private ShapeList shapelist;
    private ArrayList<IShape> array = new ArrayList<IShape>();

    public MouseListener(PaintCanvasBase pCB, IApplicationState aS){
        this.paintCanvasBase = pCB;
        this.IapplicationState = aS;
        this.g = pCB.getGraphics2D();
        this.shapelist = new ShapeList();
    }


    @Override
    public void mousePressed(MouseEvent e){
        this.start = e.getPoint();
        double x = this.start.getX();
        double y = this.start.getY();
        System.out.println("mouse pressed");
        System.out.println("Press X: " + x + "and Y: " + y);
    }
    @Override
    public void mouseReleased(MouseEvent e){
        this.end = e.getPoint();
        double x = this.end.getX();
        double y = this.end.getY();
        System.out.println("mouse released");
        System.out.println("Release X: " + x + "and Y: " + y);
        DrawShapeCommand draw = new DrawShapeCommand(start, end, g, IapplicationState, paintCanvasBase);
        draw.run();
        array.add(draw.returnshape());
        ShapeList.giveList(array);
        paintCanvasBase.repaint();
    }

}
