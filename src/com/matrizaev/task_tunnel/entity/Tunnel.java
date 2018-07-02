package com.matrizaev.task_tunnel.entity;



public class Tunnel {

    private String name;
    private int totalCapacity;
    private int directionCapacity;

    public String getName() {
        return name;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getDirectionCapacity() {
        return directionCapacity;
    }

    public Tunnel(String name, int totalCapacity, int directionCapacity) {
        this.name = name;
        this.totalCapacity = totalCapacity;
        this.directionCapacity = directionCapacity;
    }
}
