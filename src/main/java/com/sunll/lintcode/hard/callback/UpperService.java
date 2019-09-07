package com.sunll.lintcode.hard.callback;

/**
 * <p>desc: 回调接口</p>
 * 一开始我们可能不太理解哈，所以我在这里做一下解释
 * 回调：A调用B的x()方法，B调用x()方法之后回调A中的一个y()方法，这就是回调的机制。
 * 而回调分为同步回调和异步回调！！
 * 同步回调：A等待B的x()调用，调用完毕后自己主动调用自己的y()方法。
 * 异步回调：A调用B的x()之后不等待B是否真的执行而继续，当B的x()执行完毕后，B进行回调A中的y()。
 * 所以异步回到就是我们想要实现的回调。
 * 我们要实现一个东西：
 *
 * ***同学A在数学题，遇到一个问题想要问问B，问完B之后不需要等着，因为A不知道B什么时候会回复自己，可能在忙、可能要花时间计算。A继续做后面的题，当B解决完问题之后通过回调将结果返回给A。
 *
 * 其中：
 * bottomService ———— B同学那些人：底层服务类，供上层服务调用
 * UpperService  ———— Callback接口：上层服务类的接口
 * UpperServiceIMpl ———— A同学那些人：上层服务，要调用底层服务，并且提供可以回调的函数
 * @author sunliangliang 2019-09-07 09:17
 * @version 1.0
 */
public interface UpperService<T> {

    void askQuestion(T a);//调用了问别人问题

    void writeAnswer(T res);//别人得到结果后回调这个函数，把结果写到试卷上
}
