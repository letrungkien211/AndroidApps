package kienle.learning.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


/**
 * Handles youtube activity
 */
public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YoutubeActivity";

    private boolean autoPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        autoPlay = intent.getBooleanExtra("autoPlay", false);

        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView player = new YouTubePlayerView(this);
        player.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        player.initialize(getString(R.string.youtube_api_key), this);
        layout.addView(player);
        Log.i(TAG, "onCreate: ");
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.i(TAG, "onInitializationSuccess: ");

        Toast.makeText(this, "Initialized Youtube Player successfuly", Toast.LENGTH_LONG).show();
        if (!b) {
            if(autoPlay){
                youTubePlayer.loadVideo(getString(R.string.youtube_video_id));
            }
            else {
                youTubePlayer.cueVideo(getString(R.string.youtube_video_id));
            }
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.i(TAG, "onInitializationFailure: start");
        Toast.makeText(this, "Failed to initialize", Toast.LENGTH_LONG);
    }
}
