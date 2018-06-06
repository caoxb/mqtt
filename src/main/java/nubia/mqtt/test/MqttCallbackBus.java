package nubia.mqtt.test;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

public class MqttCallbackBus implements MqttCallback {
    /**
     * 连接中断
     */
    @Override
    public void connectionLost(Throwable cause) {
        Log.e("MqttManager", "cause : " + cause.toString());
        // 可在此方法内写重连的逻辑
    }

    /**
     * 消息送达
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.d("MqttManager", "topic : " + topic + "\t MqttMessage : " + message.toString());
        //EventBusUtil.sendStickyEvent(new EventModel(10001, topic));
        //EventBusUtil.sendStickyEvent(new EventModel(10010, message));
        //EventBus.getDefault().post(topic);
        //EventBus.getDefault().postSticky(new EventModel(10010, message.toString()));
    }

    /**
     * 交互完成
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.d("MqttManager", "token : " + token.toString());
    }
}
