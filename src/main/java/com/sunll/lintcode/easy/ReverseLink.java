package com.sunll.lintcode.easy;

/**
 * <p>desc: 反转单向链表</p>
 * 1.单向链表反转
 * 2.判断链表是否是回文的a-b-c-c-b-a
 * 3.判断链表是否成环
 * 很不可思议的一件事：金山云2017年校招笔试题出了第三题，2019年社招第三面出了第一题，第四面出了第二题。
 * 有点意思
 * @author sunliangliang 2019-08-21 09:11
 * @version 1.0
 */
public class ReverseLink {
    public static void main(String[] args) {
        MyNode node4 = new MyNode(4, null);
        MyNode node3 = new MyNode(3, node4);
        MyNode node2 = new MyNode(3, node3);
        MyNode node1 = new MyNode(4, node2);
        MyNode head = node1;
//        while(null != head){
//            System.out.println(head.value);
//            head = head.next;
//        }
//        System.out.println("-------------");
//        MyNode head2 = reverseLink(node1);
//        while(null != head2){
//            System.out.println(head2.value);
//            head2 = head2.next;
//        }

        //System.out.println(isPalindromic(head));
        node4.next = node1;
        System.out.println(isCycle(head));
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

    /**
     * 判断是否是回文的单向链表
     * 思路：该题考察的还是单向链表反转，但是一开始我只想到了空间复杂度为0(n)的，很简单就是进行反转然后依次比较即可，但是要求是时间和空间复杂度都是O（n）
     * 后来经过面试官提示：并不需要把链表全部反转！！！我一想，我去，只需要反转一般而且还不用占用额外空间
     * @param head
     * @return
     */
    public static boolean isPalindromic(MyNode head){
        int count = 0;
        MyNode cur = head;
        while (cur != null){
            count++;
            cur = cur.next;
        }
        if (count <= 1) return false;
        int mid = count / 2;
        //开始反转mid个节点
        MyNode pre = null;
        MyNode tmp = head;
        while (mid > 0){
            tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
            mid--;
        }
        if (count % 2 != 0) head = head.next;//如果是奇数个
        while (pre != null && head != null){
            if (pre.value == head.value){
                pre = pre.next;
                head = head.next;
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断链表是否是有环的
     * 思想：用两个指针往后跑，一个指针是另一个指针速度的二倍，那么如果存在环的话，肯定会在某时刻相遇
     * 如果存在环，那么相遇点就是环的入口，从此时开始计数，再碰见一次，慢的计数就是环的长度
     * @param head
     * @return
     */
    public static boolean isCycle(MyNode head){
        if (null == head || null == head.next) return false;
        MyNode slow = head.next;
        MyNode fast = head.next.next;
        while (slow != fast){
            if (null == fast || null == fast.next){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
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

