package com.vingcoz.dentalapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingOk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_ok);

        TextView MMessage = findViewById(R.id.txtMessage);
        String MessageID = "We have received your booking request. We will contact you after when it confirms \n Your booking Id is : ";

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                MessageID += null;
            } else {
                MessageID += extras.getString("BookingId");
            }
        } else {
            MessageID += (String) savedInstanceState.getSerializable("BookingId");
        }
        MMessage.setText(MessageID);
    }
}