package kienle.learning.youtubeplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);

        Button btnPlayVideo = findViewById(R.id.btnPlayVideo);
        Button btnPlayList = findViewById(R.id.btnPlayList);

        btnPlayVideo.setOnClickListener(this);
        btnPlayList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id){
            case R.id.btnPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, getString(R.string.youtube_api_key), getString(R.string.youtube_video_id));
                break;
            case R.id.btnPlayList:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, getString(R.string.youtube_api_key), getString(R.string.youtube_playlist));
                break;
            default:
                break;
        }

        if(intent!=null){
            startActivity(intent);
        }
    }
}
