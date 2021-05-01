package com.example.user.justmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import static android.net.wifi.p2p.WifiP2pManager.EXTRA_WIFI_STATE;

public class WiFiDirectBroadcastReciever extends BroadcastReceiver {
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChanel;
    private MainActivity mActivity;

    public WiFiDirectBroadcastReciever(WifiP2pManager mManager,WifiP2pManager.Channel mChanel, MainActivity mActivity){
        this.mManager = mManager;
        this.mChanel = mChanel;
        this.mActivity = mActivity;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)){
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
            if(state==WifiP2pManager.WIFI_P2P_STATE_ENABLED){
                Toast.makeText(context, "Wifi is On",Toast.LENGTH_SHORT).show();
            }else{
               Toast.makeText(context, "Wifi is Off",Toast.LENGTH_SHORT).show();
            }
        }else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)){
            if(mManager!=null){
                mManager.requestPeers(mChanel,mActivity.peerListListener);
            }
        }else if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)){

        }else if(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)){

        }
    }
}
