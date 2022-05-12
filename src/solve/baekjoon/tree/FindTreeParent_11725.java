package solve.baekjoon.tree;

import java.io.*;
import java.util.*;

public class FindTreeParent_11725 {
    static int N;
    static Node[] nodeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nodeArr = new Node[N+1];
        for(int i=1; i<=N; i++){
            nodeArr[i] = new Node(i);
        }

        int a,b;
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            nodeArr[a].addAdj(nodeArr[b]);
            nodeArr[b].addAdj(nodeArr[a]);
        }

        Tree.fillParent(nodeArr[1]);

        for(int i=2; i<=N; i++)
            bw.write(nodeArr[i].parent+"\n");

        bw.flush();
        bw.close();


    }

    static class Node{
        int data;
        int parent;
        List<Node> adj;

        public Node(int n){

            this.parent = n == 1 ? 0: -1;
            this.data = n;
            this.adj = new ArrayList<>();
        }

        public void addAdj(Node node) {
            this.adj.add(node);
        }
    }

    static class Tree{
        public static void fillParent(Node node){
            Queue<Node> q = new LinkedList<>();

            q.offer(node);

            while(!q.isEmpty()){
                Node source = q.poll();
                for(Node target: source.adj){
                    if(target.parent == -1){
                        target.parent = source.data;
                        q.offer(target);
                    }
                }
            }
        }
    }
}
