package solve.programmers.monthly_challenge1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTrioMid {
    public static int solution(int n, int[][] edges) {
        /**
         * 하면 안되는 알고리즘: 3개의 노드를 선택해서 노드 간 거리를 모두 구해서 최대인 길이를 구한다 -> O(nC3 * nC2 * BFS연산)
         *
         * [해결방법]
         * 트리라는 점을 이용해서, 두 노드가 가장 긴 경우는 루프노드와 리프노드간의 거리(뎁스)를 활용한다.
         * 물론, 두 노드간의 거리이기 때문에 루프 - 리프만으로는 최대 거리의 두 노드를 구하기는 힘들다
         * 그렇다면, 여기에 추가로 무엇을 봐야 할까? 바로, "트리의 지름"이다.
         * 트리의 지름은 리프노드-리프노드 거리 중에 가장 긴 거리가 된다.(이를 지름으로 원을 만들면 가장 큰 원이 그려지기 때문에 트리의 지름이라 한다)
         * 우리는 3개 노드 간 거리의 중간값 중에 최댓값을 찾는다.
         * 그렇다면 가장 멀리 떨어진 노드 3개를 구해야한다.
         * 특정 지점에서 트리의 지름인 노드가 2개 이상이면, 중간값은 그 사이 어떤 노드를 선택해도 지름이 된다.
         * 특정 지점에서 트리의 지름인 노드가 1개라면, 중간값은 (지름-1)이 된다. <- 노드간의 거리는 1이기 때문에 나머지 떨어진 최대거리의 노드는 지름 -1일 것이기 때문
         *
         * [트리의 지름을 이용한 방법]
         * 임의의 노드 시작점을 1로 잡는다. <- 이는 처음 A를 잡아주기 위함이다.
         * 2. A로부터 가장 먼 노드 B를 찾는다. <- bfs를 이용한다.
         *  2-1. 만약 가장 먼 노드 B가 2개 이상인 경우, 2개를 지정하면 되므로 지름을 리턴한다.
         *  2-2. 만약 가장 먼 노드 B가 1개인 경우 다시 B를 기준으로 시작점을 지정한다.
         * 3. B로부터 가장 먼 노드를 찾는다.
         *  3-1. 가장 먼 노드가 여러개라면, 2개를 지정하면 되기 때문에 지름을 리턴한다.
         *  3-2. 가장 먼  노드가 1개인 경우 지름 - 1을 리턴한다.
        **/

        int answer = 0;

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        // list형태의 graph에 정점간의 관계 넣기
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // graph에서 각 노드까지의 거리구하고, 1번 노드에서 가장 거리가 먼노드 A를 구한다.
        int start = 1, cnt = 1;
        int[] result = bfs(graph, 1, n);

        // A노드의 시작지점 체크
        for(int i = 2; i<n+1; i++){
            if(result[start] < result[i]) {
                // 가장 먼 노드 A를 찾는다.
                start = i;
            }
        }

        result = bfs(graph, start, n);

        // A노드에서 가장 먼 B를 찾고, 같은 길이의 B 개수를 구한다.
        for(int i= 1; i<n+1; i++){
            if(result[start] < result[i]){
                cnt = 1;
                start = i;
            }else if(result[start] == result[i]){
                cnt++;
            }
        }
//        System.out.println("result = " + result[start] + " cnt = "+ cnt);
        int max = result[start];
        // 가장 먼 노드 A 개수가 2개 이상인지 확인한다. 2개 이상이면
        if(cnt >= 2) return max;

        // A노드 개수가 1개라면, 다시 B노드를 찾는다.
        cnt = 1;
        result = bfs(graph, start, n);
        for(int i=1; i<n+1; i++){
            if(result[start] < result[i]){
                // B노드를 찾는다.
                cnt = 1;
                start = i;
            }else if(result[start] == result[i]){
                cnt++;
            }
        }
        // B노드가 2개 이상이라면 지름을 return하고, 1개라면 지름 - 1을 리턴
//        System.out.println("result = " + result[start] + " cnt = "+ cnt);
        if(cnt >= 2) return result[start];

        max = Math.max(max, result[start]);
        // S -> A까지의 거리와 A -> B까지의 거리 중에 길었던 거리를 기준으로 지름 -1을 리턴한다.
        return max - 1;
    }

    static int[] bfs(List<Integer>[] graph, int start, int n){
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int num: graph[curr]){
                if(!visited[num]){
                    visited[num] = true;
                    q.offer(num);
                    dist[num] = dist[curr] + 1;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
//        solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}});
        solution(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}});

    }
}
