package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseListener;
import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeList;
import model.ShapeType;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.*;
import java.util.Collection;
import java.util.EnumMap;

public class Main {
    public static void main(String[] args){
        ShapeList shapeList = new ShapeList();
        ShapeInfo shapeInfo = new ShapeInfo();
        PaintCanvasBase paintCanvas = new PaintCanvas(shapeList);
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        IJPaintController controller = new JPaintController(uiModule, appState, shapeList, paintCanvas, shapeInfo);
        MouseListener mouseListener = new MouseListener(paintCanvas, appState, shapeList, shapeInfo);
        paintCanvas.addMouseListener(mouseListener);
        controller.setup();

    }
}
