package com.example.myproject.ui.HomePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.R;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomePageFragment extends Fragment {

    ArrayAdapter<String> adapter;
    ListView listView;
    ArrayList<String> arrayList;
    View.OnClickListener clickListener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomePageViewModel homePageViewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView)root.findViewById(R.id.sectionListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return root;
    }


}