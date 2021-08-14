package view.gui;

import controller.DrawShapeCommand;
import model.DrawDash;
import model.ShapeFactory;
import model.ShapeList;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;


public class PaintCanvas extends PaintCanvasBase {
    private ShapeList shapelist;
    private ArrayList<IShape> canvasShapes;
    private ArrayList<IShape> selectShapes;
    public PaintCanvas(ShapeList shapeList){
        this.shapelist = shapeList;
        this.canvasShapes = shapelist.getCurrList();
        this.selectShapes = shapelist.getSelectList();
    }
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    public void paintList(ArrayList<IShape> list, ArrayList<IShape> selectlist,Graphics2D g){
        if(list != null){
            for(IShape x : list){
                if(selectlist.contains(x)){
                    x = new DrawDash(x);
                }
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
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        paintList(canvasShapes, selectShapes, graphics2D); //paints the list of shapes we have drawn
        System.out.println("Time to repaint");
    }
}
