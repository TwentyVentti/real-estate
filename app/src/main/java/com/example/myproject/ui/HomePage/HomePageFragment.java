package com.example.myproject.ui.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.Models.Parsing.BaseExp;
import com.example.myproject.Models.Parsing.GrammarException;
import com.example.myproject.Models.Parsing.TokenException;
import com.example.myproject.Models.Phrase;

import com.example.myproject.R;
import com.example.myproject.ViewModels.MainActivity;
import com.example.myproject.ViewModels.PhraseListActivity;
import com.example.myproject.ViewModels.SearchActivity;
import com.example.myproject.ViewModels.loginActivity;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO: Docstring
 * @author Andrew Carse - u6666440
 */
public class HomePageFragment extends Fragment {
    public ArrayAdapter<String> arrayAdapter;
    public ListView sectionListView;
    public static ArrayList<String> sections = new ArrayList<>();
    public static ArrayList<HashMap<String,ArrayList<Phrase>>> levelArrayMap;
    public View.OnClickListener clickListener;
    public static BaseExp USER_SELECTION;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomePageViewModel homePageViewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        sectionListView = (ListView)root.findViewById(R.id.sectionListView);
        try {
            USER_SELECTION= SearchActivity.getUserSelectionFromEdit();
        } catch (GrammarException e) {
            e.printStackTrace();
        }
        sections = getSections();
        try {
            if (getActivity()!=null) {
                arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, sections);
            }
            else {
                System.err.println("NULL POINTER: ARRAY ADAPTER APPLICATION CONTEXT");
            }
        } catch (NullPointerException nullPointer){
            System.err.println("NULL POINTER: ARRAY ADAPTER APPLICATION CONTEXT");
            nullPointer.printStackTrace();
        } catch (Exception ex){
            System.err.println("GENERAL EXCEPTION: ARRAY ADAPTER APPLICATION CONTEXT");
            ex.printStackTrace();
        }
        sectionListView.setAdapter(arrayAdapter);

        sectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PhraseListActivity.class);
                intent.putExtra("Section",sections.get(i));
                startActivityForResult(intent,1);
            }
        });
        return root;
    }
    /** TODO: Level 3 not created */
    public ArrayList<String> getSections(){
        ArrayList<String> sections = new ArrayList<>();
        HashMap<String,ArrayList<HashMap<String,ArrayList<Phrase>>>> phraseHash;
        ArrayList<HashMap<String,ArrayList<Phrase>>> mapArrayList = new ArrayList<>();
        try{
            phraseHash = loginActivity.phraseListHash;
            int level = USER_SELECTION.level;
            System.out.println(level);
            switch (level){
                case 1:
                    mapArrayList = phraseHash.get("Level 1");
                    System.out.println(11);
                    levelArrayMap = mapArrayList;
                    break;
                case 2:
                    mapArrayList = phraseHash.get("Level 2");
                    System.out.println(22);
                    levelArrayMap = mapArrayList;
                    break;
                case 3:
                    mapArrayList = phraseHash.get("Level 3");
                    System.out.println(mapArrayList.isEmpty());
                    System.out.println(33);
                    levelArrayMap = mapArrayList;
                    break;
                default:
                    System.out.println(44);
                    break;
            }
            assert mapArrayList != null;
            for (HashMap<String,ArrayList<Phrase>> levelMap : mapArrayList) {
                sections.add(levelMap.keySet().iterator().next());
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return sections;
    }


}