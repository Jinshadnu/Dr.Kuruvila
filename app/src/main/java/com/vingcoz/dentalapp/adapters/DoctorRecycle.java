package com.vingcoz.dentalapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vingcoz.dentalapp.R;
import com.vingcoz.dentalapp.models.DoctorItem;

import java.util.List;


public class DoctorRecycle extends RecyclerView.Adapter<DoctorRecycle.VideoViewHolder> {

    Context mCtx;
    List<DoctorItem> MaterialList;

    public DoctorRecycle(Context mCtx, List<DoctorItem> MaterialList) {
        this.mCtx = mCtx;
        this.MaterialList = MaterialList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.row_recycle_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        DoctorItem VideoSingle = MaterialList.get(position);

        holder.txtName.setText(VideoSingle.getDName());
        holder.txtSpeceial.setText(VideoSingle.getDSpecialization());
        holder.txtTime.setText(VideoSingle.getDTime());
    }

    @Override
    public int getItemCount() {
        return MaterialList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtSpeceial, txtTime;
        CardView CardSection;


        public VideoViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.idname);
            txtSpeceial = itemView.findViewById(R.id.idSpecialization);
            txtTime = itemView.findViewById(R.id.idTime);
        }
    }


}

