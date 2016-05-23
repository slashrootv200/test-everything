package in.curium.testall.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import in.curium.testall.R;
import in.curium.testall.services.QuerieableService;

public class QueriableServiceConnectedActivity extends AppCompatActivity {
  private final String TAG = QueriableServiceConnectedActivity.class.getCanonicalName();
  private QuerieableService mQuerieableService;
  private boolean mIsBound = false;
  private Messenger mMessenger = new Messenger(new IncomingHandler());
  private TextView mCallbackTv;

  private class IncomingHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case QuerieableService.MSG_SET_VALUE:
          mCallbackTv.setText("Received from service: " + msg.arg1);
          break;
        default:
          super.handleMessage(msg);
          break;
      }
    }
  }

  private ServiceConnection mConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      debugLog("entering onServiceConnected with name = " + name + ", service = " + service);
      mQuerieableService = ((QuerieableService.LocalBinder) service).getService();
      mCallbackTv.setText("Attached.");
      debugLog("exiting onServiceConnected");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      debugLog("entering onServiceDisconnected with name = " + name);
      mCallbackTv.setText("Disconnected.");
      debugLog("exiting onServiceDisconnected");
    }
  };

  @Override
  protected void onCreate(
      @Nullable
      Bundle savedInstanceState) {
    debugLog("entering onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_queriable);
    initViews();
    debugLog("exiting onCreate");
  }

  public void initViews() {
    debugLog("entering initViews");
    debugLog("exiting initViews");
  }

  public void connectToService(View v) {
    debugLog("entering connectToService");
    mCallbackTv = (TextView) findViewById(R.id.callback_tv);
    bindService(new Intent(this, QuerieableService.class), mConnection, Context.BIND_AUTO_CREATE);
    mIsBound = true;
    mCallbackTv.setText("Binding.");
    debugLog("exiting connectToService");
  }

  public void disconnectFromService(View v) {
    debugLog("entering disconnectFromService");
    if (mIsBound) {
      if (mQuerieableService != null) {
        //try {
          Message msg = Message.obtain(null, QuerieableService.MSG_UNREGISTER_CLIENT);
          msg.replyTo = mMessenger;
          mQuerieableService.send(msg);
        //} catch (RemoteException e) {
        //
        //}
      }
      unbindService(mConnection);
      mIsBound = false;
      mCallbackTv.setText("Unbinding.");
    }
    debugLog("exiting disconnectFromService");
  }

  private final void debugLog(String message) {
    Log.d(TAG, message);
  }
}
