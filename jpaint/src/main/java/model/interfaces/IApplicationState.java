package model.interfaces;

import model.*;

import java.awt.*;

public interface IApplicationState {
    void setActiveShape(ShapeType type);
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType(ShapeShadingType type);

    void setActiveShadingType();

    void setActiveStartAndEndPointMode(MouseMode mode);

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
