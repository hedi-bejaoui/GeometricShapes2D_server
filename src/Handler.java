import java.awt.*;

public interface Handler {
    void setNextHandler(Handler nextHandler);
    void handleRequest(String[] tokens, Graphics graphics);
}
