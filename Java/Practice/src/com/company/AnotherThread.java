package com.company;

public class AnotherThread extends Thread {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public void run(){
        System.out.println(ANSI_BLUE+"hello AnotherThread here");
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            System.out.println(ANSI_BLUE+"AnotherThread: wake me up");
        }

        System.out.println(ANSI_BLUE+"Three seconds passed and I am awake");
    }
}
