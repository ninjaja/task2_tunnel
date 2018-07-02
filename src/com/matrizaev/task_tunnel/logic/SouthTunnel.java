package com.matrizaev.task_tunnel.logic;

import com.matrizaev.task_tunnel.entity.Train;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SouthTunnel implements TunnelStrategy {

    private static final int TUNNEL_TOTAL_CAPACITY = 5;
    private static final int TUNNEL_DIRECTION_CAPACITY = 3;
    private Semaphore semaphore = new Semaphore(TUNNEL_TOTAL_CAPACITY);
    private Lock lockEW = new ReentrantLock(false);
    private Lock lockWE = new ReentrantLock(false);
    private Map<Integer, Train> trainsInSouthTunnel = new HashMap<>();
    private int numOfEW;
    private int numOfWE;

    @Override
    public boolean determineDirection(Train train) {
        boolean isDetermined = false;
        if (train.getDirection().equals("EW")) {
            if (this.enterTunnelForEW(train)) {
                isDetermined = true;
            }
        } else {
            if (this.enterTunnelForWE(train)) {
                isDetermined = true;
            }
        }
        return isDetermined;
    }

    private boolean enterTunnelForEW(Train train) {
        boolean isEntered = false;

            if (trainsInSouthTunnel.size() <= TUNNEL_TOTAL_CAPACITY && numOfEW <= TUNNEL_DIRECTION_CAPACITY) {
                try {
                    System.out.println(train.toString() + " is waiting near the Southern tunnel...");
                    semaphore.acquire();
                    lockEW.lock();
                    trainsInSouthTunnel.put(train.getTrainNumber(), train);
                    numOfEW++;
                    lockEW.unlock();
                    isEntered = true;
                    System.out.println(train.toString() + " has entered the Southern tunnel! Num of trains: " + trainsInSouthTunnel.size());

                    System.out.println(train.toString() + " is MOVING through the Southern tunnel");
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace(); //TODO: change to logger
                } finally {
                    System.out.println(train.toString() + " has EXITED the Southern tunnel");
                    trainsInSouthTunnel.remove(train.getTrainNumber());
                    numOfEW--;
                    semaphore.release();
                }
            }
        return isEntered;
    }

    private boolean enterTunnelForWE(Train train) {
        boolean isEntered = false;

        if (trainsInSouthTunnel.size() <= TUNNEL_TOTAL_CAPACITY && numOfWE <= TUNNEL_DIRECTION_CAPACITY) {
            try {
                System.out.println(train.toString() + " is waiting near the Southern tunnel...");
                semaphore.acquire();
                lockWE.lock();
                trainsInSouthTunnel.put(train.getTrainNumber(), train);
                numOfWE++;
                lockWE.unlock();
                isEntered = true;
                System.out.println(train.toString() + " has entered the Southern tunnel! Num of trains: " + trainsInSouthTunnel.size());

                System.out.println(train.toString() + " is MOVING through the Southern tunnel");
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace(); //TODO: change to logger
            } finally {
                System.out.println(train.toString() + " has EXITED the Southern tunnel");
                trainsInSouthTunnel.remove(train.getTrainNumber());
                numOfWE--;
                semaphore.release();
            }
        }
        return isEntered;
    }
}
