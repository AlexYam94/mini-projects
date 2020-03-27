package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.company.Main.EOF;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
	// write your code here
    ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);
//    ReentrantLock bufferLock = new ReentrantLock();

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    MyProducer producer = new MyProducer(buffer,ThreadColor.ANSI_BLUE);
    MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
    MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN);

    executorService.execute(producer);
    executorService.execute(consumer1);
    executorService.execute(consumer2);

    Future<String> future = executorService.submit(new Callable<String>() {
        @Override
        public String call() throws Exception {
            System.out.println(ThreadColor.ANSI_RED+"I'm being printed for the callable class");
            return "This is the callable result";
        }
    });

    try {
        System.out.println(future.get());
    }catch (ExecutionException e){
        System.out.println("Something went wrong");
    }catch (InterruptedException e){
        System.out.println("Thread running the task was interrupted");
    }

    executorService.shutdown();
    }
}

class MyProducer implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1","2","3","4","5"};
        for(String num: nums){
            try {
                System.out.println(color + "Adding..." + num);
                buffer.put(num);

                Thread.sleep(random.nextInt(2000));
            }
            catch (InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }
        System.out.println(color+"Adding EOF and exiting...");
        try {
            buffer.put("EOF");
        }catch (InterruptedException e){
            System.out.println("Producer was interrupted");
        }
    }
}

class MyConsumer implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {

        while (true){
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {
                    System.out.println("Consumer was interrupted");
                }
            }
        }
    }
}

class Countdown{
    private int i=0;
    public void doCountdown(){
        String color;

        switch(Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i=" + i);
            }
        }
    }
}

class CountdownThread extends Thread{
    private Countdown threadCountdown;
    public CountdownThread(Countdown countdown){
        this.threadCountdown = countdown;
    }

    public void run(){
        threadCountdown.doCountdown();
    }
}
