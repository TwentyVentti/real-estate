package com.example.myproject.ViewModels;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.Models.BST.BinarySearch;
import com.example.myproject.Models.BST.Node;
import com.example.myproject.Models.Phrase;
import com.example.myproject.R;
import com.example.myproject.ui.HomePage.HomePageFragment;
import android.speech.tts.TextToSpeech;
//import com.google.cloud.texttospeech.v1beta1.TextToSpeechClient;
//import com.google.cloud.texttospeech.v1.AudioConfig;
//import com.google.cloud.texttospeech.v1.AudioEncoding;
//import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
//import com.google.cloud.texttospeech.v1.SynthesisInput;
//import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
//import com.google.cloud.texttospeech.v1.TextToSpeechClient;
//import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
//import com.google.protobuf.ByteString;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * This class represents the activity which will be called when the user clicks on one of the list objects
 * in the homePageFragment listView.
 * @author Andrew Carse - u6666440
 */
public class PhraseListActivity extends AppCompatActivity {
    TextView sectionTextView;
    String section;
    ListView phraseListView;
    ArrayAdapter<String> phraseArrayAdapter;
    ArrayList<String> userFirstLanguagePhrases = new ArrayList<>();
    ArrayList<String> userSelectedLanguagePhrases = new ArrayList<>();
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_list);
        sectionTextView = findViewById(R.id.sectionTextView);
        textToSpeech =new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };
        setLanguageArrays();
        initListView();
        sectionTextView.setText(section);
    }

    /**
     * Initialises the listView, arrayAdapter and all
     * objects related to the list of phrases being displayed.
     */
    private void initListView(){
        phraseListView = findViewById(R.id.phraseListView);
        phraseArrayAdapter = new ArrayAdapter<>(PhraseListActivity.this,android.R.layout.simple_list_item_1, userFirstLanguagePhrases);
        phraseListView.setAdapter(phraseArrayAdapter);
        phraseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast toast = Toast.makeText(getApplicationContext(),userSelectedLanguagePhrases.get(i),Toast.LENGTH_LONG);
                toast.show();
                String s = userSelectedLanguagePhrases.get(i).toString();
                textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        Intent homePageIntent = getIntent();
        try {
            section = homePageIntent.getStringExtra("Section").replaceAll("_", " ");
        } catch (Exception ex){
            System.err.println("Null section title");
            ex.printStackTrace();
        }
    }
    /**
     *
     * @return the phrases that are needed to populate the listview.
     */

    private ArrayList<Node> getBSTPhrases() {
        ArrayList<Node> phrases = new ArrayList<>();
        int currentSection = 1; // Replace it with getIntent
        int level = HomePageFragment.USER_SELECTION.level;
        HashMap<String, BinarySearch> levelBST;
        try {
            levelBST = loginActivity.levelBST;
            String language =HomePageFragment.USER_SELECTION.language;
            language = language.substring(0, 1).toUpperCase() + language.substring(1);
            phrases = levelBST.get(language).getArrayFromLevelSection(level,currentSection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return phrases;
    }
    private ArrayList<Phrase> getPhrases(){
        ArrayList<Phrase> phrases = new ArrayList<>();
        String currentSection = getIntent().getStringExtra("Section");
        for (int i = 0; i < HomePageFragment.levelArrayMap.size(); i++) {
            if (HomePageFragment.levelArrayMap.get(i).keySet().iterator().hasNext()){
                if (HomePageFragment.levelArrayMap.get(i).keySet().iterator().next().equals(currentSection)){
                    phrases=HomePageFragment.levelArrayMap.get(i).get(currentSection);
                }
            }
        }
        return phrases;
    }

    /**
     * Sets the @userFirstLanguagePhrases and the @userSelectedLanguagePhrases
     * Also, depending on the language, will set the text-to-speech object
     * to the correct locale.
     */
    private void setLanguageArrays(){
        String language = HomePageFragment.USER_SELECTION.language;
//        ArrayList<Phrase> phraseArrayList = getPhrases();
        ArrayList<Node> phraseBSTList = getBSTPhrases();
        int x =0;
        for (Node phrase :phraseBSTList) {
            userFirstLanguagePhrases.add(phrase.getEnglishPhrase());
            switch (language){
                case "French":
                    if (x==0){
                        textToSpeech.setLanguage(Locale.FRENCH);
                        x+=1;
                    }
                case "Dutch":
                    if (x==0){
                        textToSpeech.setLanguage(new Locale("nl","NL"));
                        x+=1;
                    }
                case "Italian":
                    if (x==0){
                        textToSpeech.setLanguage(Locale.ITALIAN);
                        x+=1;
                    }
                case "Spanish":
                    if (x==0){
                        textToSpeech.setLanguage(new Locale("es_ES"));
                        x+=1;
                    }
                    break;
                default:
                    break;
            }
            userSelectedLanguagePhrases.add(phrase.getLanguagePhrase());
        }
    }

}