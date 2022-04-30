package solve.baekjoon.datastructure;

import java.io.*;
import java.util.StringTokenizer;

public class ParenthesisString_9012 {
    static int N;
    static VPS vps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        vps = new VPS();
        String target;
        String result;
        for(int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            target = st.nextToken();
            result = vps.isValid(target)? "YES": "NO";

            bw.write(result+"\n");
            vps.flush();
        }
        bw.flush();
        bw.close();
    }

    static class VPS{
        private int threshold = 0;

        public boolean isValid(String target){
            Character c;
            for(int i=0; i< target.length(); i++){
                c = target.charAt(i);
                if (c.equals('(')) ++threshold;
                else --threshold;

                if(threshold < 0){
                    return false;
                }
            }

            if(threshold == 0)
                return true;
            return false;

        }

        public void flush(){
            threshold = 0;
        }
    }
}
