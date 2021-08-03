package controller;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import model.MouseMode;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;

/**
 * Provides a keyboard interface for:
 * - shape selection
 *    ctrl-R = Rectangle
 *    ctrl-T = Triangle
 *    ctrl-E = Ellipse
 * - Mouse mode
 *    ctrl-D = Draw
 *    ctrl-S = Select
 *    ctrl-M = Move
 * - Shading type
 *    ctrl-F = Filled in
 *    ctrl-O = outlined
 *    ctrl-B = Both filled ina and outlined
 *
 *    To use, add the following code somewhere early in the program
 *    (I have it in main())
 *
 *    KeyboardInterface keys = new KeyboardInterface(paintCanvas, appState);
 *    keys.setup();
 */
public class KeyboardInterface {
  private JComponent target;
  private IApplicationState applicationState;

  public KeyboardInterface(JComponent target, IApplicationState applicationState) {
    this.target = target;
    this.applicationState = applicationState;
  }

  public void setup() {

    // Mouse modes
    setupAction("draw", "control D", MouseMode.DRAW, (mode) -> applicationState.setActiveStartAndEndPointMode((MouseMode)mode));
    setupAction("select", "control S", MouseMode.SELECT, (mode) -> applicationState.setActiveStartAndEndPointMode((MouseMode)mode));
    setupAction("move", "control M", MouseMode.MOVE, (mode) -> applicationState.setActiveStartAndEndPointMode((MouseMode)mode));

    // Shape choices
    setupAction("rectangle", "control R", ShapeType.RECTANGLE, (type) -> applicationState.setActiveShape((ShapeType) type));
    setupAction("triangle", "control T", ShapeType.TRIANGLE, (type) -> applicationState.setActiveShape((ShapeType) type));
    setupAction("ellipse", "control E", ShapeType.ELLIPSE, (type) -> applicationState.setActiveShape((ShapeType) type));

    // Shading types
    setupAction("filled", "control F", ShapeShadingType.FILLED_IN, (type) -> applicationState.setActiveShadingType((ShapeShadingType) type));
    setupAction("outlined", "control O", ShapeShadingType.OUTLINE, (type) -> applicationState.setActiveShadingType((ShapeShadingType) type));
    setupAction("both", "control B", ShapeShadingType.OUTLINE_AND_FILLED_IN, (type) -> applicationState.setActiveShadingType((ShapeShadingType) type));

  }

  private void setupAction(String name, String strokeName, Object mode, Consumer c) {
    target.getActionMap().put(name, new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent e) {
        c.accept(mode);
      }
    });
    InputMap inputMap = target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(KeyStroke.getKeyStroke(strokeName), name);
  }
}
