package com.matrizaev.task_tunnel.logic;

import com.matrizaev.task_tunnel.entity.Train;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NorthTunnel implements TunnelStrategy {

    private static final int TUNNEL_TOTAL_CAPACITY = 5;
    private static final int TUNNEL_DIRECTION_CAPACITY = 3;
    private static Logger logger = LogManager.getLogger();
    private Semaphore semaphore = new Semaphore(TUNNEL_TOTAL_CAPACITY);
    private Lock lockEW = new ReentrantLock(false);
    private Lock lockWE = new ReentrantLock(false);
    private Map<Integer, Train> trainsInNorthTunnel = new HashMap<>();
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

        if (trainsInNorthTunnel.size() <= TUNNEL_TOTAL_CAPACITY && numOfEW <= TUNNEL_DIRECTION_CAPACITY) {
            try {
                System.out.println(train.toString() + " is waiting near the Northern tunnel...");
                semaphore.acquire();
                lockEW.lock();
                trainsInNorthTunnel.put(train.getTrainNumber(), train);
                numOfEW++;
                lockEW.unlock();
                isEntered = true;
                System.out.println(train.toString() + " has entered the Northern tunnel! Num of trains: "
                        + trainsInNorthTunnel.size());

                System.out.println(train.toString() + " is MOVING through the Northern tunnel");
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                logger.log(Level.WARN, "Exception during thread sleep in class " + this.getClass());

            } finally {
                System.out.println(train.toString() + " has EXITED the Northern tunnel");
                trainsInNorthTunnel.remove(train.getTrainNumber());
                numOfEW--;
                semaphore.release();
            }
        }
        return isEntered;
    }

    private boolean enterTunnelForWE(Train train) {
        boolean isEntered = false;

        if (trainsInNorthTunnel.size() <= TUNNEL_TOTAL_CAPACITY && numOfWE <= TUNNEL_DIRECTION_CAPACITY) {
            try {
                System.out.println(train.toString() + " is waiting near the Northern tunnel...");
                semaphore.acquire();
                lockWE.lock();
                trainsInNorthTunnel.put(train.getTrainNumber(), train);
                numOfWE++;
                lockWE.unlock();
                isEntered = true;
                System.out.println(train.toString() + " has entered the Northern tunnel! Num of trains: "
                        + trainsInNorthTunnel.size());

                System.out.println(train.toString() + " is MOVING through the Northern tunnel");
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                logger.log(Level.WARN, "Exception during thread sleep in class " + this.getClass());
            } finally {
                System.out.println(train.toString() + " has EXITED the Northern tunnel");
                trainsInNorthTunnel.remove(train.getTrainNumber());
                numOfWE--;
                semaphore.release();
            }
        }
        return isEntered;
    }
}
