package com.example.myproject.ui.Greetings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.Models.MapActivity;
import com.example.myproject.R;

public class GreetingsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Intent i = new Intent(getActivity(), MapActivity.class);
        startActivity(i);

        //GreetingsViewModel greetingsViewModel = ViewModelProviders.of(this).get(GreetingsViewModel.class);
        View root = inflater.inflate(R.layout.activity_map, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        //greetingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }

}
