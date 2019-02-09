package com.kurniawan.jadwaldonor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class JadwalDonorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<JadwalItems>> {

    ListView listView;
    JadwalAdapter adapter;
    ArrayList<JadwalItems> jadwal;
    static final String EXTRA_JADWAL= "EXTRA_JADWAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_donor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Jadwal Donor");

        adapter = new JadwalAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        jadwal = new ArrayList<>();

        Bundle bundle = new Bundle();
        getSupportLoaderManager().initLoader(0, bundle, this);
    }


    @NonNull
    @Override
    public Loader<ArrayList<JadwalItems>> onCreateLoader(int id, @Nullable Bundle args) {
        String jadwalList = "";
        if (args != null) {
            jadwalList = args.getString(EXTRA_JADWAL);
        }
        return new JadwalLoader(this, jadwalList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<JadwalItems>> loader, ArrayList<JadwalItems> data) {
        jadwal.addAll(data);
        adapter.setData(data  );
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<JadwalItems>> loader) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return false;
    }


}
