package com.sunll.lintcode.hard.callback;

/**
 * <p>desc: 测试回调</p>
 *
 * @author sunliangliang 2019-09-07 09:58
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        //底层服务启动
        BottomService<Integer> bottomService = new BottomService<>();
        //上层服务启动
        UpperService<Integer> upperService = new UpperServiceImpl<>(bottomService);
        //上层服务调用底层服务
        upperService.askQuestion(12);
    }
}
