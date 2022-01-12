package data_structure.custom;

/** Segment Tree**/
// 1) 배열의 구간안에서 operation이 필요할 때 사용
// 2) 특정 인덱스의 값이 변경이 일어나고도 다시 구간의 operation을 활용할 때도 시간복잡도에 저갑
// 3) start = 1, end = 10 의 구간 합을 연산하는 문제 -> 단순 for문은 N만큼의 시간 / -> segment Tree 는 O(logN)만큼
// leaf Node: 배열의 그 수 자체
// 다른 Node: 왼쪽 자식과 오른쪽 자식의 합을 저장

public class SegmentTree {
    private long[] tree;
    private int startIdx;
    private int endIdx;
    private int NODE_START = 1;

    // 전체 노드개수 구하기, param: 1) n = 전체 노드수 계산을 위한 배열크기
    SegmentTree(int n, int start, int end){
            double treeHeight= Math.ceil(Math.log(n)/Math.log(2)) +1;
            long treeNodeCnt = Math.round(Math.pow(2, treeHeight));
            tree = new long[Math.toIntExact(treeNodeCnt)];
            this.startIdx= start;
            this.endIdx = end;
    }

    // 세그먼트 트리의 노드 값 초기화(구간 크기 단위(2^) 별로 구간 합을 미리 초기화 한다
    // node : 1부터
    // start: SegmentTree에 담길 배열의 시작 index
    // end: SegmentTree에 담길 배열의 끝 index
    long init(long[] arr, int node, int start, int end){
        //leaf node인 경우
        if(start == end){
            return tree[node] = arr[start];
        }else{ // 자식 노드가 남아있는 경우 값을 더해서 노드의 값을 초기화 후 리턴
            return tree[node] =  init(arr, node*2, start, (start + end)/2)
                    + init(arr, node*2+1, (start+end)/2 +1, end);
        }
    }

    // 배열 합 구하기
    // node: tree의 node 번호
    // start & end: 노드가 가진 합의 시작&끝 index
    // left & right: 구하고 싶은 합의 구간의 왼쪽 & 오른쪽
    long sum(int node, int start, int end, int left, int right){
        // 노드가 가지는 값의 구간안에 start와 end가 속하지 않는 경우
        if(end < left || right < start){
            return 0;
        }else if(left <= start && end <= right){
            // 노드가 가지는 값의 구간의 시작과 끝 구간이 <구하려고 하는 합의 구간>에 속하는 경우 노드의 값 리턴
            return tree[node];
        }else{
            // 1. 노드가 가진 구간 중에 left와 right 중 일부만 속하고 일부는 속하지 않음
            // 2. 노드가 가진 구간이 left와 right 을 모두 포함하는 경우
            return sum(node*2, start, (start + end)/2, left, right)
                    + sum( node*2+1, (start + end)/2 +1, end, left, right);
        }
    }

    long sum(int left, int right){
        return sum(NODE_START, startIdx, endIdx, left, right);
    }

    // 특정 인덱스를 변경하고 싶은 경우
    // start & end & node : sum과 동일
    // index: 변경하고자 하는 배열 원소의 위치
    // diff: 변경 후 값 - 변경 전 기존값 = 차이
    void updateByDiff(int node, int start, int end, int index, long diff){
        if(index < start || end < index){
            return;
        }

        // 노드의 구간 합 값 + 변동 차이값
        tree[node] = tree[node] + diff;

        // 리프노드가 아닐 때까지!
        if(start != end){
            updateByDiff(node*2, start, (start+end)/2, index, diff);
            updateByDiff(node*2, (start+end)/2, end, index, diff);
        }
    }

    // changeValue: 변경 될 값 그 자체
    long updateByValue(int node, int start, int end, int index, long changeValue){
        if(index < start || end < index){
            return tree[node];
        }

        if(start == index && end == index) {
            return tree[node] = changeValue;
        }else{
            return tree[node] = updateByValue(node*2, start, (start+end)/2, index, changeValue)
                    + updateByValue(node*2+1, (start+end)/2+1, end, index, changeValue);
        }
    }

    void update(int index, long diff){
        updateByDiff(NODE_START, startIdx, endIdx, index, diff);
        //return updateByValue(NODE_START, startIdx, endIdx, index, changeValue);
    }
}
