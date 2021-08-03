package model.interfaces;

import model.*;

import java.awt.*;

public interface IApplicationState {
    void setActiveShape(ShapeType type);

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType(ShapeShadingType type);

    void setActiveStartAndEndPointMode(MouseMode mode);

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
