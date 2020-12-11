package com.vingcoz.dentalapp.ui.doctors;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vingcoz.dentalapp.R;
import com.vingcoz.dentalapp.adapters.DoctorRecycle;
import com.vingcoz.dentalapp.models.DoctorItem;
import com.vingcoz.dentalapp.models.DoctorResponse;
import com.vingcoz.dentalapp.utilities.ApiUtils;
import com.vingcoz.dentalapp.utilities.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorFragment extends Fragment {


    ProgressDialog progressDialog;
    RecyclerView mRecycleView;
    DoctorRecycle mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_doctors, container, false);
        mRecycleView = root.findViewById(R.id.recyclerview);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        LoadDoctors();

        return root;
    }

    private void LoadDoctors() {


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Doctors..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiUtils service = RetrofitClient.getClient().create(ApiUtils.class);

        Call<DoctorResponse> call = service.GetDoctors();
        call.enqueue(new Callback<DoctorResponse>() {

            @Override
            public void onResponse(@NonNull Call<DoctorResponse> call, @NonNull Response<DoctorResponse> response) {
                assert response.body() != null;

                if (!response.body().isError()) {
                    loadDataList(response.body().getDoctor());
                } else {
                    Toast.makeText((getContext()), "Error : " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DoctorResponse> call, @NonNull Throwable throwable) {
                Toast.makeText(getContext(), "Error : " + "Error : " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.dismiss();
    }

    private void loadDataList(List<DoctorItem> mList) {
        mAdapter = new DoctorRecycle(getActivity(), mList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
    }
}