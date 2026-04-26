import java.io.*;



public class Runner {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter("results.csv");

        out.println("n, iterations, time_ns");

        for (int t = 1; t <= 100; t++) {
            int[] nHolder = new int[1];

            var graph = LevitWithMetrics.readGraph("test" + t + ".txt", nHolder);
            int n = nHolder[0];

            var result = LevitWithMetrics.levit(n, graph, 0);
            out.println(n + ", " + result.iterations + ", " + result.timeNs);
        }

        out.close();
    }
}