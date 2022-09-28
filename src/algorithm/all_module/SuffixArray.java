package algorithm.all_module;


import java.util.*;

public class SuffixArray {
    public static void main(String[] args) {
        String text = "baekjoon";

        List<Integer> suffix = getSuffixArray(text);

        for (int i : suffix) {
            System.out.println(i + " - " + text.substring(i));
        }
    }

    private static List<Integer> getSuffixArray(String text){
        int n = text.length();
        int t = 1;

        List<Integer> perm = new ArrayList<>();

        int[] group = new int[n + 1];
        for (int i = 0; i < n; i++) {
            perm.add(i);
            group[i] = text.charAt(i) - 'a';
        }

        group[n] = -1;
        CompUsing2T compUsing2T = new CompUsing2T(n, t, group);

        while(t < n){
            perm.sort(compUsing2T);

            t *= 2;

            if(t >= n) break;

            int[] newGroup = new int[n+1];
            newGroup[perm.get(0)] = 0;
            newGroup[n] = -1;

            for (int i = 1; i < n; i++) {
                if(compUsing2T.compare(perm.get(i-1), perm.get(i)) < 0){
                    newGroup[perm.get(i)] = newGroup[perm.get(i -1)] + 1;
                }else{
                    newGroup[perm.get(i)] = newGroup[perm.get(i - 1)];
                }
            }

            group = newGroup;
            compUsing2T.changeValues(t, group);
        }

        return perm;
    }

    static class CompUsing2T implements Comparator<Integer> {
        private int n;
        private int t;
        private int[] group;

        CompUsing2T(int n, int t, int[] group){
            this.n = n;
            this.t = t;
            this.group = Arrays.copyOf(group, group.length);
        }

        public void changeValues(int t, int[] group){
            this.t = t;
            this.group = Arrays.copyOf(group, group.length);
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if(group[o1] != group[o2]){
                    return group[o1] - group[o2];
            }

            int left = o1 + t;
            int right = o2 + t;

            if(left > n){
                left = n;
            }
            if(right > n){
                right = n;
            }

            return group[left] - group[right];
        }
    }
}
