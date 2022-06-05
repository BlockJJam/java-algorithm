package solve.baekjoon.backtracking;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NemoNemo_14712 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Nemo nemo = new Nemo(N, M);
        nemo.findAllStopedCase(0);

        bw.write(nemo.result + "\n");
        bw.flush();
        bw.close();
    }

    static class Nemo{
        boolean[][] graph;
        int N;
        int M;
        long result;

        Nemo(int N, int M){
            this.graph = new boolean[N+1][M+1];
            this.N = N;
            this.M = M;
            this.result = 0L;
        }

        static int cnt = 0;
        public void findAllStopedCase(int cnt){
            // dfs
            if(cnt == N*M){
                result++;
                return;
            }

            int y = cnt / M + 1;
            int x = cnt % M + 1;

            if(graph[y-1][x] && graph[y][x-1] && graph[y-1][x-1] ){
                findAllStopedCase(cnt +1);
            }else{
                findAllStopedCase(cnt+1);
                graph[y][x] = true;
                findAllStopedCase(cnt+1);
                graph[y][x] = false;
            }
        }

        public void printGraph(boolean[][] graph){
            for(boolean[] g : graph){
                for(boolean target: g){
                    System.out.print(target? 1+ " " : 0+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
