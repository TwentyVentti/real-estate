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
        setLanguageArrays();
        setContentView(R.layout.activity_phrase_list);
        sectionTextView = findViewById(R.id.sectionTextView);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
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
        sectionTextView.setText(section);
    }
    /**
     * @return the phrases that are needed to populate the listview.
     */
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

//    /**
//     * Sets the @userFirstLanguagePhrases and the @userSelectedLanguagePhrases
//     */
    private void setLanguageArrays(){
        String language = HomePageFragment.USER_SELECTION.language;
        ArrayList<Phrase> phraseArrayList = getPhrases();
        int x =0;
        for (Phrase phrase :phraseArrayList) {
            userFirstLanguagePhrases.add(phrase.getEnglish());
            switch (language){
                case "French":
                    userSelectedLanguagePhrases.add(phrase.getFrench());
                    break;
                case "Dutch":
                    userSelectedLanguagePhrases.add(phrase.getDutch());
                    break;
                case "Italian":
                    userSelectedLanguagePhrases.add(phrase.getItalian());
                    break;
                case "Spanish":
                    userSelectedLanguagePhrases.add(phrase.getSpanish());
                    break;
                default:
                    break;
            }
        }
    }
//
//    public void textToSpeech(String phrase) throws IOException {
//        try(TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
//            // Set the text input to be synthesized
//            SynthesisInput input = SynthesisInput.newBuilder().setText("Hello, World!").build();
//
//            // Build the voice request, select the language code ("en-US") and the ssml voice gender
//            // ("neutral")
//            VoiceSelectionParams voice =
//                    VoiceSelectionParams.newBuilder()
//                            .setLanguageCode("en-US")
//                            .setSsmlGender(SsmlVoiceGender.NEUTRAL)
//                            .build();
//
//            // Select the type of audio file you want returned
//            AudioConfig audioConfig =
//                    AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
//
//            // Perform the text-to-speech request on the text input with the selected voice parameters and
//            // audio file type
//
//            SynthesizeSpeechResponse response =
//                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
//
//            // Get the audio contents from the response
//            ByteString audioContents = response.getAudioContent();
//
//            // Write the response to the output file.
//            try (OutputStream out = new FileOutputStream("output.mp3")) {
//                out.write(audioContents.toByteArray());
//                System.out.println("Audio content written to file \"output.mp3\"");
//            }
//        }
//        }

}