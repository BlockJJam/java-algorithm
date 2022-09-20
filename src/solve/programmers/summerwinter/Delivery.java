package solve.programmers.summerwinter;

import java.util.Arrays;

public class Delivery {

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[][] graph = new int[N+1][N+1];
        for(int i=0; i<=N; i++){
            Arrays.fill(graph[i], 2_000_000);
        }

        for(int i=0; i<= N; i++){
            graph[i][i] = 0;
        }
        for(int[] roadInfo : road){
            for(int i=0; i<roadInfo.length; i++){
                int a = roadInfo[0];
                int b = roadInfo[1];
                int c = roadInfo[2];

                graph[a][b] = Math.min(graph[a][b], c);
                graph[b][a] = Math.min(graph[b][a], c);
            }
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    graph[i][j] = Math.min(graph[i][j] , graph[i][k] + graph[k][j]);
                }
            }
        }


        for(int i = 1; i<=N; i++){
            // System.out.println("graph = " + graph[1][i]);
            if(graph[1][i] <= K){
                answer++;
            }
        }

        return answer;
    }



    // 도로를 graph를 만들어 담는다
    // 플로이드 워셸 알고리즘을 활용해서 시작지점에서 모든 지점까지의 최단 거리를 구한다
    // graph[시작지점][다른 지점] 의 거리가 K 미만인 개수를 모두 구한다

    // 다익스트라 버전

    static class Graph{

    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][]{ {1,2,1},{1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5, 6, 1} }, 4));
    }
}
