package com.learnJava.events;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer {

    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQ = new LinkedBlockingQueue<>();

        Prod p = new Prod(sharedQ);
        Consumer c = new Consumer(sharedQ);

        p.start();
        c.start();
    }
}

class Prod extends Thread {
    private final BlockingQueue<Integer> sharedQueue;

    public Prod(BlockingQueue<Integer> aQueue) {
        super("PRODUCER");
        this.sharedQueue = aQueue;
    }

    @Override
    public void run() {
        // no synchronization needed
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(getName() + " produced " + i);
                sharedQueue.put(i);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class Consumer extends Thread {
    private final BlockingQueue<Integer> sharedQueue;

    public Consumer(BlockingQueue<Integer> aQueue) {
        super("CONSUMER");
        this.sharedQueue = aQueue;
    }

    public void run() {
        try {
            while (true) {
                Integer item = sharedQueue.take();
                System.out.println(getName() + " consumed " + item);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
