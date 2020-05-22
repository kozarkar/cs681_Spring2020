package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.ArrayList;

public class TwoStepThreadTerminationBankAcc {
    
    private ReentrantLock lock;
    private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
    private TwoStepThreadTerminationBankAcc acc;
    private boolean done = true;
    private double balance = 0;
    
    public TwoStepThreadTerminationBankAcc(){
        lock = new ReentrantLock();
        sufficientFundsCondition = lock.newCondition();
        belowUpperLimitFundsCondition = lock.newCondition();
        acc = this;
    }

    public void deposit(double amount){
        lock.lock();
        System.out.println("Locked Transaction");
        System.out.println("Depositing.. " + " [Thread id :" + Thread.currentThread().getId() + "]" + " Amount "+ amount + " deposited!.");
        while(balance >= 300){
            System.out.println(Thread.currentThread().getId() +
                    " await(): Balance exceeds the upper limit.");
            try {
                belowUpperLimitFundsCondition.await();
            } catch (InterruptedException e) {
                done=false;
                System.out.println(Thread.currentThread().getId() +
                        " Terminated ");
                System.out.println("Balance : " + balance);
                break;
            }
        }
        if (done) {
            System.out.print("Previous balance : " + balance );
            balance += amount;
            System.out.println(Thread.currentThread().getId() +
                    " New balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        else if (!done) {
            System.out.println("Transaction Cancelled.");
        }
        lock.unlock();
        System.out.println("--Unlocked--");
    }

    public void withdraw(double amount){
        lock.lock();
        System.out.println("Locked Transaction");
        System.out.println("customer " + Thread.currentThread().getId() + " withdrawing.." + " Amount " +amount + " withdrawn!!.");
        while(balance <= 0){
            System.out.println(Thread.currentThread().getId() +
                    "await(): Insufficient funds");
            try {
                sufficientFundsCondition.await();
            } catch (InterruptedException e) {
                done=false;
                System.out.println(Thread.currentThread().getId() +
                        "Terminated ");
                System.out.println("Balance : " + balance);
                break;
            }
        }
        if (done) {
            System.out.print("Previous balance : " + balance);
            balance -= amount;
            System.out.println(Thread.currentThread().getId() +
                    " New balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        else if (!done) {
            System.out.println("Transaction Cancelled!!");
        }
        lock.unlock();
        System.out.println("--Unlocked-- \n");
    }

    public static void main(String[] args){
        //System.out.println("---------------------------------------");
        System.out.println("Bank Account System");
        System.out.println("---\n");
        TwoStepThreadTerminationBankAcc bankAccount = new TwoStepThreadTerminationBankAcc();
        ArrayList<Thread> Deposits = new ArrayList<Thread>();
        Thread Withdraw = new Thread( bankAccount.new WithdrawRunnable());

        for(int i = 0; i < 3; i++){
            Deposits.add(new Thread( bankAccount.new DepositRunnable()));
        }

        for(int j = 0; j <= Deposits.size() - 1; j++) {
            Deposits.get(j).start();
        }

        Withdraw.start();
        for(int k = 0; k <= Deposits.size() - 1; k++) {
            Deposits.get(k).interrupt();
        }
        Withdraw.interrupt();
    }

    private class DepositRunnable implements Runnable{
        public void run(){
            while(done) {
                acc.deposit(100);
            }
        }
    }

    private class WithdrawRunnable implements Runnable{
        public void run(){
            while(done) {
                acc.withdraw(100);
            }
        }
    }
}