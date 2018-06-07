package nubia.mqtt.test;

import android.app.Application;
import android.util.Log;

import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class DemoApplication extends Application {

    private static SSLSocketFactory sslSockFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        sslSockFactory = get2SSLSocketFactory();
    }

    public static SSLSocketFactory getSslSockFactory() {
        return sslSockFactory;
    }
    /**
     * 双向认证
     */
    private SSLSocketFactory get2SSLSocketFactory(){
        try {
            SSLContext context;
            SSLSocketFactory sslSockFactory = null;
            // for server key store
            KeyStore ts = KeyStore.getInstance("BKS");
            ts.load(getResources().openRawResource(R.raw.key), "nubia&123".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init(ts);
            TrustManager[] tm = tmf.getTrustManagers();
            context = SSLContext.getInstance("TLS");
            context.init(null, tm, null);
            // for client key store
            KeyStore kts = KeyStore.getInstance("PKCS12");
            kts.load(getResources().openRawResource(R.raw.keycert), "nubia&123".toCharArray());
            KeyManagerFactory keyManager = KeyManagerFactory.getInstance("X509");
            keyManager.init(kts, "nubia&123".toCharArray());
            //init
            context = SSLContext.getInstance("tlsv1");
            context.init(keyManager.getKeyManagers(), tmf.getTrustManagers(), null);
            sslSockFactory = context.getSocketFactory();
            Log.d("DemoApplication","get2SSLSocketFactory success!");
            return sslSockFactory;

            // SocketFactory factory= SSLSocketFactory.getDefault();
            // Socket socket =factory.createSocket("localhost", 10000);

            //SocketFactory factory = context.getSocketFactory();
            //conOpt.setSocketFactory(factory);
        } catch (Exception e) {
            Log.e("DemoApplication",e.getMessage());
            return null;
        }
    }

}
