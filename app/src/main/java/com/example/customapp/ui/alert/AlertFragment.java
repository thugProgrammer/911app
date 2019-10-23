package com.example.customapp.ui.alert;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.customapp.R;
import com.example.customapp.SignInActivity;
import com.example.customapp.ui.shareModel.ShareViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;
import java.util.zip.Inflater;

public class AlertFragment extends Fragment implements OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private MapView mapView;
    private ShareViewModel shareViewModel;
    protected GoogleMap map;
    private boolean mLocationPermissionGranted = false;
//    private Button btnLocPermission;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        shareViewModel = ViewModelProviders.of(getActivity()).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alert, container, false);
        mapView = (MapView) root.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
//        final TextView textView = root.findViewById(R.id.text_testing);
//        shareViewModel.getName().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

//    public void onStart() {
//        super.onStart();
//        if (mapView != null){
//            mapView.onStart();
//            getLocationPermission();
//        }
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        btnLocPermission = getActivity().findViewById(R.id.button_permission);
//        btnLocPermission.setOnClickListener(btnLocListener);
        getLocationPermission();
    }

    public void onResume() {
        super.onResume();
        if (mapView != null){
            mapView.onResume();
         }

    }

    public void onPause() {
        super.onPause();
        if (mapView != null){
            mapView.onResume();
        }
    }

    public void onStop() {
        super.onStop();
        if (mapView != null){
            mapView.onStop();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mapView != null){
            mapView.onDestroy();
        }
    }

    public final void onSaveInstanceState (Bundle outState){

    }

    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null){
            mapView.onLowMemory();

        }
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//
//        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            map.setMyLocationEnabled(true);
//        } else {
//            // Show rationale and request permission.
//        }
    }

    public void updateUI(){
        RelativeLayout layoutLocation = getActivity().findViewById(R.id.layout_location);
        layoutLocation.setVisibility(View.VISIBLE);
    }

    public void getLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            updateUI();
        } else{
            Log.i("HAPPEN", "yes");
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
          }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.i("CALLED", "yes");
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                Log.i("HAPPEN", "yes");
                updateUI();
            }

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Location must be activated", Toast.LENGTH_SHORT).show();
        }

    }




}