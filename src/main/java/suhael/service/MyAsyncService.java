package suhael.service;

import javax.servlet.AsyncContext;

public class MyAsyncService implements Runnable {

    AsyncContext ac;

    public MyAsyncService(AsyncContext ac) {
        this.ac = ac;
    }

    @Override
    public void run() {
        ac.complete();
    }
}
