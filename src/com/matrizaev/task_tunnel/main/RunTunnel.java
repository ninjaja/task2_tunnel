package com.matrizaev.task_tunnel.main;

import com.matrizaev.task_tunnel.entity.Train;
import com.matrizaev.task_tunnel.generator.TrainGenerator;
import com.matrizaev.task_tunnel.generator.TrainThreadGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class RunTunnel {

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        int trainQuantity = 20;                 //set total number of trains here
        TrainGenerator generator = TrainGenerator.getInstance();
        List<Train> generatedTrainList = generator.generateTrainList(trainQuantity);
        System.out.println(trainQuantity + " trains created...");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, "Exception during thread sleep in RunTunnel class");
        }
        TrainThreadGenerator.getInstance().generateTrainThreads(generatedTrainList);
    }
}
