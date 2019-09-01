package com.sunll.lintcode.easy;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * <p>desc: 哈夫曼编码</p>
 * 给出一段字符串，给出最短编码长度(按照字母来的，不是字母组合)
 * 是根据二叉树来判断编码长度的，特点是不唯一，无损压缩，而且有效避免了前缀问题
 * 哈夫曼树就是一棵带权二叉树、它的WPL是最小的、也就是从根节点到每一个节点的路径长度（经过的边数）与权值乘积的总和是最小的、就称为哈夫曼树。
 * 思路：
 * 把各个字符在整个串中出现的频率作为它的权重、通过使用0、1表示来缩短整个串的长度、可用于无损压缩。
 * 完成哈夫曼编码首先要先建立哈夫曼树、根据树中节点的路径、计算出对应节点的编码。
 * 总结：
 * 需求分析
 * 从一个文件中读取数据，统计文件的每个字节出现的频数，根据不同的这些频数构建赫夫曼树并实现编码译码分别保存到新的文件中。编码文件为原文件+“.ext”,译码文件为编码文件+“.txt”。
 * 养成良好习惯：每个小功能的实现都需要及时进行测试，第一个功能没写好就不要往下写，不然到时候出了错都没办法找原因。
 * 流程分析
 * 先从文件中初始化数据，用map来保存名值对。Byte对应出现次数。
 * 根据map创建树的节点。
 * 根据节点创建赫夫曼树。
 * 根据赫夫曼树进行编码。
 * 根据编码和源文件内容对其进行编码并保存到文件中。
 * 从已编码的文件中读取数据进行译码并保存到文件中。
 * 最终产生的文件和原文件是一模一样的即算成功。
 *
 * @author sunliangliang 2019-08-29 23:15
 * @version 1.0
 */
public class HaffmanCoding {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //测试
        String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
                + "depending on the characteristics of the data being compressed. 中华崛起";
        oriStr = "abcaa";
        HaffmanCoding haffmanCoding = new HaffmanCoding();
        System.out.println(haffmanCoding.encode(oriStr));

    }


    //1.统计词频
    private Map<Character, Integer> statistic(String str){
        char[] charArray = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray){
            Character character = new Character(c);
            if (map.containsKey(character)){
                map.put(character, map.get(character) + 1);
            }else{
                map.put(character, 1);
            }
        }
        return map;
    }

    //2.构建haffman树
    private Tree buildTree(String str, List<Node> leafs){
        Map<Character, Integer> statistics = statistic(str);
        Character[] keys = statistics.keySet().toArray(new Character[0]);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Character character : keys){
            Node node = new Node();
            node.chars = character.toString();
            node.frequence = statistics.get(character);
            priorityQueue.add(node);
            leafs.add(node);
        }
        int size = priorityQueue.size();
        for (int i = 1; i <= size - 1; i++){//跟while(queue.size() <=1一样)
            Node node1 = priorityQueue.poll();
            Node node2 = priorityQueue.poll();
            Node sumNode = new Node();
            sumNode.chars = node1.chars + node2.chars;
            sumNode.frequence = node1.frequence = node2.frequence;
            sumNode.leftNode = node1;
            sumNode.rightNode = node2;
            node1.parent = sumNode;
            node2.parent = sumNode;
            priorityQueue.add(sumNode);
        }
        Tree tree = new Tree();
        tree.root = priorityQueue.poll();
        return tree;
    }
    //3.有了tree就可以进行编码了
    private String encode(String str){
        if (str == null || str.equals("")) return "";
        char[] charArray = str.toCharArray();
        List<Node> leafNodes = new ArrayList<>();
        buildTree(str, leafNodes);
        Map<Character, String> encodInfo = buildEncodingInfo(leafNodes);
        StringBuffer buffer = new StringBuffer();
        for (char c : charArray){
            Character character = new Character(c);
            buffer.append(encodInfo.get(character));
        }
        return buffer.toString();
    }
    //辅助编码
    private Map<Character, String> buildEncodingInfo(List<Node> leafNodes){
        Map<Character, String> codewords = new HashMap<>();
        for (Node leafNode : leafNodes){
            Character character = new Character(leafNode.chars.charAt(0));
            String codeword = "";
            Node currentNode = leafNode;
            do {
                if (currentNode.isLeftChild()){
                    codeword = "0" +codeword;
                }else{
                    codeword = "1" + codeword;
                }
                currentNode = currentNode.parent;
            }while (currentNode.parent != null);
            codewords.put(character, codeword);
        }
        return codewords;
    }

    //4.解码，这里的解码逻辑不对，应该是给字典和已经coding的String进行解码
//    private String decode(String binaryStr){
//        if (binaryStr == null || binaryStr.equals("")) return "";
//        char[] binaryCharArray = binaryStr.toCharArray();
//        LinkedList<Character> binaryList = new LinkedList<>();
//        int size = binaryCharArray.length;
//        for (int i = 0; i < size; i++){
//            binaryList.addLast(new Character((binaryCharArray[i])));
//        }
//        List<Node> leafNodes = new ArrayList<>();
//        Tree tree = buildTree(statistics, leafNodes);
//        StringBuffer buffer = new StringBuffer();
//        while (binaryList.size() > 0){
//            Node node = tree.root;
//            do {
//                Character character = binaryList.removeFirst();
//                if (character.charValue() == '0'){
//                    node = node.leftNode;
//                }else{
//                    node = node.rightNode;
//                }
//            }while(!node.isLeaf());
//            buffer.append(node.chars);
//        }
//        return buffer.toString();
//    }
}

class Node implements Comparable<Node>{
    public int frequence;//权重
    public String chars;//值,为什么不是char ch，因为小节点要合并的！！！
    public Node parent;//父节点
    public Node leftNode;
    public Node rightNode;//左右孩子

    @Override
    public int compareTo(Node o) {
        return this.frequence - o.frequence;
    }

    public boolean isLeftChild(){
        return parent != null && this == parent.leftNode;
    }

    public boolean isLeaf(){//是不是叶子结点
        return chars.length() == 1;
    }
    public boolean isRoot(){//是不是根结点
        return parent == null;
    }

    @Override
    public String toString() {
        return chars + "---" + frequence;
    }
}

class Tree{
    public Node root;
}