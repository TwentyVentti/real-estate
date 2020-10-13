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
import com.example.myproject.ViewModels.MainActivity;
import com.example.myproject.ViewModels.loginActivity;
import java.util.ArrayList;

public class HomePageFragment extends Fragment {
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    ArrayList<String> sections;
    View.OnClickListener clickListener;
    ArrayList<String> USER_SELECTION = MainActivity.getUserSelectionFromEdit();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomePageViewModel homePageViewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView)root.findViewById(R.id.sectionListView);

        sections = MainActivity.getUserSelectionFromEdit();

        try {
            if (getActivity()!=null)
                arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, sections);
            else
                System.err.println("NULL POINTER: ARRAY ADAPTER APPLICATION CONTEXT");
        } catch (NullPointerException nullPointer){
            System.err.println("NULL POINTER: ARRAY ADAPTER APPLICATION CONTEXT");
            nullPointer.printStackTrace();
        } catch (Exception ex){
            System.err.println("GENERAL EXCEPTION: ARRAY ADAPTER APPLICATION CONTEXT");
            ex.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return root;
    }
/*
public class User {
    private String language;
    private Integer level;
    private String city;
    private Integer duration;
    private String country;
 */
    public ArrayList<String> getSections(){
        return new ArrayList<>();
    }


}