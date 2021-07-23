package controller;

import model.ShapeInfo;
import model.ShapeList;
import model.dialogs.ChooseStartAndEndPointModeDialog;
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
    private IApplicationState appstate;
    private MouseMode mMode = MouseMode.DRAW;
    private Graphics2D g;
    private ShapeList shapelist;
    private ShapeInfo shapeInfo;
    private ArrayList<IShape> array = new ArrayList<IShape>();

    public MouseListener(PaintCanvasBase pCB, IApplicationState aS, ShapeList shapelist, ShapeInfo shapeInfo){
        this.paintCanvasBase = pCB;
        this.appstate = aS;
        this.g = pCB.getGraphics2D();
        this.shapelist = shapelist;
        this.shapeInfo = shapeInfo;
    }


    @Override
    public void mousePressed(MouseEvent e){
        appstate.setStart(e.getPoint());
        this.start = e.getPoint();
        double x = this.start.getX();
        double y = this.start.getY();
        System.out.println("mouse pressed");
        System.out.println("Press X: " + x + "and Y: " + y);
    }
    @Override
    public void mouseReleased(MouseEvent e){
        appstate.setEnd(e.getPoint());
        this.end = e.getPoint();
        double x = this.end.getX();
        double y = this.end.getY();
        System.out.println("mouse released");
        System.out.println("Release X: " + x + "and Y: " + y);

        if(appstate.getActiveMouseMode().equals(MouseMode.DRAW)){
            shapeInfo = appstate.getShapeInfo();
            DrawShapeCommand draw = new DrawShapeCommand(g, appstate, paintCanvasBase, shapeInfo, shapelist);
            draw.run();
            paintCanvasBase.repaint();
        }
        else if(appstate.getActiveMouseMode().equals(MouseMode.SELECT)){
            shapeInfo = appstate.getShapeInfo();
            System.out.println("SELECT MOUSE MODE");
            SelectShapeCommand select = new SelectShapeCommand(shapelist, appstate, shapeInfo);
            select.run();

        } else if (appstate.getActiveMouseMode().equals(MouseMode.MOVE)){
            System.out.println("MOVE MOUSE MODE");
            MoveShapeCommand move = new MoveShapeCommand(shapelist,appstate,shapeInfo, paintCanvasBase);
            move.run();
            paintCanvasBase.repaint();
        }

    }

}
