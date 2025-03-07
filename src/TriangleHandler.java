import java.awt.*;

import static javax.swing.UIManager.getColor;

public class TriangleHandler extends ShapeHandler {
    @Override
    protected boolean canHandle(String shapeType) {
        return "TRIANGLE".equalsIgnoreCase(shapeType);
    }

    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawTriangle(tokens, graphics);
    }

    private void drawTriangle(String[] tokens, Graphics graphics) {
        System.out.println("Drawing Triangle...");
        int[] xPoints = {Integer.parseInt(tokens[2]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[6])};
        int[] yPoints = {Integer.parseInt(tokens[3]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[7])};
        String colorName = tokens[tokens.length - 1].trim().toLowerCase();
        Color color = DrawingServer.getColor(colorName);
        graphics.setColor(color);
        graphics.drawPolygon(xPoints, yPoints, 3);
    }
}
