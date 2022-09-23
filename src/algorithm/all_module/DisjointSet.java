package algorithm.all_module;

import java.util.Arrays;

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
