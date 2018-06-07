package nubia.mqtt.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //前面也说了，在任何你要接收事件的地方都要先注册
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getEventBus(String message) {
        Toast.makeText(this, "message:"+message, Toast.LENGTH_SHORT).show();
    }

    public void mqtt(View view) {
        String brokerUrl = "ssl://192.168.1.102:8883";
        //String brokerUrl = "tcp://192.168.1.102:1883";
        String userName = "admin";
        String passWord = "admin";
        String clientId = "123456";
        String topic = "out/test";
        MqttManager.getInstance().creatConnect(brokerUrl,userName,passWord,clientId,topic);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MqttManager.release();
        //别忘了在onDestroy里面解绑
        EventBus.getDefault().unregister(this);
    }

    public void publish(View view) {
        String topic = "in/00CE57EC";
        int qos = 1;
        String payload = "Hello World!";
        MqttManager.getInstance().publish(topic,qos,payload);
    }

    public void subscribe(View view) {
        String topic = "out/#";
        int qos = 1;
        MqttManager.getInstance().subscribe(topic,qos);
    }

}
