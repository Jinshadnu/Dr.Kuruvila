package com.vingcoz.dentalapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vingcoz.dentalapp.BookingForm;
import com.vingcoz.dentalapp.R;
import com.vingcoz.dentalapp.models.DoctorItem;

import java.util.List;


public class DoctorHorizontalRecycle extends RecyclerView.Adapter<DoctorHorizontalRecycle.VideoViewHolder> {

    Context mCtx;
    List<DoctorItem> MaterialList;

    public DoctorHorizontalRecycle(Context mCtx, List<DoctorItem> MaterialList) {
        this.mCtx = mCtx;
        this.MaterialList = MaterialList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.row_doctor_horizontal, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        DoctorItem VideoSingle = MaterialList.get(position);

        holder.txtName.setText(VideoSingle.getDName());
        holder.txtSpeceial.setText(VideoSingle.getDSpecialization());
        holder.txtTime.setText(VideoSingle.getDTime());

        holder.button_booking.setOnClickListener(v -> {
            Intent intent=new Intent(mCtx.getApplicationContext(), BookingForm.class);
            intent.putExtra("doctor_name",VideoSingle.getDName());
            intent.putExtra("doctor_id",VideoSingle.getDId());
            mCtx.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return MaterialList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtSpeceial, txtTime;
        CardView CardSection;
        public Button button_booking;


        public VideoViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.idname);
            txtSpeceial = itemView.findViewById(R.id.idSpecialization);
            txtTime = itemView.findViewById(R.id.idTime);
            button_booking=itemView.findViewById(R.id.button_booking);
        }
    }


}


