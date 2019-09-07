package com.sunll.lintcode.hard.callback;

import java.util.concurrent.Callable;

/**
 * <p>desc: 同学B</p>
 * 作用：类似帮助别人解决问题的人
 * 一直等待别人的调用，等到处理完毕进行回调调用者的回调函数，所以需要持有调用者的引用
 *
 * @author sunliangliang 2019-09-07 09:30
 * @version 1.0
 */
public class BottomService<T> {

    public void compute(UpperService upperService, T a) throws InterruptedException {
        System.out.println("B开始计算");
        Thread.sleep(2000);//计算两秒
        T res = (T) (a+""+a);//这里是泛型，所以凑活着来吧
        upperService.writeAnswer(res);//回调，让A把结果写到试卷上
    }

}
