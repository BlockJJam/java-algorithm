package solve.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BreakEgg_16987 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Egg[] eggs = new Egg[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(d, w);
        }

        EggBreaker eggBreaker = new EggBreaker(eggs, N);
        eggBreaker.breakEggs(0);

        bw.write(eggBreaker.result+ "\n");
        bw.flush();
        bw.close();
    }

    static class EggBreaker{
        Egg[] eggs;
        boolean[] broken;
        int N;
        int result;

        EggBreaker(Egg[] eggs, int N){
            this.eggs = eggs;
            this.N = N;
            this.broken= new boolean[N];
            result = 0;
        }

        public void breakEggs(int start) throws CloneNotSupportedException {
//            System.out.println(start);
            if(start == N){
                // 마지막 계란까지 처리했으면 몇 개나 깼는지 조사
                int cnt = 0;
                for(boolean b : broken){
                    cnt = b? cnt+1: cnt;
                }
//                System.out.println(cnt);
                result = Math.max(result, cnt);

                return;
            }

            if(broken[start]){
                breakEggs(start+1);
            }else{
                boolean noTouch = true;
                for (int i = 0; i < N; i++) {
                    Egg copyStart = null;
                    Egg copyI = null;
                    if (!broken[i] && i != start) {
                        noTouch = false;
                        copyStart = eggs[start].clone();
                        copyI = eggs[i].clone();
                        battleEggs(eggs, broken, start, i);
                        breakEggs(start + 1);

                        eggs[i] = copyI;
                        broken[i] = false;
                        eggs[start] = copyStart;
                        broken[start] = false;
                    }else{
                        if(noTouch && i == N-1)
                            breakEggs(start +1);
                    }
                }

            }
        }

        public void battleEggs(Egg[] eggs, boolean[] broken, int attack, int defend) {
            eggs[attack].durability = eggs[attack].durability - eggs[defend].weight;
            broken[attack] = eggs[attack].durability <= 0;

            eggs[defend].durability = eggs[defend].durability - eggs[attack].weight;
            broken[defend] = eggs[defend].durability <= 0;

//            System.out.println("attack"+attack+": "+eggs[attack].durability +" defend"+defend+": "+eggs[defend].durability);

        }
    }

    static class Egg implements Cloneable{
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }

        @Override
        protected Egg clone() throws CloneNotSupportedException {
            return (Egg)super.clone();
        }

    }
}
