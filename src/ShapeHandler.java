import java.awt.*;

public abstract class ShapeHandler implements Handler {
    protected Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

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

    protected abstract boolean canHandle(String shapeType);

    protected abstract void process(String[] tokens, Graphics graphics);
}