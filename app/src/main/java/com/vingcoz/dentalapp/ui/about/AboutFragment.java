package com.vingcoz.dentalapp.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.vingcoz.dentalapp.R;

public class AboutFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about, container, false);


        MaterialButton btnVersion = root.findViewById(R.id.btnVersion);
        MaterialButton btnPrivacy = root.findViewById(R.id.btnPrivacy);
        MaterialButton btnRate = root.findViewById(R.id.btnRate);
        MaterialButton btnCompany = root.findViewById(R.id.btnCompany);
        MaterialButton btnWeb = root.findViewById(R.id.btnWeb);

        MaterialButton btnVisitWebSite = root.findViewById(R.id.btnVisitWebSite);
        MaterialButton btnReview = root.findViewById(R.id.btnReview);

        btnVisitWebSite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://business.google.com/website/dr-kuruvilla-memorial-dental-clinic"));
            startActivity(browserIntent);
        });

        btnReview.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.google.com/local/writereview?placeid=ChIJJ88A1UaVpzsR8cmA1HJuiZI"));
            startActivity(browserIntent);
        });

        btnVersion.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vingcoz.laundryapp"));
            startActivity(browserIntent);
        });

        btnPrivacy.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://vingcoz.com/mobile_app/privacypolicy/kuruvilla_dental_clinic_privacy_policy.html"));
            startActivity(browserIntent);
        });

        btnRate.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vingcoz.dentalapp"));
            startActivity(browserIntent);
        });

        btnCompany.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vingcoz.com/"));
            startActivity(browserIntent);
        });

        btnWeb.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vingcoz.com/"));
            startActivity(browserIntent);
        });

        return root;
    }
}