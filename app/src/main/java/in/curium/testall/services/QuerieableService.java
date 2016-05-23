package in.curium.testall.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import in.curium.testall.R;

public class QuerieableService extends Service {
  private final String TAG = QuerieableService.class.getCanonicalName();
  private final IBinder mBinder = new LocalBinder();
  private final int NOTIFICATION = R.string.querieable_service_started_message;
  private NotificationManager mNotificationManager;
  public static final int MSG_UNREGISTER_CLIENT = 1;
  public static final int MSG_SET_VALUE = 2;

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return mBinder;
  }

  private void showNotification() {
    // In this sample, we'll use the same text for the ticker and the expanded notification
    CharSequence text = getText(R.string.querieable_service_started_message);

    // The PendingIntent to launch our activity if the user selects this notification

    // Set the info for the views that show in the notification panel.
    Notification notification =
        new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher)  // the status icon
            .setTicker(text)  // the status text
            .setWhen(System.currentTimeMillis())  // the time stamp
            .setContentTitle(getText(R.string.querieable_service_started_message))
            // the label of the entry
            .setContentText(text)  // the contents of the entry
            .build();

    // Send the notification.
    mNotificationManager.notify(NOTIFICATION, notification);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    showNotification();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    debugLog("received start id = " + startId + ", flags = " + flags + ", intent = " + intent);
    return START_NOT_STICKY;
  }

  private final void debugLog(String message) {
    Log.d(TAG, message);
  }

  @Override
  public void onDestroy() {
    mNotificationManager.cancel(NOTIFICATION);
    // Tell the user we stopped.
    Toast.makeText(this, R.string.querieable_service_stopped_message, Toast.LENGTH_SHORT).show();
    super.onDestroy();
  }

  public class LocalBinder extends Binder {
    public QuerieableService getService() {
      return QuerieableService.this;
    }
  }

  public void send(Message msg) {

  }
}
