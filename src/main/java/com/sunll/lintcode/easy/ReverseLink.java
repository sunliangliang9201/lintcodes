package com.sunll.lintcode.easy;

/**
 * <p>desc: 反转单向链表</p>
 * @author sunliangliang 2019-08-21 09:11
 * @version 1.0
 */
public class ReverseLink {
    public static void main(String[] args) {
        MyNode node4 = new MyNode(4, null);
        MyNode node3 = new MyNode(3, node4);
        MyNode node2 = new MyNode(2, node3);
        MyNode node1 = new MyNode(1, node2);
        MyNode head = node1;
        while(null != head){
            System.out.println(head.value);
            head = head.next;
        }
        System.out.println("-------------");
        MyNode head2 = reverseLink(node1);
        while(null != head2){
            System.out.println(head2.value);
            head2 = head2.next;
        }
    }

    /**
     * 反转单向链表方法很多，可以用一个栈或者双向队列来实现，如果要求空间复杂度是O(1)的话
     * @param head
     * @return
     */
    public static MyNode reverseLink(MyNode head){
        if (null == head || null == head.next) return head;
        MyNode pre = null;
        MyNode tmp = head;
        while(null != head){
            tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    static class MyNode{
        private int value;
        private MyNode next;

        public MyNode(int value, MyNode next){
            this.value = value;
            this.next = next;
        }
    }
}

