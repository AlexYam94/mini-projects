package com.company;

import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
	// write your code here
        System.out.println(ANSI_PURPLE + "hello from the main thread");
        Thread anotherThread = new AnotherThread();
        Thread threadColor = new ThreadColor();
        anotherThread.start();
        Thread runnableThread = new Thread(new MyRunnable(){
            @Override
            public void run() {
                System.out.println(ANSI_RED+"Hello from the anonymous Runnable");
                try{
                    anotherThread.join(1000);
                    System.out.println(ANSI_RED+"AnotherThread terminated or time out, so I am running now");
                } catch (InterruptedException e){{
                    System.out.println(ANSI_RED+"I could not wait after all. I was interrupted");
                }}
            }

        });
        new Thread(){
                public void run(){System.out.println(ANSI_GREEN + "Hello from anonymous thread"); }
        }.start();

        threadColor.start();
        runnableThread.start();
        System.out.println(ANSI_PURPLE+"Hello again from the main thread.");
    }

    public static void saveObject(ISaveable objectToSave){
        for(int i=0;i<objectToSave.Write().size();i++){
            System.out.println("Saving "+objectToSave.Write().get(i)+" to storage device");
        }
    }

    public static void loadObject(ISaveable objectToLoad){
        ArrayList<String> values = readValues();
        objectToLoad.Read(values);
    }

    public static ArrayList<String> readValues() {
        ArrayList<String> values = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        System.out.println("Choose\n" +
                "1 to enter a string\n" +
                "0 to quit");

        while (!quit) {
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.print("Enter a string: ");
                    String stringInput = scanner.nextLine();
                    values.add(index, stringInput);
                    index++;
                    break;
            }
        }
        return values;
    }


}
