package solve.programmers.monthly_challenge1;

public class QuadCompact {

    public static int[] solution(int[][] arr) {
        int[] answer = {};

        Quad quad = compact(arr, 0, arr.length, 0, arr.length, arr.length);
        System.out.println("quad = " + quad);
        answer = new int[2];
        answer[0] = quad.zero;
        answer[1] = quad.one;

        return answer;
    }

    public static Quad compact(int[][] arr, int rs, int re, int cs,  int ce, int width){
        if(width == 1){
            return new Quad(arr[rs][cs] == 1? 0:1, arr[rs][cs] == 1? 1:0, true);
        }

        Quad left1 = compact(arr, rs, re - width/2, cs, ce - width/2, width/2);
        Quad left2 = compact(arr, rs + width/2, re, cs, ce - width/2, width/2);
        Quad right1 = compact(arr, rs, re - width/2, cs + width/2, ce, width/2);
        Quad right2 = compact(arr, rs + width/2, re, cs + width/2, ce, width/2);

        if(compactQuad(left1, left2, right1, right2)){
            int oneSum = left1.one+ left2.one+ right1.one+ right2.one;
            int zeroSum = left1.zero+ left2.zero+ right1.zero+ right2.zero;
            if(oneSum == 4){
                return new Quad(0, 1, true);
            }else if(zeroSum == 4){
                return new Quad(1, 0, true);
            }else{
                return new Quad(zeroSum, oneSum, false);
            }
        }else{
            int oneSum = left1.one+ left2.one+ right1.one+ right2.one;
            int zeroSum = left1.zero+ left2.zero+ right1.zero+ right2.zero;
            return new Quad(zeroSum, oneSum, false);
        }
    }

    private static boolean compactQuad(Quad left1, Quad left2, Quad right1, Quad right2) {
        if(left1.compacted){
            left1.one = left1.one != 0? 1: 0;
            left1.zero= left1.zero != 0? 1: 0;
        }

        if(left2.compacted){
            left2.one = left2.one != 0? 1: 0;
            left2.zero= left2.zero != 0? 1: 0;
        }
        if(right1.compacted){
            right1.one = right1.one != 0? 1: 0;
            right1.zero= right1.zero != 0? 1: 0;
        }
        if(right2.compacted){
            right2.one = right2.one != 0? 1: 0;
            right2.zero= right2.zero != 0? 1: 0;
        }
        return left1.compacted && left2.compacted && right1.compacted && right2.compacted;
    }

    static class Quad{
        int zero;
        int one;
        boolean compacted;

        public Quad(int zero, int one, boolean compacted) {
            this.zero = zero;
            this.one = one;
            this.compacted = compacted;
        }

        @Override
        public String toString() {
            return "Quad{" +
                    "zero=" + zero +
                    ", one=" + one +
                    ", compacted=" + compacted +
                    '}';
        }
    }

    public static void main(String[] args) {

    }
}
