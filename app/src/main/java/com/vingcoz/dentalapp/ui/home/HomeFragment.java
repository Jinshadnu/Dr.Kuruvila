package com.vingcoz.dentalapp.ui.home;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.vingcoz.dentalapp.R;
import com.vingcoz.dentalapp.adapters.DoctorHorizontalRecycle;
import com.vingcoz.dentalapp.adapters.SliderAdapterExample;
import com.vingcoz.dentalapp.models.DoctorItem;
import com.vingcoz.dentalapp.models.DoctorResponse;
import com.vingcoz.dentalapp.utilities.ApiUtils;
import com.vingcoz.dentalapp.utilities.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    SliderView sliderView;

    ProgressDialog progressDialog;
    RecyclerView mRecycleView;
    DoctorHorizontalRecycle mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        sliderView = root.findViewById(R.id.imageSlider);
        mRecycleView = root.findViewById(R.id.recyclerview);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        LoadDoctors();

        final SliderAdapterExample adapter = new SliderAdapterExample(getContext());
        adapter.setCount(5);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(position -> sliderView.setCurrentPagePosition(position));
        // homeViewModel.getText().observe(this, s -> textView.setText(s));

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
        mAdapter = new DoctorHorizontalRecycle(getActivity(), mList);
        mRecycleView.setAdapter(mAdapter);
    }
}