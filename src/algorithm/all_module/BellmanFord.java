package algorithm.all_module;

/**
 * 최단 경로를 찾는데 다익스트라와 다른 점은 음수 경로를 체크한다
 * 최단 경로문제는 도시가 있고, 이동을 하는데 해당 도시로 가는데 가장 빠른 시간을 구하는 (경로문제)
 */
public class BellmanFord {

    static class Bus{
        int u;
        int v;
        int cost;

        public Bus(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }


    static int V;
    static int E;
    static long INF = Long.MAX_VALUE;
    public static void bellmanFord(){
        Bus[] bus = new Bus[E];

        long[] dist = new long[V+1];

        for(int i=1; i<V+1; i++){
            dist[i] = INF;
        }

        int start = 0;
        dist[start] = 0;

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int from = bus[i].u;
                int to = bus[i].v;
                int cost = bus[i].cost;

                if(dist[from] != INF){
                    if(dist[from] + cost < dist[to]){
                        dist[to] = dist[from] + cost;

                        if(i == V -1){
                            // 순환일 때 로직
                            return;
                        }
                    }
                }
            }
        }

        // 순환이 아니고 최단 경로를 찾았을 때 로직
    }

}
