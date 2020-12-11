package com.vingcoz.dentalapp.ui.contactus;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.vingcoz.dentalapp.R;

public class ContactFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        final MaterialButton btnOpenMap = root.findViewById(R.id.btnGotoMap);
        final MaterialButton btnCall = root.findViewById(R.id.btnCall);
        btnOpenMap.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/fSi985xb7m63GmCw5"));
            startActivity(browserIntent);
        });
        btnCall.setOnClickListener(v -> {


            Dexter.withActivity(getActivity())
                    .withPermission(Manifest.permission.CALL_PHONE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "04885212242"));
                            startActivity(intent);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            // check for permanent denial of permission
                            if (response.isPermanentlyDenied()) {
                                // navigate user to app settings
                                Toast.makeText(getContext(), "We need CALL_PHONE permission to call from your phone.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();

        });

        return root;
    }
}