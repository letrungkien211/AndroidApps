package learningprogramming.academy.top10downloader;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

final class HttpUtil {
    private static final String TAG = "HttpUtil";

    static String downloadXml(String urlPath) {
        StringBuilder xmlResult = new StringBuilder();
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int response = connection.getResponseCode();
            Log.i(TAG, "downloadXml: the response code was " + response);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                int charsRead;
                char[] inputBuffer = new char[500];
                while ((charsRead = reader.read(inputBuffer))>=0) {
                    if(charsRead>0)
                        xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
                }
            }
            return xmlResult.toString();
        } catch (MalformedURLException e) {
            Log.e(TAG, "downloadXml: invalid url ", e);
        } catch (IOException e) {
            Log.e(TAG, "downloadXml: ", e);
        }
        catch (SecurityException e){
            Log.e(TAG, "downloadXml: ", e);
        }

        return null;
    }

}
