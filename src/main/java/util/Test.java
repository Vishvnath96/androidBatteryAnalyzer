package util;

import java.io.File;
import java.lang.management.ThreadInfo;

public class Test {

    public static void main(String[] args){
        Node root = null;
        BST bst = new BST();
        root = bst.insert(root,8);
        root = bst.insert(root,3);
        root = bst.insert(root,6);
        root = bst.insert(root,10);
        System.out.print(root);
    }
}

class BST{

    public Node insert(Node node,int val){
        if(node == null){
            return createNewNode(val);
        }
        if(val < node.data){
            node.left = insert(node.left,val);
        }
        else if(val > node.data) {
            node.right = insert(node.right,val);
        }
        return node;
    }

    private Node createNewNode(int val) {
        Node a = new Node();
        a.data = val;
        a.left = null;
        a.right = null;
        return a;
    }
}

class Node{
    int data;
    Node left;
    Node right;
}
