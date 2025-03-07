import java.awt.*;

import static javax.swing.UIManager.getColor;

public class CircleHandler extends ShapeHandler {
    @Override
    protected boolean canHandle(String shapeType) {
        return "CIRCLE".equalsIgnoreCase(shapeType);
    }

    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawCircle(tokens, graphics);
    }


    private void drawCircle(String[] tokens, Graphics graphics) {
        System.out.println("Drawing Circle...");
        int cx = Integer.parseInt(tokens[2]);
        int cy = Integer.parseInt(tokens[3]);
        int radius = Integer.parseInt(tokens[4]);
        String colorName = tokens[tokens.length - 1].trim().toLowerCase();
        Color color = DrawingServer.getColor(colorName);
        graphics.setColor(color);
        graphics.drawOval(cx - radius, cy - radius, radius * 2, radius * 2);
    }
}
