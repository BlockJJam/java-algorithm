package solve.baekjoon.implement;

import java.io.*;
import java.util.StringTokenizer;

public class CowCrossReason_14467 {
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Cow[] cowGroup = new Cow[11];
        for( int i=1; i<=10; i++){
            cowGroup[i] = new Cow(i);
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int targetId = Integer.parseInt(st.nextToken());
            int targetIdx = Integer.parseInt(st.nextToken());

            cowGroup[targetId].updateIdx(targetIdx);
        }

        bw.write(result+ "\n");
        bw.flush();
        bw.close();
    }

    static class Cow{
        int ID;
        int idx;

        public Cow(int id){
            this.ID = id;
            this.idx = -1;
        }

        public void updateIdx(int idx){
            if(this.idx == -1){
                this.idx = idx;
            }else if(this.idx != idx){
                this.idx = idx;
                result++;
            }
        }
    }
}
