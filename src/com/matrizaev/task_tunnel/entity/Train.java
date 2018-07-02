package com.matrizaev.task_tunnel.entity;

import com.matrizaev.task_tunnel.generator.TrainPropertyGenerator;
import com.matrizaev.task_tunnel.logic.TunnelConfigurator;

import java.util.Objects;
import java.util.concurrent.Callable;

public class Train implements Callable<Boolean> {

    private String direction;
    private String tunnelName;
    private int trainNumber;

    public Train() {
        direction = TrainPropertyGenerator.generateDirection();
        trainNumber = TrainPropertyGenerator.generateNumber();
        tunnelName = TrainPropertyGenerator.generateTunnel();
    }

    public String getDirection() {
        return direction;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTunnelName() {
        return tunnelName;
    }

    @Override
    public Boolean call(){
        boolean result = false;
        if (TunnelConfigurator.configureTunnel(this)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainNumber == train.trainNumber &&
                Objects.equals(direction, train.direction) &&
                Objects.equals(tunnelName, train.tunnelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, tunnelName, trainNumber);
    }

    @Override
    public String toString() {
        return "Train #" + trainNumber + " " + direction ;
    }
}
