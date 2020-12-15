package com.vingcoz.dentalapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vingcoz.dentalapp.models.BookingResponse;
import com.vingcoz.dentalapp.models.DoctorResponse;
import com.vingcoz.dentalapp.models.OtpResponse;
import com.vingcoz.dentalapp.models.OtpVerificationResponse;
import com.vingcoz.dentalapp.models.PurposeResponse;
import com.vingcoz.dentalapp.utilities.ApiUtils;
import com.vingcoz.dentalapp.utilities.RetrofitClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingForm extends AppCompatActivity {


    TextInputEditText txtPatientID;
    TextInputEditText txtPatientName;
    TextInputEditText txtMobile;
    TextInputEditText txtAddress;
    TextInputEditText txtEmail;
    TextInputEditText txtOtp;
    MaterialButton btnSubmitBooking;

    TextInputLayout txtOTPLayout;
    TextInputLayout layoutName;
    TextInputLayout layoutEmail;
    TextInputLayout layoutAddress;
    TextInputLayout layoutCardNo;
    TextInputLayout layoutBookingDate;
    TextInputLayout layoutBookingTime;

    public String doctor_name;

    public int doctor_id;
    TextView txtViewDoc;
    TextView txtViewPurpose;

    Spinner spDoctor;
    Spinner spPurpose;

    TextInputEditText tDate, tTime;

    int[] arrDoctorID;
    int[] arrPurposeID;
    int DoctorID, PurposeID;
    ProgressDialog progressDialog;

    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMinute;
    String strDate = "";
    String strTime = "";

    long OtpId = 0;

    boolean isFormCompleted = false;
    boolean IsOtpVerified = false;

    Calendar mCalendar = Calendar.getInstance();
    Date date = mCalendar.getTime();
    DateFormat ShowDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat SaveDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat TimeFormat = new SimpleDateFormat("hh:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);
     /*   Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        progressDialog = new ProgressDialog(BookingForm.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);

        doctor_name=getIntent().getStringExtra("doctor_name");
        doctor_id=getIntent().getIntExtra("doctor_id",0);
        txtPatientID = findViewById(R.id.txtPatientCode);
        txtPatientName = findViewById(R.id.txtname);
        txtMobile = findViewById(R.id.txtMobile);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtOtp = findViewById(R.id.txtOtp);

        //txtViewDoc = findViewById(R.id.txtViewDoc);
        txtViewPurpose = findViewById(R.id.txtViewPurpose);
        txtOTPLayout = findViewById(R.id.txtOTPLayout);

        //spDoctor = findViewById(R.id.spDoctor);
        spPurpose = findViewById(R.id.spPurpose);

        tDate = findViewById(R.id.txtBDate);
        tTime = findViewById(R.id.txtBTime);

        layoutName = findViewById(R.id.layoutName);
        layoutEmail = findViewById(R.id.layoutEmail);
        layoutAddress = findViewById(R.id.layoutAddress);
        layoutCardNo = findViewById(R.id.layoutCardNo);
        layoutBookingDate = findViewById(R.id.layoutBookingDate);
        layoutBookingTime = findViewById(R.id.layoutBookingTime);

        String strDate = ShowDateFormat.format(date);
        tDate.setText(strDate);
        tTime.setText(TimeFormat.format(date));
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        tDate.setOnClickListener(v -> {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(BookingForm.this,
                    (view, year, monthOfYear, dayOfMonth) ->
                            tDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay
            );
            datePickerDialog.show();
        });

        tTime.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            int CalendarHour = calendar.get(Calendar.HOUR_OF_DAY);
            int CalendarMinute = calendar.get(Calendar.MINUTE);
            final String[] format = new String[1];

            TimePickerDialog timepickerdialog = new TimePickerDialog(BookingForm.this,
                    (view, hourOfDay, minute) -> {

                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            format[0] = "AM";
                        } else if (hourOfDay == 12) {
                            format[0] = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            format[0] = "PM";
                        } else {
                            format[0] = "AM";
                        }
                        tTime.setText(hourOfDay + ":" + minute + " " + format[0]);
                    }, CalendarHour, CalendarMinute, false);
            timepickerdialog.show();
        });

//        spDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int pos, long id) {
//                int intArrPosition = spDoctor.getSelectedItemPosition();
//                DoctorID = arrDoctorID[intArrPosition];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//            }
//        });
        spPurpose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                int intArrPosition = spPurpose.getSelectedItemPosition();
                PurposeID = arrPurposeID[intArrPosition];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        btnSubmitBooking = findViewById(R.id.fab);
        btnSubmitBooking.setOnClickListener(view -> {

            if (Validate()) {

                if (!isFormCompleted) {
                    SendOtp();
                } else {
                    VerifyOTP();
                }
            }
        });
        LoadDoctors();
    }

    boolean Validate() {
        boolean ReturnValue = true;

        if (txtPatientName.length() < 3) {
            txtPatientName.setError("Invalid name");
            ReturnValue = false;
        }
        if (txtMobile.length() != 10) {
            txtMobile.setError("Invalid mobile number");
            ReturnValue = false;
        }
        if (txtAddress.length() < 5) {
            txtAddress.setError("Invalid address");
            ReturnValue = false;
        }
        return ReturnValue;
    }

    private void LoadDoctors() {

        progressDialog.setMessage("Loading Doctors..");
        progressDialog.show();

        ApiUtils service = RetrofitClient.getClient().create(ApiUtils.class);

        Call<DoctorResponse> call = service.GetDoctors();
        call.enqueue(new Callback<DoctorResponse>() {

            @Override
            public void onResponse(Call<DoctorResponse> call, Response<DoctorResponse> response) {
                assert response.body() != null;

                if (!response.body().isError()) {

                    String[] items = new String[response.body().getDoctor().size()];
                    arrDoctorID = new int[response.body().getDoctor().size()];

                    for (int i = 0; i < response.body().getDoctor().size(); i++) {
                        arrDoctorID[i] = response.body().getDoctor().get(i).getDId();
                        items[i] = String.valueOf(response.body().getDoctor().get(i).getDName());
                    }

                    ArrayAdapter<String> adapter;
                    adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
                   // spDoctor.setAdapter(adapter);

                } else {
                    Toast.makeText(BookingForm.this, "Error : " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                LoadPurpose();
            }

            @Override
            public void onFailure(Call<DoctorResponse> call, Throwable throwable) {
                Toast.makeText(BookingForm.this, "Error : " + "Error : " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void LoadPurpose() {

        progressDialog.setMessage("Loading Purposes..");
        progressDialog.show();

        ApiUtils service = RetrofitClient.getClient().create(ApiUtils.class);

        Call<PurposeResponse> call = service.GetPurpose();
        call.enqueue(new Callback<PurposeResponse>() {

            @Override
            public void onResponse(Call<PurposeResponse> call, Response<PurposeResponse> response) {
                assert response.body() != null;

                if (!response.body().isError()) {

                    String[] items = new String[response.body().getPurpose().size()];
                    arrPurposeID = new int[response.body().getPurpose().size()];

                    for (int i = 0; i < response.body().getPurpose().size(); i++) {
                        arrPurposeID[i] = response.body().getPurpose().get(i).getPuId();
                        items[i] = String.valueOf(response.body().getPurpose().get(i).getPuName());
                    }

                    ArrayAdapter<String> adapter;
                    adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
                    spPurpose.setAdapter(adapter);

                } else {
                    Toast.makeText(BookingForm.this, "Error : " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PurposeResponse> call, Throwable throwable) {
                Toast.makeText(BookingForm.this, "Error : " + "Error : " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void SubmitBooking() {
        progressDialog.setMessage("Submitting booking details.Please wait..");
        progressDialog.show();

        ApiUtils apiService = RetrofitClient.getClient().create(ApiUtils.class);

        Call<BookingResponse> call;
        call = apiService.RequestSignUp(
                Objects.requireNonNull(txtPatientID.getText()).toString(),
                Objects.requireNonNull(txtPatientName.getText()).toString(),
                Objects.requireNonNull(txtMobile.getText()).toString(),
                Objects.requireNonNull(txtAddress.getText()).toString(),
                Objects.requireNonNull(txtEmail.getText()).toString(),
                doctor_id, PurposeID,
                mYear + "-" + mMonth + "-" + mDay,
                Objects.requireNonNull(tTime.getText()).toString(),
                " "
        );

        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookingResponse> call, @NonNull Response<BookingResponse> response) {

                assert response.body() != null;

                if (!response.body().isError()) {
                    progressDialog.dismiss();
                    Intent OpenOk = new Intent(BookingForm.this, BookingOk.class);
                    String BID = String.valueOf(response.body().getBookingId());
                    OpenOk.putExtra("BookingId", BID);
                    startActivity(OpenOk);
                    finish();
                } else {
                    Toast.makeText(BookingForm.this, "Something went wrong , Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BookingResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BookingForm.this, "Something went wrong , Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void SendOtp() {
        progressDialog.setMessage("Sending OTP..Please wait for OTP");
        progressDialog.show();

        ApiUtils apiService = RetrofitClient.getClient().create(ApiUtils.class);

        Call<OtpResponse> call;
        call = apiService.SendOTP(txtMobile.getText().toString()
        );

        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(@NonNull Call<OtpResponse> call, @NonNull Response<OtpResponse> response) {

                assert response.body() != null;

                if (!response.body().isError()) {
                    progressDialog.dismiss();
                    OtpId = response.body().getOtpid();

                    //txtViewDoc.setVisibility(View.GONE);
                    txtViewPurpose.setVisibility(View.GONE);
                    spDoctor.setVisibility(View.GONE);
                    spPurpose.setVisibility(View.GONE);

                    tDate.setVisibility(View.GONE);
                    tTime.setVisibility(View.GONE);
                    txtPatientID.setVisibility(View.GONE);
                    txtPatientName.setVisibility(View.GONE);
                    txtMobile.setFocusable(false);
                    txtAddress.setVisibility(View.GONE);
                    txtEmail.setVisibility(View.GONE);


                    layoutName.setVisibility(View.GONE);
                    layoutEmail.setVisibility(View.GONE);
                    layoutAddress.setVisibility(View.GONE);
                    layoutCardNo.setVisibility(View.GONE);
                    layoutBookingDate.setVisibility(View.GONE);
                    layoutBookingTime.setVisibility(View.GONE);

                    isFormCompleted = true;
                    txtOTPLayout.setVisibility(View.VISIBLE);
                    txtOtp.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.showSoftInput(txtOtp, InputMethodManager.SHOW_IMPLICIT);
                    Toast.makeText(BookingForm.this, "Please enter otp", Toast.LENGTH_SHORT).show();
                    btnSubmitBooking.setText("Verify and Submit");
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(BookingForm.this, "Invalid Mobile number or try later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<OtpResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BookingForm.this, "Invalid Mobile number or try later", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void VerifyOTP() {
        progressDialog.setMessage("Verifying Otp please wait");
        progressDialog.show();

        ApiUtils apiService = RetrofitClient.getClient().create(ApiUtils.class);

        Call<OtpVerificationResponse> call;
        call = apiService.VerifyOtp(OtpId, txtOtp.getText().toString(), txtMobile.getText().toString());

        call.enqueue(new Callback<OtpVerificationResponse>() {
            @Override
            public void onResponse(@NonNull Call<OtpVerificationResponse> call, @NonNull Response<OtpVerificationResponse> response) {

                assert response.body() != null;

                if (!response.body().isError()) {
                    progressDialog.dismiss();
                    SubmitBooking();
                } else {
                    progressDialog.dismiss();
                    txtOtp.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.showSoftInput(txtOtp, InputMethodManager.SHOW_IMPLICIT);
                    txtOtp.setText("Invalid otp or try again");
                    Toast.makeText(BookingForm.this, "Invalid otp or try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<OtpVerificationResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BookingForm.this, "Invalid otp or try later", Toast.LENGTH_SHORT).show();
            }
        });

    }
}