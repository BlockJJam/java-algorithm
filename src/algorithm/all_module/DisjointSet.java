package algorithm.all_module;

import java.util.Arrays;

/**
 * set은 리스트와 달리 순서를 고려하지 않는다
 * A모든 원소가 B에 포함 될 때, A를 B의 부분집합이라 한다.(B는 superSet)
 * 공유하는 원소가 없으면, A와 B는 mutual하다
 * 분할한다는 것 = 각 부분집합이 1) 다시 합치면 원래의 set으로 된다. 2) 각 부분집합이 mutual하다
 *
 */
public class DisjointSet {
    int[] parent;
    int[] rank;

    public DisjointSet(int n){
        initParent(n);
        initRank(n);
    }

    public int find(int n){
        if(n == parent[n]){
            return n;
        }

        return parent[n] = find(parent[n]);
    }

    public void merge(int u1, int u2){
        u1 = find(u1);
        u2 = find(u2);

        if(u1 == u2)
            return;

        if(rank[u2] <= rank[u1]){
            parent[u2]= u1;
        }else{
            parent[u1] = u2;
        }

        if(rank[u1] == rank[u2]){
            rank[u1]++;
        }
    }

    public void initParent(int n){
        parent = new int[n+1];
        for (int i = 1; i < n + 1; i++) {

            parent[i] = i;
        }
    }

    public void initRank(int n){
        rank = new int[n + 1];
        Arrays.fill(rank, 0);
    }

}
