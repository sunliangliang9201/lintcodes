package com.sunll.lintcode.normal.producerconsumer;

/**
 * <p>desc: 仓库接口</p>
 *
 * @author sunliangliang 2019-08-29 19:44
 * @version 1.0
 */
public interface Store<T> {
    void produce(T t);
    void consume();
}
