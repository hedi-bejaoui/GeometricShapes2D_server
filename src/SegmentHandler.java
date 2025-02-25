import java.awt.*;

import static javax.swing.UIManager.getColor;

public class SegmentHandler extends ShapeHandler {
    @Override
    protected boolean canHandle(String shapeType) {
        return "SEGMENT".equalsIgnoreCase(shapeType);
    }

    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawSegment(tokens, graphics);
    }

    private void drawSegment(String[] tokens, Graphics graphics) {
        System.out.println("Drawing Segment...");
        int x1 = Integer.parseInt(tokens[2]);
        int y1 = Integer.parseInt(tokens[3]);
        int x2 = Integer.parseInt(tokens[4]);
        int y2 = Integer.parseInt(tokens[5]);
        Color color = getColor(tokens[6]);
        graphics.setColor(color);
        graphics.drawLine(x1, y1, x2, y2);
    }
}
