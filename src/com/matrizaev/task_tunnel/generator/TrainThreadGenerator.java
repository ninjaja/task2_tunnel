package com.matrizaev.task_tunnel.generator;


import com.matrizaev.task_tunnel.entity.Train;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TrainThreadGenerator {

    private static Logger logger = LogManager.getLogger();

    private TrainThreadGenerator() {
    }

    private static class TrainThreadGeneratorHolder {
        private static final TrainThreadGenerator INSTANCE = new TrainThreadGenerator();
    }

    public static TrainThreadGenerator getInstance() {
        return TrainThreadGeneratorHolder.INSTANCE;
    }

    public void generateTrainThreads(List<Train> trainList) {
        System.out.println("starting threads...");
        ExecutorService executor = Executors.newFixedThreadPool(trainList.size());
        System.out.println(trainList.size() + " threads started");
        logger.log(Level.INFO, trainList.size() + " threads started");
        ArrayList<Future<Boolean>> list = new ArrayList<>();
        for(Train train : trainList) {
            list.add(executor.submit(train));
            logger.log(Level.INFO, train.toString() + " submitted by executor");

        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, "Exception during thread sleep in class " + this.getClass());
        }

        for(Future future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException|ExecutionException e) {
                logger.log(Level.WARN, "Exception during future.get() in class " + this.getClass() );
            }
        }
        executor.shutdown();
    }
}
