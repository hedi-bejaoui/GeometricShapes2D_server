import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class
DrawingServer {
    private static final Map<String, Color> colorMap = new HashMap<>();

    static {
        colorMap.put("red", Color.RED);
        colorMap.put("green", Color.GREEN);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("yellow", Color.YELLOW);
        colorMap.put("black", Color.BLACK);
        colorMap.put("cyan", Color.CYAN);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is running...");

        Frame frame = new Frame("Drawing Server");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        BufferStrategy bufferStrategy = frame.getBufferStrategy();

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

    private static void processRequest(String request, Graphics graphics) {
            System.out.println("Received: " + request);
            String[] tokens = request.split(" ");

            // Création des handlers
            Handler circleHandler = new CircleHandler();
            Handler segmentHandler = new SegmentHandler();
            Handler triangleHandler = new TriangleHandler();
            Handler polygonHandler = new PolygonHandler();

            // Chainer les handlers
            circleHandler.setNextHandler(segmentHandler);
            segmentHandler.setNextHandler(triangleHandler);
            triangleHandler.setNextHandler(polygonHandler);

            // Lancer le traitement
            circleHandler.handleRequest(tokens, graphics);
        }



    private static Color getColor(String colorName) {
        return colorMap.getOrDefault(colorName.toLowerCase(), Color.BLACK);
    }
}
























        /*
        System.out.println("Received: " + request);
        String[] tokens = request.split(" ");
        switch (tokens[1]) {
            case "CIRCLE":
                drawCircle(tokens, graphics);
                break;
            case "SEGMENT":
                drawSegment(tokens, graphics);
                break;
            case "TRIANGLE":
                drawTriangle(tokens, graphics);
                break;
            case "POLYGONE":
                drawPolygon(tokens, graphics);
                break;
            default:
                System.out.println("Unsupported shape: " + tokens[1]);
        }*/

    /*private static void drawCircle(String[] tokens, Graphics graphics) {
        int cx = Integer.parseInt(tokens[2]);
        int cy = Integer.parseInt(tokens[3]);
        int radius = Integer.parseInt(tokens[4]);
        Color color = getColor(tokens[5]);
        graphics.setColor(color);
        graphics.drawOval(cx - radius, cy - radius, radius * 2, radius * 2);
    }

    private static void drawSegment(String[] tokens, Graphics graphics) {
        int x1 = Integer.parseInt(tokens[2]);
        int y1 = Integer.parseInt(tokens[3]);
        int x2 = Integer.parseInt(tokens[4]);
        int y2 = Integer.parseInt(tokens[5]);
        Color color = getColor(tokens[6]);
        graphics.setColor(color);
        graphics.drawLine(x1, y1, x2, y2);
    }

    private static void drawTriangle(String[] tokens, Graphics graphics) {
        int[] xPoints = {Integer.parseInt(tokens[2]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[6])};
        int[] yPoints = {Integer.parseInt(tokens[3]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[7])};
        Color color = getColor(tokens[8]);
        graphics.setColor(color);
        graphics.drawPolygon(xPoints, yPoints, 3);
    }

    private static void drawPolygon(String[] tokens, Graphics graphics) {
        int n = (tokens.length - 2) / 2;
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];
        for (int i = 0; i < n; i++) {
            xPoints[i] = Integer.parseInt(tokens[2 + i * 2]);
            yPoints[i] = Integer.parseInt(tokens[3 + i * 2]);
        }
        Color color = getColor(tokens[tokens.length - 1]);
        graphics.setColor(color);
        graphics.drawPolygon(xPoints, yPoints, n);
    }
*/