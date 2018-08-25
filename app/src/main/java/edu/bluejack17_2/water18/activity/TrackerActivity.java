package edu.bluejack17_2.water18.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import edu.bluejack17_2.water18.R;
import edu.bluejack17_2.water18.service.TrackerService;

public class TrackerActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "Please Enable Location Service", Toast.LENGTH_SHORT).show();
            finish();
        }

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permission == PackageManager.PERMISSION_GRANTED){
            startTrackerService();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST
            );
        }
    }

    private void startTrackerService() {
        startService(new Intent(this, TrackerService.class));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSIONS_REQUEST && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startTrackerService();
        }
        else{
            finish();
        }
    }
}
