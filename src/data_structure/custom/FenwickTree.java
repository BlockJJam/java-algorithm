package data_structure.custom;

public class FenwickTree {
    public static long[] tree;

    public static void update(int idx, long diffValue){
        while(idx < tree.length){
            tree[idx] += diffValue;
            idx += (idx & -idx);
        }
    }

    public static long getSumToIndexRangeValue(int idx){
        long result = 0;
        while(idx > 0){
            result += tree[idx];
            idx -= (idx & -idx);
        }

        return result;
    }
}
