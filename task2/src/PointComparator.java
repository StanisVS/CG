import java.util.Comparator;

/**
 * Created by s on 30.09.14.
 */
public class PointComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        if(o1.x<o2.x){
            return -1;
        }
        if(o1.x>o2.x){
            return 1;
        }

        if(o1.y<o2.y){
            return -1;
        }
        return 0;
    }
}
