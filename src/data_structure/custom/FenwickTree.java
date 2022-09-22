package data_structure.custom;

public class FenwickTree {
    public static long[] tree;

    public static void update(int idx, long diffValue){
        while(idx < tree.length){
            tree[idx] += diffValue;
            idx += (idx & -idx); // 비트 연산자로, 2의 보수( 1의 보수(0->1, 1->0) + 1)
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
