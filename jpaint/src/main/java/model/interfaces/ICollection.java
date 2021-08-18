package model.interfaces;

import model.ShapeGroup;

public interface ICollection {
    ShapeGroup.ShapeGroupIterator createIterator();
}
