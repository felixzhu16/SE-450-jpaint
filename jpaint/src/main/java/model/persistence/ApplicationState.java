package model.persistence;

import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;

import java.awt.*;

public class ApplicationState implements IApplicationState {
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private MouseMode activeMouseMode;
    private Undo activeUndo;
    private Redo activeRedo;
    private Point start;
    private Point end;


    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeMouseMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    public void setStart(Point start){
        this.start = start;
    }

    public void setEnd(Point end){
        this.end = end;
    }
    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }
    public ShapeInfo getShapeInfo(){
        ShapeInfo shapeInfo = new ShapeInfo();
        shapeInfo.setPrimColor(activePrimaryColor);
        shapeInfo.setSecColor(activeSecondaryColor);
        shapeInfo.setShadingType(activeShapeShadingType);
        shapeInfo.setShapeType(activeShapeType);
        shapeInfo.setStart(start);
        shapeInfo.setEnd(end);
        shapeInfo.setFixedStart();
        shapeInfo.setFixedEnd();
        return shapeInfo;
    }

    public Point getStart(){
        return start;
    }

    public Point getEnd(){
        return end;
    }
    @Override
    public MouseMode getActiveMouseMode() {
        return activeMouseMode;
    }

    @Override
    public Undo getUndo(){
        return activeUndo;
    }
    @Override
    public Redo getRedo(){
        return activeRedo;
    }


    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeMouseMode = MouseMode.DRAW;
        activeUndo = Undo.CANCEL;
        activeRedo = Redo.CANCEL;
    }
}
