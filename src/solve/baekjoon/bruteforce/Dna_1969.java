package solve.baekjoon.bruteforce;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Dna_1969 {

    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] DNA = new int[M][4]; // 0: A, 1: C, 2: G, 3: T

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String dnaInfo = st.nextToken();
            for(int j=0; j<M; j++){
                char target = dnaInfo.charAt(j);

                switch (target){
                    case 'A':
                        DNA[j][0]++;
                        break;
                    case 'C':
                        DNA[j][1]++;
                        break;
                    case 'G':
                        DNA[j][2]++;
                        break;
                    case 'T':
                        DNA[j][3]++;
                        break;
                    default:
                        break;
                }
            }
        }

        HashMap<Integer, Character> dnaMatcher = new HashMap<>();
        dnaMatcher.put(0,'A'); dnaMatcher.put(1,'C'); dnaMatcher.put(2,'G'); dnaMatcher.put(3,'T');

        String resultS="";
        int resultCnt =0;
        StringBuilder sb = new StringBuilder();
        int dnaKey = 0;
        int minDist = N+1;

        for(int i=0; i<M; i++){
            for(int j=0; j<4; j++){
                int dist = N-DNA[i][j];
                if(dist < minDist){
                    dnaKey = j;
                    minDist = dist;
                }
            }

            sb.append(dnaMatcher.get(dnaKey));
            resultCnt += minDist;
            minDist = N+1;
        }

        resultS = sb.toString();

        bw.write(resultS+"\n");
        bw.write(resultCnt+"\n");
        bw.flush();
        bw.close();
    }
}
