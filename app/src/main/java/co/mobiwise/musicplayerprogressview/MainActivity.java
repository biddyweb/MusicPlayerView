package co.mobiwise.musicplayerprogressview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import co.mobiwise.playerview.MusicPlayerView;

public class MainActivity extends Activity {

    final static String TAG = "MainActivity";

    MusicPlayerView mpv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpv = (MusicPlayerView) findViewById(R.id.mpv);
        final Target target = new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d(TAG, "Cover bitmap loaded!");
                mpv.setCoverBitmap(bitmap);
            }

            @Override public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Log.d(TAG, "Loading cover!");
        Picasso.with(this)
                .load("https://upload.wikimedia.org/wikipedia/en/b/b3/MichaelsNumberOnes.JPG")
                .into(target);

        mpv.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            if (mpv.isRotating()) {
                mpv.stop();
            } else {
                mpv.start();
            }
          }
        });
    }
}
