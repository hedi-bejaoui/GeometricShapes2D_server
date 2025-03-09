import java.awt.*;

/**
 * Interface for handling drawing requests for shapes.
 * <p>
 * This interface is part of the Chain of Responsibility pattern used in the project.
 * Implementing classes are responsible for processing drawing requests based on the shape type.
 * If a handler cannot process a request, it should delegate it to the next handler.
 * </p>
 *
 * @see ShapeHandler
 */
public interface Handler {
    /**
     * Sets the next handler in the chain of responsibility.
     *
     * @param nextHandler the next {@code Handler} to process the request if the current handler cannot.
     */
    void setNextHandler(Handler nextHandler);

    /**
     * Handles a drawing request for a shape.
     * <p>
     * The request is represented by an array of tokens, where the second token is expected to specify
     * the shape type. The provided {@code Graphics} object is used for drawing the shape.
     * </p>
     *
     * @param tokens   an array of strings representing the drawing request details.
     * @param graphics the {@code Graphics} object used for rendering the shape.
     */
    void handleRequest(String[] tokens, Graphics graphics);
}
