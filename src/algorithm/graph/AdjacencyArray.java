package algorithm.graph;

public class AdjacencyArray<T> {
    public static void main(String[] args) {
        int initSize = 5;
        AdjacencyArray adArray = new AdjacencyArray(initSize);

        adArray.put(1,2,1);
        adArray.put(1,4,1);
        adArray.put(2,3,1);
        adArray.put(2,5,1);
        adArray.put(4,5,1);

        adArray.printGraph();
    }

    private int[][] adArray;
    private int size;

    public AdjacencyArray(int size){
        // 1부터 시작하는 것을 가정
        this.adArray = new int[size+1][size+1];
        this.size = size;
    }

    // 양방향 가중치 그래프를 가정하여 weight 전달
    // 가중치가 없으면 1로 대체
    public void put(int y, int x, int weight){
        this.adArray[y][x] = this.adArray[x][y] = weight;
    }
    // 단방향
    public void putSingle(int y, int x, int weight){
        this.adArray[y][x] = weight;
    }

    // 특정 Vertex의 인접 Vertex들 정보를 1차원 배열로 제공
    public int[] getVertex(int idx){
        return this.adArray[idx];
    }

    //특정 Vertex_X와 Vertex_Y간의 관계를 반환
    public int getWeight(int y, int x){
        return this.adArray[y][x];
    }

    public void printGraph(){
        StringBuilder sb = new StringBuilder();
        sb.append("인접 행렬을 구현한 2차원 배열 내역\n");
        for(int i=1; i < this.adArray.length; i++){
            for(int j=1; j < this.adArray[i].length; j++){
                sb.append(this.adArray[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
