import java.awt.*;

import static javax.swing.UIManager.getColor;

/**
 * The {@code CircleHandler} class is responsible for handling drawing requests for circles.
 * <p>
 * This class extends {@link ShapeHandler} as part of the Chain of Responsibility pattern.
 * It determines if a given drawing request is for a circle and, if so, processes the request by extracting
 * the circle parameters (center coordinates, radius, and color) from the provided tokens and rendering the circle
 * using a {@code Graphics} object.
 * </p>
 *
 * @see ShapeHandler
 * @see DrawingServer
 */
public class CircleHandler extends ShapeHandler {

    /**
     * Determines whether this handler can process the drawing request based on the shape type.
     * <p>
     * This implementation returns {@code true} if the provided {@code shapeType} equals "CIRCLE"
     * (ignoring case).
     * </p>
     *
     * @param shapeType the type of the shape to be drawn.
     * @return {@code true} if the shape type is "CIRCLE", {@code false} otherwise.
     */
    @Override
    protected boolean canHandle(String shapeType) {
        return "CIRCLE".equalsIgnoreCase(shapeType);
    }

    /**
     * Processes the drawing request for a circle.
     * <p>
     * This method delegates the drawing operation to the {@link #drawCircle(String[], Graphics)} method.
     * </p>
     *
     * @param tokens   an array of strings representing the drawing request details.
     *                 Expected tokens include the shape type at index 1, the x-coordinate at index 2,
     *                 the y-coordinate at index 3, the radius at index 4, and the color in the last token.
     * @param graphics the {@code Graphics} context used for drawing.
     */
    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawCircle(tokens, graphics);
    }

    /**
     * Draws a circle using the specified tokens and {@code Graphics} context.
     * <p>
     * This method parses the center coordinates, radius, and color from the tokens array.
     * It then retrieves the corresponding {@code Color} object via {@link DrawingServer#getColor(String)}
     * and draws the circle using {@code Graphics.drawOval}.
     * </p>
     *
     * @param tokens   an array of strings containing the drawing parameters. The expected tokens are:
     *                 <ul>
     *                   <li>tokens[2] - the x-coordinate of the circle's center</li>
     *                   <li>tokens[3] - the y-coordinate of the circle's center</li>
     *                   <li>tokens[4] - the radius of the circle</li>
     *                   <li>the last token - the color name of the circle</li>
     *                 </ul>
     * @param graphics the {@code Graphics} object used to render the circle.
     */
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
