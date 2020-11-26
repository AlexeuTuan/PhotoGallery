package foreground;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class MyService extends Worker {
    Context context;

    public static final String ACTION_SHOW_NOTIFICATION = "com.bignerdranch.android.photogallery.SHOW_NOTIFICATION";
    public static final String PERM_PRIVATE = "com.bignerdranch.android.photogallery.PRIVATE";
    public static final String REQUEST_CODE = "REQUEST_CODE";
    public static final String NOTIFICATION = "NOTIFICATION";

   public MyService(
       @NonNull Context context,
       @NonNull WorkerParameters params) {
       super(context, params);
       this.context = context;
   }

   @Override
   public Result doWork() {
       // MyNotification notification = new MyNotification(context);
       // notification.createNotificationBuilder();
       Log.d("Notification","Done");
       showBackgroundNotification(0, new MyNotification(context).createNotification());
     return Result.success();
   }

   public static WorkRequest getWorkRequest() {
       return new OneTimeWorkRequest.Builder(MyService.class)
               .setInitialDelay(1,TimeUnit.SECONDS)
               .build();
   }

   public static WorkRequest getPeriodicWorkRequest() {
       return new PeriodicWorkRequest.Builder(MyService.class, 5, TimeUnit.SECONDS)
               // Constraints
               .build();
   }

   public static Constraints setConstraints() {
       return new Constraints.Builder()
               .setRequiredNetworkType(NetworkType.UNMETERED)
               .setRequiresCharging(true)
               .build();
   }

   public static void submitRequest(WorkRequest uploadWorkRequest, Context context) {
       WorkManager
               .getInstance(context)
               .enqueue(uploadWorkRequest);
   }

    private void showBackgroundNotification(int requestCode, Notification notification) {
        Intent i = new Intent(ACTION_SHOW_NOTIFICATION);
        i.putExtra(REQUEST_CODE, requestCode);
        i.putExtra(NOTIFICATION, notification);
        context.sendOrderedBroadcast(i, PERM_PRIVATE, null, null,
                Activity.RESULT_OK, null, null);
    }
}
