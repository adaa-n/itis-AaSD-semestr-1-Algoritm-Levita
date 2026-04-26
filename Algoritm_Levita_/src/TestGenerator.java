

import java.io.*;
import java.util.*;

public class TestGenerator {

    static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        for (int t = 1; t <= 100; t++) {
            int n = 100 + rand.nextInt(9900);
            int m = n * 2;

            PrintWriter out = new PrintWriter("test" + t + ".txt");

            out.println(n + " " + m);

            // 1. гарантируем связность
            for (int i = 0; i < n - 1; i++) {
                int w = rand.nextInt(10) + 1;
                out.println(i + " " + (i + 1) + " " + w);
            }

            // 2. добавляем остальные рёбра
            for (int i = 0; i < m - (n - 1); i++) {
                int u = rand.nextInt(n);
                int v = rand.nextInt(n);
                int w = rand.nextInt(20);

                out.println(u + " " + v + " " + w);
            }

            out.close();
        }
    }





}