package com.company;

import java.util.concurrent.TimeUnit;

public class MyCyclicBarrier {
    private int initialParties;
    private int waitingParties = 0;
    private Runnable action;
    boolean haveRunnable = false;

    public MyCyclicBarrier(int parties){
        this.initialParties = parties;
    }

    public MyCyclicBarrier(int parties, Runnable action){
        this.initialParties = parties;
        this.action = action;
        haveRunnable = true;
    }

    //waiting for initialParts threads near barrier
    public synchronized void await() throws InterruptedException{
        waitingParties++;
        if(waitingParties == initialParties){
            //reset barrier
            waitingParties = 0;
            if(haveRunnable) {
                action.run();
            }
            notifyAll();
        }
        else{
            this.wait();
        }
    }

    //waiting for initialParts threads near barrier or for time is passed
    public synchronized int await(long timeout, TimeUnit unit) throws InterruptedException{
        waitingParties++;
        if(waitingParties == initialParties){
            waitingParties = 0;
            action.run();
            notifyAll();
            return initialParties;
        }
        else{
            this.wait(unit.convert(timeout, unit));
        }
        return waitingParties;
    }

    public int getParties() {
        return initialParties;
    }

    public synchronized int getNumberWaiting(){
        return waitingParties;
    }
}
