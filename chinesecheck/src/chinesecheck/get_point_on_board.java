package chinesecheck;

import java.awt.Point;

class get_point_on_board {
    static checkmain c = new checkmain();
    Point pointa = new Point();
    Point pointb = new Point();

    int ptax = 0;
    int ptay = 0;
    int ptbx = 0;
    int ptby = 0;

    public get_point_on_board(Point a, Point b) {
        set(a, b);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                System.err.print("cbp:(" + c.cbPoint[i][j].x + "," + c.cbPoint[i][j].y + ")\t");
            }
            System.err.println();
        }

    }

    int[] getpta() {
        int[] ptapos = new int[2];
        ptapos[0] = ptax;
        ptapos[1] = ptay;
        return ptapos;
    }

    int[] getptb() {
        int[] ptbpos = new int[2];
        ptbpos[0] = ptbx;
        ptbpos[1] = ptby;
        return ptbpos;
    }

    void set(Point a, Point b) {
        pointa = a;
        pointb = b;
    }
}