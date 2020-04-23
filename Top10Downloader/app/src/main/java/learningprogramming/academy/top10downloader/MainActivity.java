package learningprogramming.academy.top10downloader;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String FEED_URL = "FEED_URL";
    private static final String LIMIT = "LIMIT";
    private ListView listApps;
    private String feedUrl;
    private int limit;
    private CachedString fullFeedUrl = new CachedString("");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listApps = findViewById(R.id.xmlListView);

        if (savedInstanceState != null) {
            feedUrl = savedInstanceState.getString(FEED_URL);
            limit = savedInstanceState.getInt(LIMIT);
        } else {
            feedUrl = getString(R.string.top10_free_apps_url);
            limit = 10;
        }

        downloadUrl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feeds_menu, menu);
        if (limit == 10) {
            menu.findItem(R.id.mnu10).setChecked(true);
        } else {
            menu.findItem(R.id.mnu25).setChecked(true);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: start");
        outState.putString(FEED_URL, feedUrl);
        outState.putInt(LIMIT, limit);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: end");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mnuFree:
                feedUrl = getString(R.string.top10_free_apps_url);
                break;
            case R.id.mnuPaid:
                feedUrl = getString(R.string.top10_paid_apps_url);
                break;
            case R.id.mnuSongs:
                feedUrl = getString(R.string.top10_songs_url);
                break;
            case R.id.mnu10:
            case R.id.mnu25:
                if (!item.isChecked()) {
                    item.setChecked(true);
                    limit = 35 - limit;
                }
                break;
            case R.id.mnuRefresh:
                fullFeedUrl.setValue("");
                break;
            default:
                break;
        }
        downloadUrl();
        return true;
    }

    private void downloadUrl() {
        fullFeedUrl.setValue(String.format(feedUrl, limit));
        if (!fullFeedUrl.cached()) {
            Log.i(TAG, "downloadUrl: start");
            DownloadDataTask downloadData = new DownloadDataTask(this, listApps);
            downloadData.execute(fullFeedUrl.getValue());
        } else {
            Log.i(TAG, "downloadUrl: cached, so no action");
        }
    }


    /**
     * String that supports .cached() operation which checked if current value is the same with the previous version or not
     */
    private final class CachedString {
        private String value;
        private String cached;
        CachedString(@NonNull String value) {
            if(value==null) throw new IllegalArgumentException("value most not be null");
            this.value = value;
        }

        /**
         * @return if current value is the same as the previous one or not
         */
        boolean cached() {
            return value.equals(cached);
        }

        String getValue() {
            return value;
        }

        void setValue(@NonNull String value) {
            this.cached = this.value;
            this.value = value;
        }

        @Override
        public String toString() {
            return "CachedString{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

}
