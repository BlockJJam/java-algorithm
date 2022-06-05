package algorithm.perm_comb;

public class Combination {
    static int num= 0;
    static int[] arr = {1,2,3,4};
    static int[] output = new int[4];
    static boolean[] visited = new boolean[4];
    static int N = 4;

    public static void main(String[] args) {
//        comb(0,2);
        output = new int[4];
        dupComb(0,0,4);
    }

    // 일반 조합
    public static void comb(int start, int r){
        if(start == r){
            for(int i=0; i< visited.length; i++){
                if(visited[i]){
                    System.out.print(arr[i] + " ");
                }
            }
            num++;
            System.out.println("-" + num + "번째 경우");
            return;
        }

        for(int i=start; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                comb(start+1, r);
                visited[i] = false;
            }
        }
    }

    // 중복 조합
    public static void dupComb(int start,int depth, int r){
        if(depth == r){
            for(int num : output)
                System.out.print(num + " ");
            System.out.println();
            return;
        }

        for(int i=start; i<N; i++){
            output[depth] = arr[i];
            dupComb(i, depth+1, r);
        }
    }
}
