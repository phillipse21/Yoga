package simple_activity.cookbook.com.musicplaying;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

import static simple_activity.cookbook.com.musicplaying.R.drawable.playbuttonsm;



public class MyActivity extends Activity {
    Intent serviceIntent;
    private Button buttonPlayStop;
    private boolean boolMusicPlaying = false;
    // -- name of the song to be declared
    String strAudioLink = "10.mp3";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
        serviceIntent= new Intent(this,myPlayService.class );
            initViews();
            setListeners();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    e.getClass().getName() + " " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    // --- Set up initial screen ---
    private void initViews() {
        buttonPlayStop = (Button) findViewById(R.id.ButtonPlayStopy);
        //setbackgroundresource will assign the picture of the button in the empty button space
        buttonPlayStop.setBackgroundResource(playbuttonsm);
    }

    // --- Set up listeners ---
    private void setListeners() {
        buttonPlayStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPlayStopClick();
            }
        });

    }
    // --- invoked from ButtonPlayStop listener above ----
    private void buttonPlayStopClick() {
        if (!boolMusicPlaying) { //if the music is not playing, we want it to play
            buttonPlayStop.setBackgroundResource(R.drawable.pausebuttonsm);
            playAudio();
            boolMusicPlaying = true;
        } else {
            if (boolMusicPlaying) { //if the music is playing, we want it to stop
                buttonPlayStop.setBackgroundResource(R.drawable.playbuttonsm);
                stopMyPlayService();
                boolMusicPlaying = false;
            }
        }
    }

    private void stopMyPlayService() {
        try {
            stopService(serviceIntent);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    e.getClass().getName() + " " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        boolMusicPlaying = false;
    }

    private void playAudio(){
        serviceIntent.putExtra("sentAudioLink", strAudioLink); //passing information to the service as to
                                                                //pass what song to play, all using putExtra
        try {
            startService(serviceIntent);
        } catch (Exception e) { //for catching any errors

            e.printStackTrace();
            Toast.makeText( getApplicationContext(),

                    e.getClass().getName() + " " + e.getMessage(),

                    Toast.LENGTH_LONG).show();
        }

    }


}

