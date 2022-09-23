package algorithm.all_module;

/**
 * 모든 위치의 start ~ end 지점까지의 최단 경로를 담는다.
 * 경유하지 않는 경우부터 중간에 경유하는 모든 경우를 적용하여 최단 경로를 구한다.
 * N의 크기가 너무 큰 경우, 속도가 O(N^3)이기 때문에 시간 초과를 조심해야 한다.
 */
public class FloydWarshell {
    static int[][] dist;
    static int N;

    public void floyd(){
        for(int k = 0; k < N; k++){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
