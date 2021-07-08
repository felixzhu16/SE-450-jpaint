package view.gui;

import controller.DrawShapeCommand;
import model.ShapeFactory;
import model.ShapeList;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;


public class PaintCanvas extends PaintCanvasBase {
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    public void paintList(ArrayList<IShape> list, Graphics2D g){
        if(list != null){
            for(IShape x : list){
                x.draw(g);
            }
        }
    }

    @Override
    /**
     * This is an event handler.  If this function gets called, its time to
     * draw the entire picture.
     * It you want to force a paint event, call aPaintCanvas.repaint()
     */
    public void paint(Graphics g) {
        ArrayList<IShape> canvasShapes = ShapeList.getCurrList();
        Graphics2D graphics2D = (Graphics2D) g;
        ShapeList.setG(graphics2D);
        super.paint(g);
        paintList(canvasShapes, graphics2D); //paints the list of shapes we have drawn
        System.out.println("Time to repaint");
    }
}
