package com.sunll.lintcode.hard.callback;

/**
 * <p>desc: 同学A</p>
 * 实现callback接口，作用就是主动问别人问题，问完继续做题，等待别人回调自己的写答案的方法即可
 * @author sunliangliang 2019-09-07 09:29
 * @version 1.0
 */
public class UpperServiceImpl<T> implements UpperService<T> {

    BottomService<T> bottomService;

    public UpperServiceImpl(BottomService bottomService){
        this.bottomService = bottomService;
    }

    @Override
    public void askQuestion(final T a) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("A向B提问问题");
                    bottomService.compute(UpperServiceImpl.this,a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("继续做后面的题...");
    }

    @Override
    public void writeAnswer(T res) {
        System.out.println("A向试卷上写上了答案" + res);
    }
}
