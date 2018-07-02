package com.matrizaev.task_tunnel.generator;

import com.matrizaev.task_tunnel.entity.Train;

import java.util.ArrayList;
import java.util.List;

public class TrainGenerator {

    private TrainGenerator() {

    }
    private static class TrainGeneratorHolder{
        private static final TrainGenerator INSTANCE = new TrainGenerator();
    }

    public static TrainGenerator getInstance() {return TrainGeneratorHolder.INSTANCE;}

    public List<Train> generateTrainList(int quantity) {
        ArrayList<Train> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            list.add(new Train());
        }
        return list;
    }
}
