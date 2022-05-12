package solve.baekjoon.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeOrder_1991 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Node<Character>[] nodeArr = new Node[N];

        for(int i=0; i<N; i++){

           char data = (char) ('A' +i);
           nodeArr[i] = new Node<>(data);
        }
        Tree tree = new Tree(nodeArr[0]);

        for(int i=0 ; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int root = st.nextToken().charAt(0) - 'A';
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';


            if(left >= 0) nodeArr[root].addLeft(nodeArr[left]);
            if(right >= 0) nodeArr[root].addRight(nodeArr[right]);
        }

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);


    }

    static class Node<T>{
        T data;
        Node left;
        Node right;

        public Node(T data){
            this.data = data;
            left = null;
            right = null;
        }

        public void addLeft(Node node){
            left = node;
        }

        public void addRight(Node node){
            right = node;
        }
    }

    static class Tree{
        Node root;

        public Tree(Node root){
            this.root = root;
        }

        public void preOrder(Node node){
            if(node == null){
                return;
            }

            System.out.print(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }

        public void inOrder(Node node){
            if(node == null){
                return;
            }

            inOrder(node.left);
            System.out.print(node.data);
            inOrder(node.right);
        }

        public void postOrder(Node node){
            if(node == null){
                return;
            }

            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data);
        }
    }
}
