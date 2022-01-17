package data_structure.custom;


import java.util.Arrays;

/**
 *  상호배타적(=서로소) 집합
 *  - 의미: 서로 중복 포함된 원소가 없는 집합
 *  - 동작: 집합에 속한 하나의 특정 멤버를 통해 각 집합을 구분
 *  - 표현 방법: 1) LinkedList 2) Tree
 *  - 연산
 *      : Set(x) - 유일한 멤버 x를 포함하는 [새로운 집합]을 생성
 *      : Find-Set(x) - x를 포함하는 집합을 찾는 연산( 해당 노드의 부모 정보 갱신 ) + 최적화시에, parent를 찾아서 올라가기만 하지 않고, parent를 찾아올라가는 과정을 압축하기 위해 찾은 부모를 업데이트 시켜준다
 *      : Union(x,y) - x와 y를 포함하는 두 집합을 통합하는 연산 + 최적화시에, 트리의 높이를 최소한으로 줄이기 위해 rank 배열을 활용 rank가 높은 집합으로 합쳐준
 *  - 케이스
 *      : 그래프 형태의 자료구조에서 Cycle이 존재하는지 여부, subset을 하나두고(-1로 초기화) 간선 정보를 꺼내가며 간선에 해당하는 정점이 어떤 subset에 속하는지 여부를 검사한다(같은 subset이면 Cycle)
 *      : 두 원소가 같은 집합에 있는지 여부를 확인하려는 곳
 */
public class DisjointSet {
    int parent[];
    int rank[];

    public DisjointSet(int n){
        this.parent= new int[n+1];
        this.rank = new int[n+1];

        for(int i=1; i < n+1; i++){
            this.parent[i] = i;
        }
        Arrays.fill(rank, 1);
    }

    public int find(int e){
        if(this.parent[e] == e) return e;
        return this.parent[e] = find(this.parent[e]);
    }

    public void union(int e1, int e2){
        e1 = find(e1);
        e2 = find(e2);

        if(e1 == e2) return;

        if(e1 < e2) {
            this.parent[e2] = e1;
        }else{
            this.parent[e1] = e2;
        }
    }

    public void unionByRank(int e1, int e2){
        e1 = find(e1);
        e2 = find(e2);

        if(e1 == e2) return;

        if(rank[e1] > rank[e2]){
            this.parent[e2] = e1;
        }else {
            this.parent[e1] = e2;
        }

        if(rank[e1] == rank[e2]) ++rank[e2];
    }
}