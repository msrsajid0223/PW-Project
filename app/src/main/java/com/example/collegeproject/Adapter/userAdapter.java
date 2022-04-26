package com.example.collegeproject.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collegeproject.Model.userModel;
import com.example.collegeproject.R;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.userHolder> {

    FragmentActivity requireActivity;
    ArrayList<userModel> allUserList;

    public userAdapter(FragmentActivity requireActivity, ArrayList<userModel> allUserList) {
        this.allUserList = allUserList;
        this.requireActivity = requireActivity;
    }

    @NonNull

    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new userHolder(LayoutInflater.from(requireActivity).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull userAdapter.userHolder holder, int position) {
        try {
            Glide.with(requireActivity).load(allUserList.get(position).getProfileImage()).into(holder.userImage);

            holder.userName.setText(allUserList.get(position).getName());
            holder.userSubject.setText(allUserList.get(position).getSubjects());
            StringBuffer buffer = new StringBuffer();
            if (allUserList.get(position).getQualification().size() == 1) {
                holder.userQualification.setText("•" + allUserList.get(position).getQualification().get(0));
                holder.userQualification.setMaxLines(1);
            } else {
                for (int o = 0; o < allUserList.get(position).getQualification().size(); o++) {
                    buffer.append("•" + allUserList.get(position).getQualification().get(o) + "\n");
                }

                holder.userQualification.setText(buffer);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return allUserList.size();
    }

    class userHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView userSubject;
        TextView userQualification;

        public userHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            userSubject = itemView.findViewById(R.id.userSubject);
            userQualification = itemView.findViewById(R.id.userQualification);
        }
    }


}
