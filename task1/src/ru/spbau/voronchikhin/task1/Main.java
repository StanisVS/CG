package ru.spbau.voronchikhin.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by s on 16.09.14.
 */

public class Main {
    public static void main(String[] args) {

        try (InputStream is = System.in; InputStreamReader isr = new InputStreamReader(is); BufferedReader reader = new BufferedReader(isr);) {
            List<Edge> edges = readEdges(reader);
            listPrint(edges);
            List<Point> points = readPoints(reader);
            listPrint(points);
            algortihm(points, edges);
        } catch (FileNotFoundException e) {
            System.err.println("file 'input.txt' not found");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("failed on reading 'input txt'");
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println("wrong format");
        }
    }

    private static List<Point> readPoints(BufferedReader reader) throws IOException {
        List<Point> points = new ArrayList<>();
        String line = reader.readLine();
        Integer pointNumber = Integer.valueOf(line);
        for (int i = 0; i < pointNumber; ++i) {
            line = reader.readLine();
            Point p = new Point(line);
            points.add(p);
        }
        return points;
    }

    private static List<Edge> readEdges(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        Integer pointNumber = Integer.valueOf(line);
        line = reader.readLine();
        Point p1 = new Point(line);
        Point p2;
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i < pointNumber; ++i) {
            line = reader.readLine();
            p2 = new Point(line);
            edges.add(new Edge(p1, p2));
            p1 = p2;
        }
        edges.add(new Edge(p1, edges.get(0).p1));
        return edges;
    }

    private static class Point implements Comparable<Point> {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
        }

        @Override //mean upper
        public int compareTo(Point o) {
            return this.y - o.y;
        }

        @Override
        public String toString() {
            return String.format("(%s,%s)", x, y);
        }
    }

    private static class Edge {
        private final Point p1;
        private final Point p2;

        private Edge(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public String toString() {
            return String.format("%s-%s", p1, p2);
        }

        public boolean isCrossing(Point p) {
            if (p1.y == p2.y) {
                if (this.p1.y == p.y && this.p1.x >= p.x && p2.x> p.x) {
                    return true;
                }
            }

            if (p1.compareTo(p) * p2.compareTo(p) < 0) {//edge crosses horizontal line through the point
                return isRightCross(this, p);
            }else {
                if(p1.x==p.x && p1.y==p.y){
                    return true;
                }
            }
            return false;
        }

        private boolean isRightCross(Edge edge, Point p) {
            if (edge.dY() < 0) {
                edge = new Edge(edge.p2, edge.p1);
            }

            Edge otherEdge = new Edge(edge.p1, p);
            return vectorSign(edge, otherEdge);
        }

        private int dX() {
            return p2.x - p1.x;
        }

        private int dY() {
            return p2.y - p1.y;
        }


    }

    private static <T> void listPrint(List<T> list) {
        for (T smth : list) {
            System.out.print(smth + ",");
        }
        System.out.println("");
    }


    private static void algortihm(List<Point> points, List<Edge> edges) {
        for (Point p : points) {
            int crosses = 0;
            for (Edge edge : edges) {
                if (edge.isCrossing(p)) {
                    ++crosses;
                }
            }
            if (crosses % 2 == 1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean vectorSign(Edge edge1, Edge edge2) {
        return edge1.dX() * edge2.dY() - edge1.dY() * edge2.dX() > 0;
    }
}


