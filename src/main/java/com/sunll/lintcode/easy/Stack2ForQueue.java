package com.sunll.lintcode.easy;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>desc: 用两个栈实现一个队列</p>
 * 这个题比较简单，比较老，但是还是写一下把(百度)
 * 思路：两个栈，一个作为入队列的栈，一个作为出队列的栈，每次操作先判断是否需要两个栈进行出栈+压栈转移
 *
 * @author sunliangliang 2019-09-02 22:25
 * @version 1.0
 */
public class Stack2ForQueue<T> {

    private Stack<T> stack1 = new Stack<>();//用于入队
    private Stack<T> stack2 = new Stack<>();//用于出队

    /**
     * 入队add函数
     * @param data
     */
    public void add(T data){
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(data);
    }

    /**
     * 出队poll函数
     * @return
     */
    public T poll(){
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.isEmpty()? null: stack2.pop();
    }


    public static void main(String[] args) {
        Stack2ForQueue<String> queue = new Stack2ForQueue<>();
        queue.add("a");
        queue.add("c");
        queue.add("m");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.add("xx");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
