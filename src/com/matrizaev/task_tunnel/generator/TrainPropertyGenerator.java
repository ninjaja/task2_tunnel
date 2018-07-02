package com.matrizaev.task_tunnel.generator;

import java.util.Random;

public class TrainPropertyGenerator {

    private enum direction {WE,EW}

    private enum tunnelName {NORTH,SOUTH}

    private static int number;

    private TrainPropertyGenerator(){}

    public static String generateDirection() {
        Random random = new Random();
        return direction.values()[random.nextInt(direction.values().length)].name();
    }

    public static int generateNumber() {
        return number++;
    }

    public static String generateTunnel() {
        Random random = new Random();
        return tunnelName.values()[random.nextInt(tunnelName.values().length)].name();
    }

}
