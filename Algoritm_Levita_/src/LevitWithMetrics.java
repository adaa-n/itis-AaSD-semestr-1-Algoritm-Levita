import java.io.File;
import java.util.*;

public class LevitWithMetrics {

    // Ребро графа
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Результат работы алгоритма
    static class Result {
        int[] dist;       // массив расстояний от стартовой вершины
        long iterations;  // количество итераций
        long timeNs;      // время работы алгоритма

        Result(int[] dist, long iterations, long timeNs) {
            this.dist = dist;
            this.iterations = iterations;
            this.timeNs = timeNs;
        }
    }


    public static Result levit(int n,  // количество вершин
                               List<List<Edge>> graph, // список смежности
                               int start // стартовая вершина
    ) {

        // массив расстояний (изначально бесконечность)
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        /*
         - Состояния вершин:
         0 — вершина ещё не посещалась (m2)
         1 — вершина в очереди (m1)
         2 — вершина полностью обработана (m0)
         */

        int[] state = new int[n];


        Deque<Integer> deque = new ArrayDeque<>();

        // счётчик итераций
        long iterations = 0;

        // начальная вершина
        dist[start] = 0;
        deque.add(start);
        state[start] = 1; // поместили в очередь

        // счетчик времени
        long startTime = System.nanoTime();

        // пока есть вершины для обработки
        while (!deque.isEmpty()) {

            // берём вершину из начала очереди
            int v = deque.pollFirst();

            // помечаем как полностью обработанную
            state[v] = 2;

            // перебираем все исходящие рёбра из нее
            for (Edge edge : graph.get(v)) {

                iterations++; // считаем каждую проверку ребра

                int u = edge.to;
                int newDist = dist[v] + edge.weight;

                // если нашли более короткий путь
                if (newDist < dist[u]) {

                    dist[u] = newDist;

                    // Случай 1: вершина ещё не посещалась (m2)
                    if (state[u] == 0) {
                        deque.addLast(u);  // добавляем в конец
                        state[u] = 1;
                    }

                    // Случай 2: вершина уже была обработана (m0)
                    else if (state[u] == 2) {
                        deque.addFirst(u); // добавляем в начало (приоритет)
                        state[u] = 1;
                    }

                    // Случай 3: вершина уже в очереди (m1)
                    // ничего делать не нужно, она уже будет обработана
                }
            }
        }

        long endTime = System.nanoTime();

        return new Result(dist, iterations, endTime - startTime);
    }


    // чтение графа из файла
    public static List<List<Edge>> readGraph(String filename, int[] nHolder) throws Exception {

        Scanner sc = new Scanner(new File(filename));

        int n = sc.nextInt();
        int m = sc.nextInt();
        nHolder[0] = n;

        // создаём список смежности
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // читаем рёбра
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph.get(u).add(new Edge(v, w));
        }

        return graph;
    }
}