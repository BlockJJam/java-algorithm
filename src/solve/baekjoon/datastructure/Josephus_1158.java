package solve.baekjoon.datastructure;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Josephus_1158 {
    static int N;
    static int K;
    static Josephus josephus;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        josephus = new Josephus(N);

        int removedIndex = 1;
        while(josephus.isNotLastPerson()){
            removedIndex = josephus.removePerson(removedIndex, K);
        }

        bw.write("<");
        List<Integer> result = josephus.getRemovedSeq();
        for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1) {
                bw.write(result.get(i).toString());
            } else {
                bw.write(result.get(i) + ", ");
            }
        }
        bw.write(">\n");
        bw.flush();

    }

    static class Josephus{
        private int[] parent;
        private List<Integer> removedSeq;

        public Josephus(int n){
            parent = new int[n+1];
            removedSeq = new ArrayList<>();

            for(int i=1; i<n+1; i++){
                parent[i] = i;
            }
        }

        public int find(int e){
            if(parent[e] == e){
                return e;
            }
            return parent[e] = find(parent[e]);
        }

        public int removePerson(int e, int remain){
            int num = (e + 1) % (N + 1);
            int next = find(num==0? 1: num);
            if(remain == 1){
                parent[e] = next;
                removedSeq.add(e);
                return next;
            }
            return removePerson(next, remain-1);
        }

        public boolean isNotLastPerson(){
            return N != removedSeq.size();
        }

        public List<Integer> getRemovedSeq() {
            return removedSeq;
        }
    }
}
