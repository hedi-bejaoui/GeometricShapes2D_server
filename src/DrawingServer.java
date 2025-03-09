import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code DrawingServer} class implements a TCP server that receives drawing commands and renders shapes.
 * <p>
 * This server listens on port 8080 for incoming connections. When a request is received, it processes the request
 * using a chain of {@link Handler} implementations (for circles, segments, triangles, and polygons) and renders
 * the shape on a fixed-size drawing frame. The frame's size is locked by setting it non-resizable.
 * </p>
 *
 * <p>
 * Design Patterns used:
 * <ul>
 *   <li><b>Chain of Responsibility:</b> For delegating drawing requests to the appropriate handler.</li>
 *   <li><b>Singleton:</b> The color mapping is managed via a static initializer.</li>
 * </ul>
 * </p>
 */
public class DrawingServer {
    private static final Map<String, Color> colorMap = new HashMap<>();

    // Initialize the color mapping.
    static {
        colorMap.put("red", Color.RED);
        colorMap.put("green", Color.GREEN);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("yellow", Color.YELLOW);
        colorMap.put("black", Color.BLACK);
        colorMap.put("cyan", Color.CYAN);
    }

    /**
     * The main method initializes the drawing frame and starts the server socket to listen for drawing requests.
     * <p>
     * The frame is created with a fixed size (1000x800) and is set as non-resizable to block any modification
     * to its size. A double-buffered strategy is used to render the shapes.
     * </p>
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs when opening the server socket.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is running...");

        // Create a drawing frame with a fixed size and block any modifications to its size.
        Frame frame = new Frame("Drawing Server");
        frame.setSize(1000, 800);
        frame.setResizable(false); // Block any modification to the frame's size.
        frame.setVisible(true);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        BufferStrategy bufferStrategy = frame.getBufferStrategy();

        // Main loop: process incoming drawing requests.
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String request = in.readLine();
                Graphics graphics = bufferStrategy.getDrawGraphics();
                processRequest(request, graphics);
                bufferStrategy.show();
                graphics.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Processes a drawing request by delegating it through a chain of {@link Handler} instances.
     * <p>
     * The request is a space-delimited string containing the shape type and parameters. Handlers for circles,
     * segments, triangles, and polygons are created and chained together. The request is passed to the first
     * handler in the chain.
     * </p>
     *
     * @param request  The drawing request as a string.
     * @param graphics The {@code Graphics} object used for rendering the shape.
     */
    private static void processRequest(String request, Graphics graphics) {
        System.out.println("Received: " + request);
        String[] tokens = request.split(" ");

        // Create handlers for different shapes.
        Handler circleHandler = new CircleHandler();
        Handler segmentHandler = new SegmentHandler();
        Handler triangleHandler = new TriangleHandler();
        Handler polygonHandler = new PolygonHandler();

        // Chain the handlers.
        circleHandler.setNextHandler(segmentHandler);
        segmentHandler.setNextHandler(triangleHandler);
        triangleHandler.setNextHandler(polygonHandler);

        // Process the request starting with the circle handler.
        circleHandler.handleRequest(tokens, graphics);
    }

    /**
     * Retrieves the {@code Color} object corresponding to the given color name.
     * <p>
     * If the specified color name is not found in the color map, {@code Color.BLACK} is returned by default.
     * </p>
     *
     * @param colorName The name of the color.
     * @return The corresponding {@code Color} object, or {@code Color.BLACK} if not found.
     */
    static Color getColor(String colorName) {
        return colorMap.getOrDefault(colorName.toLowerCase(), Color.BLACK);
    }
}
