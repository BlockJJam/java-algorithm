package algorithm.all_module;

public class FenwickTree {
    static long[] tree;

    FenwickTree(int n){
        tree = new long[n + 1];
    }

    public static void update(int idx, int value){
        while(idx < tree.length){
            tree[idx] += value;
            idx += (idx & -idx);
        }
    }

    public static long getSum(int idx){
        long result = 0;
        while(idx > 0){
            result += tree[idx];
            idx -= (idx & -idx);
        }

        return result;
    }
}
