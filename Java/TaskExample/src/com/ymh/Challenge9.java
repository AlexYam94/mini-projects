package com.ymh;

import java.util.concurrent.locks.ReentrantLock;

public class Challenge9 {
    public static void main(String[] args) {
        ReentrantLock studentLock = new ReentrantLock();
        ReentrantLock tutorLock = new ReentrantLock();
        Tutor tutor = new Tutor(studentLock,tutorLock);
        Student student = new Student(tutor,studentLock,tutorLock);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }


    static class Tutor {
        private Student student;
        private ReentrantLock studentLock;
        private ReentrantLock tutorLock;

        public Tutor(ReentrantLock studentLock, ReentrantLock tutorLock) {
            this.studentLock = studentLock;
            this.tutorLock = tutorLock;
        }

        public void setStudent(Student student) {
            this.student = student;
        }


        public void studyTime() {
            tutorLock.lock();
            try {
                System.out.println("Tutor has arrived");
                studentLock.lock();
                try {
                    try {
//                        // wait for student to arrive
                        studentLock.unlock();
                        Thread.sleep(300);
//                        this.wait();
                        studentLock.lock();
                    } catch (InterruptedException e) {

                    }
                    student.startStudy();
                    System.out.println("Tutor is studying with student");
                } finally {
                    studentLock.unlock();
                }
            } finally {
                tutorLock.unlock();
            }


        }

        public void getProgressReport() {
            // get progress report
            System.out.println("Tutor gave progress report");
        }
    }

    static class Student {

        private Tutor tutor;
        private ReentrantLock studentLock;
        private ReentrantLock tutorLock;

        public Student(Tutor tutor, ReentrantLock studentLock, ReentrantLock tutorLock) {
            this.tutor = tutor;
            this.studentLock = studentLock;
            this.tutorLock = tutorLock;
        }


        public void startStudy() {
            // study
            System.out.println("Student is studying");
        }

        public void handInAssignment() {
            try{
                Thread.sleep(300);
            }catch (InterruptedException e){

            }
            tutorLock.lock();
            try {
                tutor.getProgressReport();
                studentLock.lock();
                try{
                    System.out.println("Student handed in assignment");
//                    tutor.notifyAll();
                }finally {
                    studentLock.unlock();
                }
            }finally {
                tutorLock.unlock();
            }


        }
    }
}
