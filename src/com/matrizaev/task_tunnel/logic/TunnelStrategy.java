package com.matrizaev.task_tunnel.logic;


import com.matrizaev.task_tunnel.entity.Train;

public interface TunnelStrategy {
    boolean determineDirection(Train train);
}
