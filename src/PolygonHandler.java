import java.awt.*;

import static javax.swing.UIManager.getColor;

/**
 * The {@code PolygonHandler} class is responsible for handling drawing requests for polygons.
 * <p>
 * This class extends {@link ShapeHandler} as part of the Chain of Responsibility pattern.
 * It determines if a given drawing request is for a polygon (identified by the token "POLYGONE")
 * and, if so, processes the request by extracting the polygon's vertices and color from the provided tokens,
 * and then renders the polygon using a {@code Graphics} object.
 * </p>
 *
 * @see ShapeHandler
 * @see DrawingServer
 */
public class PolygonHandler extends ShapeHandler {

    /**
     * Determines whether this handler can process the drawing request based on the shape type.
     * <p>
     * This implementation returns {@code true} if the provided {@code shapeType} equals "POLYGONE"
     * (ignoring case).
     * </p>
     *
     * @param shapeType the type of the shape to be drawn.
     * @return {@code true} if the shape type is "POLYGONE", {@code false} otherwise.
     */
    @Override
    protected boolean canHandle(String shapeType) {
        return "POLYGONE".equalsIgnoreCase(shapeType);
    }

    /**
     * Processes the drawing request for a polygon.
     * <p>
     * This method delegates the drawing operation to the {@link #drawPolygon(String[], Graphics)} method.
     * </p>
     *
     * @param tokens   an array of strings representing the drawing request details.
     *                 The tokens are expected to contain the vertices and final color information.
     * @param graphics the {@code Graphics} object used for drawing the polygon.
     */
    @Override
    protected void process(String[] tokens, Graphics graphics) {
        drawPolygon(tokens, graphics);
    }

    /**
     * Draws a polygon using the specified tokens and {@code Graphics} context.
     * <p>
     * The method calculates the number of vertices by examining the tokens array.
     * It then extracts the x and y coordinates of each vertex and stores them in integer arrays.
     * Finally, it retrieves the desired color using {@link DrawingServer#getColor(String)},
     * sets the drawing color, and renders the polygon using {@code Graphics.drawPolygon}.
     * </p>
     *
     * @param tokens   an array of strings containing the drawing parameters.
     *                 The vertices are expected to start at index 2, and the last token represents the color.
     * @param graphics the {@code Graphics} object used to render the polygon.
     */
    private void drawPolygon(String[] tokens, Graphics graphics) {
        System.out.println("Drawing Segment..."); // Debug message; may be a misnomer for polygon
        int n = (tokens.length - 2) / 2;
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];
        for (int i = 0; i < n; i++) {
            xPoints[i] = Integer.parseInt(tokens[2 + i * 2]);
            yPoints[i] = Integer.parseInt(tokens[3 + i * 2]);
        }
        String colorName = tokens[tokens.length - 1].trim().toLowerCase();
        Color color = DrawingServer.getColor(colorName);

        System.out.println(color);
        graphics.setColor(color);
        graphics.drawPolygon(xPoints, yPoints, n);
    }
}
