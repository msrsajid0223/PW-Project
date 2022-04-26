package com.example.collegeproject.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.collegeproject.Adapter.userAdapter;
import com.example.collegeproject.Model.userModel;
import com.example.collegeproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BlankFragment extends Fragment {

    RecyclerView dataRcv;
    String api = "https://my-json-server.typicode.com/easygautam/data/users";
    ArrayList<userModel> allUserList;
    ArrayList subjectsList;
    ArrayList qualificationList;


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        dataRcv = view.findViewById(R.id.dataRcv);
        dataRcv.setLayoutManager(new LinearLayoutManager(requireActivity()));

        //get all data from API
        getData();

        view.findViewById(R.id.backl).setOnClickListener(v -> {
            getActivity().finishAffinity();
        });

        return view;
    }


    public void getData() {
        allUserList = new ArrayList<>();
        subjectsList = new ArrayList();
        qualificationList = new ArrayList();

        allUserList.clear();
        subjectsList.clear();
        qualificationList.clear();
        RequestQueue queue = Volley.newRequestQueue(requireActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                Log.e("jenil", "onResponse: " + object);
                                int id = object.getInt("id");
                                String name = object.getString("name");
                                JSONArray arrayOfSubject = object.getJSONArray("subjects");
                                JSONArray qualification = object.getJSONArray("qualification");
                                String profilePicture = object.getString("profileImage");
                                ArrayList<String> qList = new ArrayList<>();
                                for (int j = 0; j < qualification.length(); j++) {
                                    qList.add(qualification.get(j).toString());
                                }
                                String nameOfSubject = arrayOfSubject.get(0).toString();

                                allUserList.add(new userModel(id, name, nameOfSubject, qList, profilePicture));
                            }

                            //set Adapter using list
                            userAdapter userAdapter = new userAdapter(requireActivity(), allUserList);
                            dataRcv.setAdapter(userAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("splash", "onErrorResponse: " + error.getLocalizedMessage());
            }
        });

        queue.add(stringRequest);
    }

}