package com.sunll.lintcode.normal.skiplist;


import java.util.Random;

/**
 * <p>desc: 跳跃链表</p>
 * 跳跃链表是可以使链表的查询变为线性复杂度的一种实现！
 * 空间换时间，跳跃链表也是redis中的zset的实现方式
 *
 *
 * 思想：理想的跳跃表可以达到时间复杂度为logn的查询。但是有个问题，如果存在节点的插入和删除的话，得维持跳跃表的结构，怎么办？？
 * 关键点：从统计学、概率学角度来讲，使用抛硬币的1/2随机概率来达到跳跃表每往上走一层数据都减少一半的目的，又因为是随机的，随意当数据插入和删除的时候不用调整结构，比平衡二叉树强多了
 * 我们采用抛硬币的方式来决定新元素插入的最高层数，这当然不能在程序中实现。代码中，我们采用随机数生成的方式来获取新元素插入的最高层数。我们先估摸一下n的规模，然后定义跳跃表的最大层数maxLevel，那么底层，也就是第0层，元素是一定要插入的，概率为1；最高层，也就是maxLevel层，元素插入的概率为1/2^maxLevel。
 * 我们先随机生成一个范围为0~2^maxLevel-1的一个整数r。那么元素r小于2^(maxLevel-1)的概率为1/2，r小于2^(maxLevel-2)的概率为1/4，……，r小于2的概率为1/2^(maxLevel-1)，r小于1的概率为1/2^maxLevel。
 * 举例，假设maxLevel为4，那么r的范围为0~15，则r小于8的概率为1/2，r小于4的概率为1/4，r小于2的概率为1/8，r小于1的概率为1/16。1/16正好是maxLevel层插入元素的概率，1/8正好是maxLevel层插入的概率，以此类推。
 * 通过这样的分析，我们可以先比较r和1，如果r<1，那么元素就要插入到maxLevel层以下；否则再比较r和2，如果r<2，那么元素就要插入到maxLevel-1层以下；再比较r和4，如果r<4，那么元素就要插入到maxLevel-2层以下……如果r>2^(maxLevel - 1)，那么元素就只要插入在底层即可。
 *
 * @author sunliangliang 2019-08-30 19:41
 * @version 1.0
 */
public class SkipList<T extends Comparable<? super T>> {
    private final int DEFAULT_LEVEL = 5;//默认层数暂且设置为5吧
    private int maxLevel;//如果指定了level数，就用这个
    private int level = 0;//当前层
    private int[] powers;//在生成节点到底需要生成几层时，这个powers就是那些根据maxLevel计算出来的8 4 2 1 0那些临界值
    private final SkipListNode<T> HEADER;//链表的头部，每层都有
    private final Random random = new Random();//随机数生成器

    public SkipList(int maxLevel){
        this.maxLevel = maxLevel <= 0? DEFAULT_LEVEL: maxLevel;
        HEADER = new SkipListNode<>(maxLevel, null);//初始化maxLevel的头指针，这些节点是null的。
        computePowers(maxLevel);
    }
    public SkipList(){
        this.maxLevel = DEFAULT_LEVEL;
        HEADER = new SkipListNode<>(maxLevel, null);//初始化maxLevel的头指针，这些节点是null的。
        computePowers(maxLevel);
    }
    /**
     * 初始化powers
     */
    private void computePowers(int maxLevel){
        powers = new int[maxLevel];
        powers[maxLevel-1] = (2 << (maxLevel-1)) -1;//如果是4层，那么根据上面的分析应该有的临界值有 1 3 7 15
        for (int i = maxLevel-2; i>=0; i--){
            powers[i] = powers[i+1] - (2 << i);
        }
    }
    /**
     * 用随机数来判断随机的层数,这个层数是0 1 2 3，不是从1开始
     */
    public int randomLevel(){
        int key = Math.abs(random.nextInt()) % powers[maxLevel-1];//得到【0，15】的整数
        int res = 0;
        for (int i = maxLevel-1; i >=0; i--){
            if (powers[i] <= key){
                break;
            }
            res++;
        }
        return res-1;
    }

    /**
     * 检查跳表中是否包含val数据
     */
    public boolean search(T val){
        //cur指向跳表头结点
        SkipListNode<T> cur = HEADER;
         //从顶层开始查找当前层的链表中是否包含节点node，如果包含node节点，直接返回true；否则在下一层中查找是否包含node节点。
         //如果最底层的链表也不包含node节点，则放回false。
        for (int i = level; i >= 0; i--) {
            while (cur.next[i] != null && cur.next[i].value.compareTo(val) < 0) {
                cur = cur.next[i];
            }
            if (cur.next[i] != null && cur.next[i].value.equals(val)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 插入元素
     */
    public void insert(T val){
        SkipListNode<T> cur = HEADER;
        SkipListNode<T>[] predecessors = new SkipListNode[maxLevel];
        //找出每层中待插入节点的前继节点
        for (int i = level; i>=0; i--){
            cur = HEADER;
            while(cur.next[i] != null && cur.next[i].value.compareTo(val) < 0){
                cur = cur.next[i];
            }
            predecessors[i] = cur;
        }
        cur = cur.next[0];
        int nextLevel = randomLevel();
        //开始一层一层的插入节点
        if (cur == null || !cur.value.equals(val)){//插入的条件是为null或者值不相同
            if (nextLevel > level){
                predecessors[nextLevel] = HEADER;
            }
            SkipListNode<T> node = new SkipListNode<>(maxLevel,val);
            for (int i = level; i >= 0; i--){
                node.next[i] = predecessors[i].next[i];
                predecessors[i].next[i] = node;
            }
        }
    }

    /**
     * 删除节点
     */
    public void delete(T val){
        SkipListNode cur = HEADER;
        SkipListNode<T>[] predecessors = new SkipListNode[maxLevel];
        //寻找待删除节点在不同层上的前继节点
        for (int i = level; i >=0 ;i--){
            while(cur.next != null && cur.next[i].value.compareTo(val) < 0){
                cur = cur.next[i];
            }
            predecessors[i] = cur;
        }
        cur = cur.next[0];
        if (!cur.value.equals(val)){
            return;//没有要删除的节点
        }
        for (int i = level; i>=0; i--){
            if (!predecessors[i].next[i].value.equals(val)){
                continue;
            }
            predecessors[i].next[i] = cur.next[i];
        }
        //如果删除元素val后level层元素树木为0，则减少一层
        while (level >0 && HEADER.next[level] == null){
            level--;
        }
    }
    /**
     * 输出跳表中元素
     * @return
     */
    public java.lang.String toString(){
        StringBuilder sb = new StringBuilder();
        SkipListNode<T> cur = HEADER.next[0];
        sb.append("{");
        while (cur.next[0] != null){
            sb.append(cur.value);
            sb.append(",");
            cur = cur.next[0];
        }
        sb.append(cur.value);
        sb.append("}");
        return sb.toString();
    }

}
class Test{
    public static void main(String[] args) {
        //测试
        SkipList<Integer> skipList = new SkipList<>();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(-1);
        skipList.delete(1);
        System.out.println(skipList.search(1));
        System.out.println(skipList);

    }
}




























