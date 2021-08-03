package controller;

import model.ShapeList;
import model.interfaces.IShape;

public class CopyCommand implements ICommand{
    ShapeList shapelist;
    public CopyCommand(ShapeList shapelist){
        this.shapelist = shapelist;
    }

    @Override
    public void run() {
        //clear the list every time we copy
        shapelist.copiedShapes.clear();
        for(IShape shape : shapelist.getSelectList()){
            shapelist.addCopyShape(shape);
        }

    }
}
