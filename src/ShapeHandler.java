import java.awt.*;

/**
 * Abstract class that implements the {@code Handler} interface to handle shape drawing requests.
 * <p>
 * This class is part of the Chain of Responsibility pattern. It defines the basic behavior
 * for handling a request. Each concrete handler must implement the {@code canHandle} and {@code process}
 * methods to determine whether it can handle a specific shape type and to perform the drawing operation, respectively.
 * </p>
 *
 * @see Handler
 */
public abstract class ShapeHandler implements Handler {
    /**
     * The next handler in the chain. If the current handler is unable to process the request,
     * the request is delegated to this handler.
     */
    protected Handler nextHandler;

    /**
     * Sets the next handler in the chain.
     *
     * @param nextHandler the next {@code Handler} to handle the request if this handler cannot.
     */
    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Handles a drawing request by delegating to the appropriate handler in the chain.
     * <p>
     * The request is represented by an array of tokens and a {@code Graphics} object.
     * The second token (tokens[1]) is expected to specify the shape type.
     * If the current handler can handle the shape type, the {@code process} method is invoked;
     * otherwise, the request is passed to the next handler in the chain. If no handler can process
     * the request, a message is printed indicating that the shape is unsupported.
     * </p>
     *
     * @param tokens   an array of strings representing the drawing request. The shape type is expected at index 1.
     * @param graphics the {@code Graphics} object used for drawing the shape.
     */
    @Override
    public void handleRequest(String[] tokens, Graphics graphics) {
        if (canHandle(tokens[1])) {
            process(tokens, graphics);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(tokens, graphics);
        } else {
            System.out.println("Unsupported shape: " + tokens[1]);
        }
    }

    /**
     * Determines whether this handler can process a drawing request for the specified shape type.
     *
     * @param shapeType the type of the shape (e.g., "circle", "triangle") to be handled.
     * @return {@code true} if this handler can process the shape; {@code false} otherwise.
     */
    protected abstract boolean canHandle(String shapeType);

    /**
     * Processes the drawing request for a shape and renders it using the provided {@code Graphics} object.
     * <p>
     * This method should perform the actual drawing of the shape based on the tokens in the request.
     * </p>
     *
     * @param tokens   an array of strings containing the drawing request details.
     * @param graphics the {@code Graphics} object used to draw the shape.
     */
    protected abstract void process(String[] tokens, Graphics graphics);
}
