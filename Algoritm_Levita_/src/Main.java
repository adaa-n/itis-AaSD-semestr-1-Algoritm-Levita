import java.util.*;

public class Main {

    public static void main(String[] args) {

        int n = 5;

        List<List<LevitWithMetrics.Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // создаём простой граф
        graph.get(0).add(new LevitWithMetrics.Edge(1, 2));
        graph.get(0).add(new LevitWithMetrics.Edge(2, 4));
        graph.get(1).add(new LevitWithMetrics.Edge(2, 1));
        graph.get(1).add(new LevitWithMetrics.Edge(3, 7));
        graph.get(2).add(new LevitWithMetrics.Edge(4, 3));
        graph.get(3).add(new LevitWithMetrics.Edge(4, 1));

        // запускаем алгоритм
        LevitWithMetrics.Result result =
                LevitWithMetrics.levit(n, graph, 0);

        // вывод результатов
        System.out.println("Кратчайшие расстояния от вершины 0:");

        for (int i = 0; i < result.dist.length; i++) {
            System.out.println("0 -> " + i + " = " + result.dist[i]);
        }

        System.out.println("Итерации: " + result.iterations);
        System.out.println("Время (нс): " + result.timeNs);
    }
}