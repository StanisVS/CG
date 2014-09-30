

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by s on 30.09.14.
 */
public class Point {
    static int counter = 0;

    final int x;
    final int y;
    final int n;
    public boolean isSuper = false;
    private boolean chain = false;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        n = counter++;
    }


    public boolean isLowChain() {
        return !chain;
    }

    public boolean sameChain(Point p) {
        return chain==p.chain || isSuper;
    }

    private static int vectorSign(Point p1, Point p2, Point p3) {
        return dX(p1, p2) * dY(p2, p3) - dY(p1, p2) * dX(p2, p3);
    }

    public static boolean isEar(Point p1, Point p2, Point p3) {
        int sign = vectorSign(p1, p2, p3);
        if (p2.isLowChain()) {
            return sign > 0;
        } else {
            return sign < 0;
        }
    }

    private static int dX(Point p1, Point p2) {
        return p2.x - p1.x;
    }

    private static int dY(Point p1, Point p2) {
        return p2.y - p1.y;
    }

    public void setChain(boolean chain) {
        this.chain = chain;
    }

    private boolean isRightTo(Point other) {
        return this.x >= other.x;
    }

    public Point(String s) {
        if (s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') {
            throw new InputMismatchException();
        }
        s = s.substring(1, s.length() - 1);
        String[] splitted = s.split(",");
        if (splitted.length < 2) {
            throw new InputMismatchException();
        }
        x = Integer.parseInt(splitted[0].trim());
        y = Integer.parseInt(splitted[1].trim());
        n = counter++;
    }

    @Override
    public String toString() {
        return String.format("(x = %s, y = %s, № = %s,super = %b, chain = %b)", x, y, n, isSuper, chain);
    }

    public static class PointReader {
        public static List<Point> readPoints() {
            List<Point> points = Collections.emptyList();
            try (InputStream is = System.in; InputStreamReader isr = new InputStreamReader(is); BufferedReader reader = new BufferedReader(isr);) {
                points = new ArrayList<>();
                String line = reader.readLine().trim();
                int numberOfPoints = Integer.valueOf(line);
                for (int i = 0; i < numberOfPoints; ++i) {
                    line = reader.readLine();
                    Point p = new Point(line);
                    points.add(p);
                }

                points.get(0).isSuper = true;
                int i = 1;
                for (; points.get(i).isRightTo(points.get(i - 1)); ++i) {
                }
                points.get(i - 1).isSuper = true;
                for (; i < numberOfPoints; ++i) {
                    points.get(i).setChain(true);
                }
                Collections.sort(points, new PointComparator());
            } catch (FileNotFoundException e) {
                System.err.println("file 'input.txt' not found");
                System.err.println(e.getMessage());
            } catch (IOException e) {
                System.err.println("failed on reading 'input txt'");
            } catch (NumberFormatException | InputMismatchException e) {
                System.err.println("wrong format");
            }
            return points;
        }
    }
}
