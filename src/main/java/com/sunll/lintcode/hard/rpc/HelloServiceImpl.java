package com.sunll.lintcode.hard.rpc;

/**
 * <p>desc: 服务借口实现</p>
 *
 * @author sunliangliang 2019-08-15 07:59
 * @version 1.0
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sqyHello(String name) {
        System.out.println("hello " + name);
    }
}
