import java.awt.*;

import static javax.swing.UIManager.getColor;

/**
 * The {@code TriangleHandler} class is responsible for handling drawing requests for triangles.
 * <p>
 * This class extends {@link ShapeHandler} as part of the Chain of Responsibility pattern.
 * It determines if a given drawing request is for a triangle (identified by the token "TRIANGLE")
 * and, if so, processes the request by extracting the triangle's vertices and color from the provided tokens,
 * and then renders the triangle using a {@code Graphics} object.
 * </p>
 *
 * @see ShapeHandler
 * @see DrawingServer
 */
public class TriangleHandler extends ShapeHandler {

    /**
     * Determines whether this handler can process the drawing request based on the shape type.
     * <p>
     * This implementation returns {@code true} if the provided {@code shapeType} equals "TRIANGLE" (ignoring case).
     * </p>
     *
     * @param shapeType the type of the shape to be drawn.
     * @return {@code true} if the shape type is "TRIANGLE", {@code false} otherwise.
     */
    @Override
    protected boolean canHandle(String shapeType) {
        return "TRIANGLE".equalsIgnoreCase(shapeType);
    }

    /**
     * Processes the drawing request for a triangle.
     * <p>
     * This method delegates the drawing operation to the {@link #drawTriangle(String[], Graphics)} method.
     * </p>
     *
     * @param tokens   an array of strings representing the drawing request details.
     *                 Expected tokens include the shape type at index 1, the triangle vertices at subsequent indices,
     *                 and the final token representing the color.
     * @param graphics the {@code Graphics} object used for drawing.
     */
    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawTriangle(tokens, graphics);
    }

    /**
     * Draws a triangle using the specified tokens and {@code Graphics} context.
     * <p>
     * This method parses the vertex coordinates from the tokens array. The x-coordinates of the vertices are
     * extracted from tokens[2], tokens[4], and tokens[6], while the y-coordinates are extracted from tokens[3],
     * tokens[5], and tokens[7]. It then retrieves the desired {@code Color} object via
     * {@link DrawingServer#getColor(String)}, sets the drawing color, and renders the triangle using
     * {@code Graphics.drawPolygon}.
     * </p>
     *
     * @param tokens   an array of strings containing the drawing parameters.
     *                 The vertices are expected to be specified in pairs starting from index 2,
     *                 and the final token represents the color.
     * @param graphics the {@code Graphics} object used to render the triangle.
     */
    private void drawTriangle(String[] tokens, Graphics graphics) {
        System.out.println("Drawing Triangle...");
        int[] xPoints = {
                Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[4]),
                Integer.parseInt(tokens[6])
        };
        int[] yPoints = {
                Integer.parseInt(tokens[3]),
                Integer.parseInt(tokens[5]),
                Integer.parseInt(tokens[7])
        };
        String colorName = tokens[tokens.length - 1].trim().toLowerCase();
        Color color = DrawingServer.getColor(colorName);
        graphics.setColor(color);
        graphics.drawPolygon(xPoints, yPoints, 3);
    }
}
