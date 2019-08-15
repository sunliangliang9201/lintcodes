package com.sunll.lintcode.hard.rpc;

import java.io.IOException;

/**
 * <p>desc: RPCserver端接口</p>
 *
 * @author sunliangliang 2019-08-15 08:00
 * @version 1.0
 */
public interface Server {
    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();
}
