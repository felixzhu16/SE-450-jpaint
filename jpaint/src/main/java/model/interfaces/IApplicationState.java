package model.interfaces;

import model.*;

import java.awt.*;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    void setStart(Point start);

    void setEnd(Point end);

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    ShapeInfo getShapeInfo();

    MouseMode getActiveMouseMode();

    Point getStart();

    Point getEnd();

    Undo getUndo();

    Redo getRedo();
}
