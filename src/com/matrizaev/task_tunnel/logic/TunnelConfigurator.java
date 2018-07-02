package com.matrizaev.task_tunnel.logic;

import com.matrizaev.task_tunnel.entity.Train;


public class TunnelConfigurator {

    private static TunnelLogic logic = TunnelLogic.getInstance();
    private static NorthTunnel northTunnel = new NorthTunnel();
    private static SouthTunnel southTunnel = new SouthTunnel();

    private TunnelConfigurator() {}

    public static boolean configureTunnel(Train train) {
        boolean isConfigured = false;
        if (train.getTunnelName().equals("NORTH")) {
            logic.setTunnelStrategy(northTunnel);
        } else {
            logic.setTunnelStrategy(southTunnel);
        }
        if (logic.selectTunnel(train)) {
            isConfigured = true;
        }
        return isConfigured;
    }
}
