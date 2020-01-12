package chinesecheck;

import java.awt.Point;

class get_point_on_board {
    Point pointa = new Point();
    Point pointb = new Point();

    public get_point_on_board(Point a, Point b) {
        set(a, b);
    }

    Point getpointa() {
        return pointa;
    }

    void set(Point a, Point b) {
        pointa = a;
        pointb = b;
    }
}