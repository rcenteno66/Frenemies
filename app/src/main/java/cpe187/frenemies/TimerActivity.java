package cpe187.frenemies;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


//This timer is for after the killer has killed a citizen//

public class TimerActivity extends AppCompatActivity implements View.OnClickListener
{
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Ringtone r;
    private Button startB;
    public TextView text;
    public long startTime = 5000;
    private final long interval = 1 * 1000;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        text.setText(text.getText() + String.valueOf(startTime / 1000));
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    public void onClick(View v)
    {
        if (!timerHasStarted)
        {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("STOP");
            r.stop();
        }
        else
        {
            countDownTimer.cancel();
            timerHasStarted = false;
            r.stop();
            startB.setText("RESTART");
        }
    }

    public class MyCountDownTimer extends CountDownTimer
    {
        MyCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }

        @Override
        public void onFinish()
        {
            text.setText("Time's up!");
            r.play();
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            text.setText("" + millisUntilFinished / 1000);
        }
    }

    public TimerActivity() {
        super();
    }
}
