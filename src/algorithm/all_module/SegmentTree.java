package algorithm.all_module;

public class SegmentTree {
    private long[] tree;
    private int startIdx;
    private int endIdx;
    private int NODE_START = 1;

    SegmentTree(int n, int start, int end){
        double treeHeight = Math.ceil(Math.log(n)/ Math.log(2)) +1;
        long treeNodeCnt = Math.round(Math.pow(2, treeHeight));

        tree = new long[Math.toIntExact(treeNodeCnt)];
        this.startIdx = start;
        this.endIdx = end;
    }

    long init(long[] arr, int node, int start, int end){
        if(start == end){
            return tree[node] = arr[start];
        }else{
            int mid = (start + end)/2;
            return tree[node] = init(arr, node*2, start, mid)+
                    init(arr, node*2 + 1, mid + 1, end);
        }
    }

    long sum(int node, int start, int end, int left, int right){
        if(end < left || right < start) {
            return 0;
        }else if(left <= start && end <= right){
            return tree[node];
        }else{
            int mid = (start + end)/2;
            return sum(node *2, start, mid, left, right) +
                    sum(node * 2 +1, mid+1, end, left, right);
        }
    }

    long sum(int left, int right){
        return sum(NODE_START, startIdx, endIdx, left, right);
    }

    void updateByDiff(int node, int start, int end, int index, int diff){
        if(index < start || index > end){
            return;
        }

        tree[node] = tree[node] + diff;

        if(start != end){
            int mid = (start + end)/2;
            updateByDiff(node *2, start, mid, index, diff);
            updateByDiff(node * 2 + 1, mid + 1, end, index, diff);
        }
    }

    long updateByValue(int node, int start, int end, int index, int value){
        if(index < start || index > end){
            return tree[node];
        }

        if(start == index && end == index){
            return tree[node] = value;
        }{
            int mid = (start + end)/2;
            return tree[node] = updateByValue(node * 2, start, mid, index, value)
                    + updateByValue(node * 2 + 1, mid + 1, end, index, value);
        }
    }

    void update(int index, int value){
        updateByDiff(NODE_START, startIdx, endIdx, index, value);
    }
}
