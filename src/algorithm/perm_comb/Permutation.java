package algorithm.perm_comb;

public class Permutation {
    static int[] arr = {1,2,3,4};
    static int[] output = new int[4];
    static boolean[] visited = new boolean[4];
    static int N = 4;

    public static void main(String[] args) {
//        perm(0, 3);
        dupPerm(0,3);
    }

    // 일반 순열
    public static void perm(int depth, int r){
        if(depth == r){
            for(int i=0; i<r; i++){
                System.out.print(output[i] +" ");
            }
            System.out.println();
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = arr[i];
                perm(depth+1, r);
                visited[i] = false;
            }
        }
    }

    // 중복 순열
    static int num = 0;
    public static void dupPerm(int depth, int r){
        if(depth == r){
            for(int i=0; i<r; i++){
                System.out.print(output[i]+ " ");
            }
            num++;
            System.out.println(num + "번째 경우");
            return;
        }

        for(int i=0; i<N; i++){
            visited[i] = true;
            output[depth] = arr[i];
            dupPerm(depth + 1, r);
            visited[i] = false;
        }
    }
}
