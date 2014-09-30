import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by s on 30.09.14.
 */
public class Triangle {
    private List<Integer> points = new ArrayList<>();
    public Triangle (Point p1,Point p2, Point p3){
        points.add(p1.n);
        points.add(p2.n);
        points.add(p3.n);
        Collections.sort(points);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)",points.toArray(new Integer[]{}));
    }
}
