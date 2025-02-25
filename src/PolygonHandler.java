import java.awt.*;

import static javax.swing.UIManager.getColor;

public class PolygonHandler extends ShapeHandler{
    @Override
    protected boolean canHandle(String shapeType) {
        return "POLYGONE".equalsIgnoreCase(shapeType);
    }

    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawPolygon(tokens, graphics);
    }

    private void drawPolygon(String[] tokens, Graphics graphics) {
        System.out.println("Drawing Segment...");
        int n = (tokens.length - 2) / 2;
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];
        for (int i = 0; i < n; i++) {
            xPoints[i] = Integer.parseInt(tokens[2 + i * 2]);
            yPoints[i] = Integer.parseInt(tokens[3 + i * 2]);
        }
        Color color = getColor(tokens[tokens.length - 1]);
        graphics.setColor(color);
        graphics.drawPolygon(xPoints, yPoints, n);
    }
}
