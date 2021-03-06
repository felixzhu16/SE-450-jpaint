package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.KeyboardInterface;
import controller.MouseListener;
import model.*;
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
        ShapeGroup shapeGroup = new ShapeGroup();
        PaintCanvasBase paintCanvas = new PaintCanvas(shapeList);
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        MouseListener mouseListener = new MouseListener(paintCanvas, appState, shapeList, shapeInfo);
        paintCanvas.addMouseListener(mouseListener);
        KeyboardInterface keys = new KeyboardInterface(paintCanvas, appState);
        keys.setup();

        IJPaintController controller = new JPaintController(uiModule, appState, shapeList, paintCanvas, shapeInfo, shapeGroup);
        controller.setup();


    }
}
