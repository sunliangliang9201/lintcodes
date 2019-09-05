package com.sunll.lintcode.hard;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 二叉查找树的一系列问题
 * 1.二叉查找树的增删改查？
 * 2.二叉树遍历（先中后层序遍历）？（普通二叉树）
 * 3.判断二叉树是否为对称的？（普通二叉树）(小米)
 * 4.二叉树的序列与反序列化方法？（普通二叉树）（快手）
 *
 * @author sunliangliang 2019/5/29
 * @version 1.0
 */
public class BSTreeUtils<T extends Comparable<? super T>> {

    //-------------------
    //定义二叉树节点类
    class Node<T extends Comparable<? super T>>{
        T data;
        Node leftChild, rightChild;//左右孩子
        public Node(T data){
            this.data = data;
        }
        public String toString() {
            return "Node [data=" + this.data + ", leftChild=" + this.leftChild + ", rightChild=" + this.rightChild + "]";
        }
    }
    //------------------

    private Node root;

    /**
     * 1. BSTree的查找
     */
    private Node find(T key){
        return find(key, root);
    }
    private Node find(T key, Node cur){
        if (cur == null) return null;
        int cmp = cur.data.compareTo(key);
        if (cmp > 0) return find(key, cur.leftChild);
        if (cmp < 0) return find(key, cur.rightChild);
        return cur;
    }

    /**
     * 2. BSTree的新增节点
     * - 若二叉排序树为空，则待插入结点*S作为根结点插入到空树中；
     * - 当非空时，将待插结点关键字S->key和树根关键字t->key进行比较，若s->key = t->key,则无须插入，若s->key< t->key,则插入到根的左子树中，若s->key> t->key,则插入到根的右子树中。而子树中的插入过程和在树中的插入过程相同，如此进行下去，直到把结点*s作为一个新的树叶插入到二叉排序树中，或者直到发现树已有相同关键字的结点为止。
     */
    private void insert(T key){
        insert(key, root);
    }
    private void insert(T key, Node cur){
        if (cur == null) cur = new Node(key);
        int cmp = cur.data.compareTo(key);
        if (cmp > 0) insert(key, cur.leftChild);
        if (cmp < 0) insert(key, cur.rightChild);
    }

    public static void main(String[] args) {
        BSTreeUtils<Integer> tree = new BSTreeUtils();
        tree.insert(1);
        tree.insert(5);
        System.out.println(tree.root);
        System.out.println(tree.find(1));
    }
}
