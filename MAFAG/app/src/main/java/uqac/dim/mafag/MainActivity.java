package uqac.dim.mafag;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    private TextView selectedNameTextView;
    private TextView selectedUrlTextView;
    private static final int REQUEST_CODE_MAFAG = 1;
    private static final String DEFAULT_NOM = "Microsoft";
    private static final String DEFAULT_URL = "https://www.microsoft.com/";
    private static final String CHANNEL_ID = "my_channel_id";
    private static final String STATE_NOM = "nom";
    private static final String STATE_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        selectedNameTextView = findViewById(R.id.mafag_name);
        selectedUrlTextView = findViewById(R.id.mafag_link);
        Button myButton = findViewById(R.id.button_choice);

        if (savedInstanceState != null) {
            selectedNameTextView.setText(savedInstanceState.getString(STATE_NOM));
            selectedUrlTextView.setText(savedInstanceState.getString(STATE_URL));
        } else {
            selectedNameTextView.setText(DEFAULT_NOM);
            selectedUrlTextView.setText(DEFAULT_URL);
        }
        if (myButton != null) {
            myButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, MafagActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MAFAG);
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_NOM, selectedNameTextView.getText().toString());
        outState.putString(STATE_URL, selectedUrlTextView.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MAFAG && resultCode == RESULT_OK && data != null) {
            String stringValue = data.getStringExtra("selectedName");
            String urlString = data.getStringExtra("selectedUrl");

            // Now you have the values, you can use them as needed
            selectedNameTextView.setText(stringValue);
            selectedUrlTextView.setText(urlString);
        }
    }

    public void openOnNavigator(View view) {
        Uri webpage = Uri.parse(selectedUrlTextView.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public void sendNotification(View view) {
        String stringValue = selectedNameTextView.getText().toString();
        String urlString = selectedUrlTextView.getText().toString();
        Log.i("Notification", "Sending notification with " + stringValue + " and " + urlString);
        // Create an Intent with the specified URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(stringValue)
                .setContentText(stringValue)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Your Channel Name";
            String description = "Your Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        } else {
            Log.i("Notification", "Notification channel not created");
        }
    }
}