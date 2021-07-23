package model;

import java.awt.*;

public class ShapeColorTranslator {
    private ShapeColor color;
    public ShapeColorTranslator(ShapeColor color){
        this.color = color;
    }

    public static Color translate(ShapeColor color){
        Color shapecol = null;
        switch(color){
            case BLACK:
                shapecol = Color.BLACK;
                break;
            case BLUE:
                shapecol = Color.BLUE;
                break;
            case CYAN:
                shapecol = Color.CYAN;
                break;
            case DARK_GRAY:
                shapecol = Color.DARK_GRAY;
                break;
            case GRAY:
                shapecol = Color.GRAY;
                break;
            case GREEN:
                shapecol = Color.GREEN;
                break;
            case LIGHT_GRAY:
                shapecol = Color.LIGHT_GRAY;
                break;
            case MAGENTA:
                shapecol = Color.MAGENTA;
                break;
            case ORANGE:
                shapecol = Color.ORANGE;
                break;
            case PINK:
                shapecol = Color.PINK;
                break;
            case RED:
                shapecol = Color.RED;
                break;
            case WHITE:
                shapecol = Color.WHITE;
                break;
            case YELLOW:
                shapecol = Color.YELLOW;
                break;
        }
        return shapecol;
    }
}
