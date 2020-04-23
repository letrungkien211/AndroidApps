package learningprogramming.academy.top10downloader;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

final class DownloadDataTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "DownloadData";
    private final Context context;
    private final ListView listView;

    DownloadDataTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i(TAG, "onPostExecute: start");
        super.onPostExecute(s);
        ParseApplications parseApplications = new ParseApplications();

        parseApplications.parse(s);
        listView.setAdapter(new FeedAdapter<FeedEntry>(context, R.layout.list_record, parseApplications.getApplications()));

        Log.i(TAG, "onPostExecute: end");
    }

    @Override
    protected String doInBackground(String... strings) {
        String rssFeed = HttpUtil.downloadXml(strings[0]);
        if (rssFeed == null) {
            Log.e(TAG, "doInBackground: Error downloading");
        }
        return rssFeed;
    }
}
