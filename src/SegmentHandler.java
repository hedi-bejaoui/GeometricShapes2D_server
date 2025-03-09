import java.awt.*;

import static javax.swing.UIManager.getColor;

/**
 * The {@code SegmentHandler} class handles drawing requests for segments.
 * <p>
 * This class extends {@link ShapeHandler} as part of the Chain of Responsibility pattern.
 * It determines if a given drawing request is for a segment (identified by the token "SEGMENT")
 * and, if so, processes the request by extracting the segment endpoints and color from the provided tokens,
 * and then renders the segment using a {@code Graphics} object.
 * </p>
 *
 * @see ShapeHandler
 * @see DrawingServer
 */
public class SegmentHandler extends ShapeHandler {

    /**
     * Determines whether this handler can process the drawing request based on the shape type.
     * <p>
     * This implementation returns {@code true} if the provided {@code shapeType} equals "SEGMENT" (ignoring case).
     * </p>
     *
     * @param shapeType the type of the shape to be drawn.
     * @return {@code true} if the shape type is "SEGMENT", {@code false} otherwise.
     */
    @Override
    protected boolean canHandle(String shapeType) {
        return "SEGMENT".equalsIgnoreCase(shapeType);
    }

    /**
     * Processes the drawing request for a segment.
     * <p>
     * This method delegates the drawing operation to the {@link #drawSegment(String[], Graphics)} method.
     * </p>
     *
     * @param tokens   an array of strings representing the drawing request details.
     *                 Expected tokens include the shape type at index 1, the endpoints at indexes 2 to 5,
     *                 and the final token as the color.
     * @param graphics the {@code Graphics} object used for drawing.
     */
    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawSegment(tokens, graphics);
    }

    /**
     * Draws a segment using the specified tokens and {@code Graphics} context.
     * <p>
     * This method parses the endpoint coordinates (x1, y1) and (x2, y2) from the tokens array,
     * extracts the color from the last token, and retrieves the corresponding {@code Color} object via
     * {@link DrawingServer#getColor(String)}. It then sets the drawing color and draws the segment using
     * {@code Graphics.drawLine}.
     * </p>
     *
     * @param tokens   an array of strings containing the drawing parameters.
     *                 The endpoints are expected at tokens[2] through tokens[5], and the color at the last token.
     * @param graphics the {@code Graphics} object used to render the segment.
     */
    private void drawSegment(String[] tokens, Graphics graphics) {
        System.out.println("Drawing Segment...");
        int x1 = Integer.parseInt(tokens[2]);
        int y1 = Integer.parseInt(tokens[3]);
        int x2 = Integer.parseInt(tokens[4]);
        int y2 = Integer.parseInt(tokens[5]);
        String colorName = tokens[tokens.length - 1].trim().toLowerCase();
        Color color = DrawingServer.getColor(colorName);
        graphics.setColor(color);
        graphics.drawLine(x1, y1, x2, y2);
    }
}
