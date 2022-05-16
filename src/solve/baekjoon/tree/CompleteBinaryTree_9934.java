package solve.baekjoon.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CompleteBinaryTree_9934 {
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        List<Integer>[] depthArr = new ArrayList[K];
        for(int i=0; i<K; i++) depthArr[i] = new ArrayList<>();


        int loop = (int)Math.pow(2,K)-1;
        Tree tree = new Tree();


        st = new StringTokenizer(br.readLine());
        for(int i=0; i<loop; i++){
            int next = Integer.parseInt(st.nextToken());

            tree.addData(next,depthArr);
        }

        for(int i=0; i<K; i++){
            for(int e: depthArr[i]){
                bw.write(e + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static class Node{
        Node parent;
        int depth;
        int data;
        Node left;
        Node right;
        boolean filled;

        public Node(int depth){
            this.depth = depth;
            this.filled = false;
            this.data = -1;
            if(depth +1 != K) {
                this.left = new Node(depth + 1);
                this.left.parent = this;

                this.right = new Node(depth + 1);
                this.right.parent = this;
            }
        }

        public static Node findBuilding(Node node){
            if(node.left == null && node.right == null && node.data == -1){
                node.filled = true;
                return node;
            }else{
                if(node.left != null && !node.left.filled) return findBuilding(node.left);
                if(node.data == -1) return node;
                if(node.right != null && !node.right.filled) return findBuilding(node.right);
                node.filled = true;
                return findBuilding(node.parent);
            }
        }
    }

    static class Tree{
        Node root;
        public Tree(){
            root = new Node(0);
        }

        public void addData(int data, List<Integer>[] depthArr){
            Node target = Node.findBuilding(root);
            target.data = data;
            depthArr[target.depth].add(target.data);

        }

    }


}
