package com.kurniawan.jadwaldonor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class JadwalLoader extends AsyncTaskLoader<ArrayList<JadwalItems>> {

    private String mJadwalList;
    private ArrayList<JadwalItems> mData;
    private boolean hasResult = false;


    public JadwalLoader(final Context context, String jadwalList) {
        super(context);
        onContentChanged();
        this.mJadwalList = jadwalList;
    }

    @Nullable
    @Override
    public ArrayList<JadwalItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<JadwalItems> jadwalArrayList = new ArrayList<>();
        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=WFYgWlk9iH_zosKi9CRKeMw96pSy0TtIxBFDCFVcXxkqK6xK9msptple--OLJjLwegSO_BdPhn8VCc0A6Qhxo-HZ8Nhx4VQNOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuB6lHT6qnqYcmFWggwoSVQQXTsQ1HqKa1CgDXQROm1OeNR5ibYVAaRxAeOtzLmbRZcVjrce7Uveb8iU1s-L39A_CLDTUaq6azCNVhRMhi1rsPEMUK-CH6pys1RvMr_dgaGkoVsMt9XllB7kFByHUCzY&lib=M-tpZm-Fm1QX9Yr80nZn_p-WXe3zpGnIr";
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }



            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonJadwal = jsonArray.getJSONObject(i);
                        JadwalItems jadwal = new JadwalItems(jsonJadwal);
                        jadwalArrayList.add(jadwal);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
        return jadwalArrayList;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(@Nullable ArrayList<JadwalItems> data) {
        mData = data;
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasResult) {
            onReleaseResources(mData);
            mData = null;
            hasResult = false;
        }
    }

    protected void onReleaseResources(ArrayList<JadwalItems> mData) {

    }
}
