import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by s on 30.09.14.
 */
public class Main {
    public static void main(String[] args) {
        List<Point> points = Point.PointReader.readPoints();
        LinkedList<Point> stack = new LinkedList<>();
        List<Triangle> triangles = new ArrayList<>();
        stack.addLast(points.get(0));
        stack.addLast(points.get(1));
        for (Point p : points.subList(2, points.size())) {
            if (stack.getLast().sameChain(p)) {
                while (stack.size() > 1 && Point.isEar(stack.get(stack.size() - 2), stack.getLast(), p)) {
                    triangles.add(new Triangle(stack.get(stack.size() - 2), stack.getLast(), p));
                    stack.removeLast();
                }
            } else {
                while (stack.size() > 1) {
                    triangles.add(new Triangle(stack.getFirst(), stack.get(1), p));
                    stack.removeFirst();
                }
                stack.getLast().isSuper = true;
            }
            stack.add(p);
        }
        for (Triangle t : triangles) {
            System.out.println(t);
        }

    }


}
