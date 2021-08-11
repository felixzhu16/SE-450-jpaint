package controller;

import model.*;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ShapeList shapeList;
    private final PaintCanvasBase paintcanvas;
    private final ShapeInfo shapeInfo;
    private final ShapeGroup shapeGroup;


    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapeList, PaintCanvasBase paintcanvas, ShapeInfo shapeInfo, ShapeGroup shapeGroup) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.paintcanvas = paintcanvas;
        this.shapeInfo = shapeInfo;
        this.shapeGroup = shapeGroup;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
       // uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape((ShapeType) type));
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
       // uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType((ShapeShadingType) type));
       // uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode((MouseMode) mode));
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand(shapeList,paintcanvas).run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand(shapeList, paintcanvas).run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand(shapeList, paintcanvas).run());
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand(shapeList).run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(shapeList, paintcanvas, applicationState).run());
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand(shapeList, paintcanvas).run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupCommand(shapeList, paintcanvas).run());
        //Add Undo, redo, copy, paste, delete, group, ungroup
    }
}
