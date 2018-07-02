package com.matrizaev.task_tunnel.logic;


import com.matrizaev.task_tunnel.entity.Train;

public class TunnelLogic {
    private TunnelStrategy strategy;

    private TunnelLogic(){}

    private static class TunnelLogicHolder {
        private static final TunnelLogic INSTANCE = new TunnelLogic();
    }

    public static TunnelLogic getInstance() {return TunnelLogicHolder.INSTANCE;}

    boolean selectTunnel(Train train) {
        boolean isSelected = false;
        if (strategy.determineDirection(train)) {
            isSelected = true;
        }
        return isSelected;
    }

    void setTunnelStrategy(TunnelStrategy strategy){
        this.strategy = strategy;
    }
}


