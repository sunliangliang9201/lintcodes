package com.sunll.lintcode.easy;

/**
 * <p>desc: 哈夫曼编码</p>
 * 给出一段字符串，给出最短编码长度(按照字母来的，不是字母组合)
 * 是根据二叉树来判断编码长度的，特点是不唯一，无损压缩，而且有效避免了前缀问题
 * 哈夫曼树就是一棵带权二叉树、它的WPL是最小的、也就是从根节点到每一个节点的路径长度（经过的边数）与权值乘积的总和是最小的、就称为哈夫曼树。
 * 思路：
 * 把各个字符在整个串中出现的频率作为它的权重、通过使用0、1表示来缩短整个串的长度、可用于无损压缩。
 * 完成哈夫曼编码首先要先建立哈夫曼树、根据树中节点的路径、计算出对应节点的编码。
 * 总结：
 * step1:建立haffman二叉树
 * @author sunliangliang 2019-08-29 23:15
 * @version 1.0
 */
public class HaffmanCoding {

    public static void main(String[] args) {

    }


}

class Node{
    private int weight;//权重
    private char ch;//值
    private Node left;
    private Node right;//左右孩子
    private Node parent;//可能存在回溯，等会再看

}