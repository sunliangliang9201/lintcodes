package com.sunll.lintcode.normal.skiplist;

/**
 * <p>desc: 跳表的每个节点</p>
 *
 * @author sunliangliang 2019-08-30 21:58
 * @version 1.0
 */
public class SkipListNode<T extends Comparable<? super T>> {
    public T value;//数据
    public SkipListNode<T>[] next;//节点指向第i层的节点next[i]，关于为什么用数组，出于效率的考虑


    public SkipListNode(int maxLevel, T val){
        this.value = val;
        this.next = new SkipListNode[maxLevel];
    }

}
