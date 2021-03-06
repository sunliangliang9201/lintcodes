package com.sunll.lintcode.hard;

import java.util.*;

/**
 * 二叉查找树的一系列问题
 * 1.二叉查找树的增删查？（搜狐）
 * 2.二叉树遍历（先中后层序遍历）？（普通二叉树）
 * 3.判断二叉树是否为对称的？（普通二叉树）(小米)
 * 4.二叉树的序列与反序列化方法？（普通二叉树）（快手）
 * 5.查询两个节点的最近公共父节点？（搜狐）
 * 6.返回某个节点的所有父节点，包含自己？（搜狐）
 *
 * @author sunliangliang 2019/5/29
 * @version 1.0
 */
public class BSTreeUtils<T extends Comparable<? super T>> {

    //-------------------
    //定义二叉树节点类
    static class Node<T extends Comparable<? super T>>{
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

    private Node root = null;

    /**
     * 1.1 BSTree的查找
     */
    public Node find(T key){
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
     * 1.2 BSTree的新增节点
     * - 若二叉排序树为空，则待插入结点*S作为根结点插入到空树中；
     * - 当非空时，将待插结点关键字S->key和树根关键字t->key进行比较，若s->key = t->key,则无须插入，若s->key< t->key,则插入到根的左子树中，若s->key> t->key,则插入到根的右子树中。而子树中的插入过程和在树中的插入过程相同，如此进行下去，直到把结点*s作为一个新的树叶插入到二叉排序树中，或者直到发现树已有相同关键字的结点为止。
     */
    public void insert(T key){
        root = insert(key, root);
    }
    private Node insert(T key, Node cur){
        if (cur == null) {
            return new Node(key);
        }
        int cmp = cur.data.compareTo(key);
        if (cmp > 0) cur.leftChild = insert(key, cur.leftChild);
        if (cmp < 0) cur.rightChild = insert(key, cur.rightChild);
        return cur;///这一步太TM关键了，把根指针指回了root
    }

    /**
     * 1.3 BSTree的删除节点
     * 思想：比较复杂，如果删除的节点的左右子树都不为空怎么办？那就把右子树的最小节点放在cur的位置，左子树还是左子树，右子树还是右子树，不过要把那个右子树的最小节点删除，因为已经移到cur了嘛
     */
    public void delete(T key){
        root = delete(key, root);
    }
    private Node delete(T key, Node cur){
        if (cur == null) return null;
        int cmp = cur.data.compareTo(key);
        if (cmp > 0) cur.leftChild = delete(key, cur.leftChild);
        if (cmp < 0) cur.rightChild = delete(key, cur.rightChild);
        if (cmp == 0) {
            if (cur.leftChild == null) return cur.rightChild;
            if (cur.rightChild == null) return cur.leftChild;
            Node tmp = cur;
            cur = getMin(cur.rightChild);
            cur.leftChild = tmp.leftChild;
            cur.rightChild = deleteMin(tmp.rightChild);
        }
        return cur;
    }
    private Node getMin(Node node){//获取最小节点
        if (node.leftChild == null) return node;
        return getMin(node.leftChild);
    }
    private Node deleteMin(Node node){//删除最小节点
        if (node.leftChild == null) return node.rightChild;
        node.leftChild = deleteMin(node.leftChild);
        return node;
    }


    /**
     * 2.1 BSTree的遍历之先序遍历
     * 在之前先说一下先序遍历、中序遍历、后序遍历如何区分：：根左右、左根右、左右根，可以发现左右顺序一致，只是根的位置逐渐向后移动
     */
    public List preOrder(){
        List<T> list = new ArrayList<>();
        preOrder(list, root);
        return list;
    }
    private void preOrder(List<T> list, Node cur){
        if (cur != null) {
            list.add((T)cur.data);
            preOrder(list, cur.leftChild);
            preOrder(list, cur.rightChild);
        }
    }

    /**
     * 2.2 BSTree的遍历之中序遍历
     */
    public List inOrder(){
        List<T> list = new ArrayList<>();
        inOrder(list, root);
        return list;
    }
    private void inOrder(List<T> list, Node cur){
        if (cur != null){
            inOrder(list, cur.leftChild);
            list.add((T) cur.data);
            inOrder(list, cur.rightChild);
        }
    }

    /**
     * 2.3 BSTree的遍历之后序遍历
     */
    public List postOrder(){
        List<T> list = new ArrayList<>();
        postOrder(list, root);
        return list;
    }
    private void postOrder(List<T> list, Node cur){
        if (cur != null){
            postOrder(list, cur.leftChild);
            postOrder(list, cur.rightChild);
            list.add((T) cur.data);
        }
    }

    /**
     * 2.4 BSTree的遍历之层序遍历
     */
    public List levelOrder(){
        List<T> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        Node tmp = null;
        while (!queue.isEmpty()){
            tmp = queue.poll();
            list.add((T) tmp.data);
            if (tmp.leftChild != null) queue.add(tmp.leftChild);
            if (tmp.rightChild != null) queue.add(tmp.rightChild);
        }
        return list;
    }


    /**
     * 3 二叉树是否对称？(注意哈：这个方法不是根据BSTree的，而是普通的二叉树！！！！)就当作剑指 offer上的一个算法题来看
     * 思想：两个节点A和B对称等价于:
     * 这两个节点上存储的值相等
     * 节点A的左子树节点和节点B的右子树上的节点是对称的
     * 节点A的右子树节点和节点A的左子树上的节点是对称的
     *
     * 上面是递归的思想，那么得递归的思想呢？
     * 非递归的方式就是使用两个队列来实现，对左子树使用从左到右的层序遍历，对右子树使用从右到做的层序遍历，一层一层比较
     */
    public static boolean isSymmetrical(Node header){
        if (header == null) return true;
        return isSymmetrical(header.leftChild, header.rightChild);
    }
    private static boolean isSymmetrical(Node left, Node right){
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return (left.data.compareTo(right.data) == 0) && isSymmetrical(left.leftChild,right.rightChild) && isSymmetrical(left.rightChild, right.leftChild);
    }

    /**
     * 4 二叉树的序列化与反序列化
     * 思想：其实说白了就是用什么样的结构存储到磁盘，然后通过读磁盘就可以恢复原来二叉树的结构！
     * 本方法规定：使用先序遍历的方式，把二叉树协程String，"/"表示一个节点值的结束，"#/"表示空节点
     */
    public static String preSerialization(Node header){//序列化
        StringBuilder sb = new StringBuilder();
        preSerialization(header, sb);
        return sb.toString();
    }
    private static void preSerialization(Node header, StringBuilder sb){
        if (header == null) {
            sb.append("#/");
        }else{
            sb.append(header.data + "/");//典型的先序遍历方式
            preSerialization(header.leftChild, sb);
            preSerialization(header.rightChild, sb);
        }
    }

    static int i = -1;
    public static Node preDeserialization(String str){//反序列化
        String[] strs = str.split("/");
        return preDeserialization(strs);
    }
    private static Node preDeserialization(String[] strs){
        Node node;
        if (strs[++i].equals("#")){
            node = null;
        }else{
            node = new Node(strs[i]);//同样是先序遍历的路子
            node.leftChild = preDeserialization(strs);
            node.rightChild = preDeserialization(strs);
        }
        return node;
    }

    /**
     * 5.二叉树中两个节点a, b，寻找两个节点的最近的公共父节点？
     * 方法很多：我当时想出来了两种方法
     * 第一种：用一个list存储a节点的所有父节点（包括自己），然后再去找b节点的父节点，从下到上，每次都去list找，如果存在的话那就是最近的公共父节点。
     * 由于是普通的二叉树，所以查询复杂度是O(n)，而去list找是否存在则是一个O(n2)的，不过呢，如果通过一些手段可以降低时间复杂度。
     * 第二种：仍然是递归的方法，从叶节点开始，如果发现该节点以下的树包含了a，b那么直接返回该节点，否则返回null，这样如果递归回溯的时候如果返回不为null的话则继续返回那个节点，如果是null则以当前
     * 节点为树进行重复工作，但是这个过程是O(n2)的
     * 第三种：大家通用的方法：跟我说的第二种非常非常像，只是充分利用了递归的思想，无非两种情况，如果分别在根结点的两侧找到的a和b，那么返回根结点，如果在一侧找到了，那么就返回非空的那一侧的节点
     * 下面实现第三种方式。
     */
    public  Node LCA(T a, T b, Node cur){// 可以想想递归的牛逼了吧！！！！！
        if (cur == null || cur.data.compareTo(a) == 0 || cur.data.compareTo(b) == 0) return cur;
        Node left = LCA(a, b, cur.leftChild);
        Node right = LCA(a, b, cur.rightChild);
        if (left != null && right != null){
            return cur;
        }
        return left != null? left: right;
    }

    /**
     * 6.返回某节点的所有父节点，包含自己？
     * 这个函数主要是为了实现以下第5题中第一种方法中的内容，毕竟这个题在面试时自己写了，而且没写好，经过面试官的提示、解释才搞明白。
     */
    private List<T> list = new ArrayList<>();
    public Node getParents(T key, Node cur){
        //第一步退出条件，非常关键！！！！！
        if (cur == null) return cur;
        if (cur.data.compareTo(key) == 0) {//如果发现了要找的节点就添加到list，并且返回
            list.add((T)cur.data);
            return cur;
        }
        //if (list.size() != 0) list.add((T)cur.data);
        Node left = getParents(key, cur.leftChild);
        if (left != null) {
            list.add((T)cur.data);
            return cur;
        }
        Node right = getParents(key, cur.rightChild);
        if (right != null) {
            list.add((T)cur.data);
            return cur;
        }
        return cur;
    }
    //总结：使用递归：第一步要确定好退出条件，看看需要操作；第二步就是调用递归，对当前结果进行判断操作即可。


    public static void main(String[] args) {
        BSTreeUtils<Integer> tree = new BSTreeUtils();
        tree.insert(1);
        tree.insert(5);
        tree.insert(3);
//        System.out.println(tree.root);
//        tree.delete(5);
//        System.out.println(tree.root);;
//        System.out.println(tree.find(3));
//        System.out.println(tree.root);
//        System.out.println(tree.preOrder());
//        System.out.println(tree.inOrder());
//        System.out.println(tree.postOrder());
//        System.out.println(tree.levelOrder());


        //创建一个普通的二叉树
//        Node<Integer> node1 = new Node<>(1);
//        Node<Integer> node2 = new Node<>(2);
//        Node<Integer> node3 = new Node<>(2);
//        Node<Integer> node4 = new Node<>(3);
//        Node<Integer> node5 = new Node<>(3);
//        Node<Integer> node6 = new Node<>(4);
//        Node<Integer> node7 = new Node<>(4);
//        node1.leftChild = node2;
//        node1.rightChild = node3;
//        node2.leftChild = node4;
//        node2.rightChild = node6;
//        node3.leftChild = node7;
//        node3.rightChild = node5;
//        //System.out.println(isSymmetrical(node1));
//        //System.out.println(preSerialization(node1));
//        String str = "1/2/3/#/#/4/#/#/2/4/#/#/3/#/#/";
//        Node header = preDeserialization(str);
//        System.out.println(header);

        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        node1.leftChild = node2;
        node1.rightChild = node3;
//        node2.leftChild = node4;
//        node3.leftChild = node5;
//        node3.rightChild = node6;
//        node5.leftChild = node7;
        //System.out.println(tree.LCA(2, 7, node1));
        tree.getParents(2, node1);
        System.out.println(tree.list);
    }
}
