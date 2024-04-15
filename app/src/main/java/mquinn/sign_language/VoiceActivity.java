package mquinn.sign_language;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

import com.bumptech.glide.Glide;

public class VoiceActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 100;
    private ImageView imageViewSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        Button btnSpeak = findViewById(R.id.btnSpeak);
        imageViewSign = findViewById(R.id.imageViewSign);

        // Check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognition();
            }
        });
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, "Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = result.get(0);
            // Use the spoken text to find and display the sign language image/GIF
            displaySignLanguageImage(spokenText);
        }
    }

    private void displaySignLanguageImage(String text) {
        // This method should map the spoken text to the corresponding image or GIF file in the assets folder
        // For simplicity, assume we have a method getFileNameForText which returns the file name
        String fileName = getFileNameForText(text);
        if (!fileName.isEmpty()) {
            // Check the file extension
            String extension = getFileExtension(fileName);
            if (extension.equalsIgnoreCase("gif")) {
                // Load GIF using Glide
                Glide.with(this)
                        .asGif()
                        .load("file:///android_asset/" + fileName)
                        .into(imageViewSign);
            } else {
                // Load image using Glide
                Glide.with(this)
                        .load("file:///android_asset/" + fileName)
                        .into(imageViewSign);
            }
        } else {
            imageViewSign.setImageDrawable(null);
            Toast.makeText(this, "No sign found for the text", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileNameForText(String text) {
        // Simple mapping for demonstration
        // You might want to implement a more sophisticated mapping based on your requirements
        switch (text.toLowerCase()) {
            case "i am sorry":
                return "i am sorry.gif";
            case "address":
                return "address.gif";
            case "Ahmedabad":
                return "ahemdabad.gif";
            case "all":
                return "all.gif";
            case "any questions":
                return "any questions.gif";
            case "are you angry":
                return "are you angry.gif";
            case "are you busy":
                return "are you busy.gif";
            case "are you hungry":
                return "are you hungry.gif";
            case "assam":
                return "assam.gif";
            case "august":
                return "august.gif";
            case "banana":
                return "banana.gif";
            case "banaras":
                return "banaras.gif";
            case "Bangalore":
                return "banglore.gif";
            case "be careful":
                return "be careful.gif";
            case "bridge":
                return "bridge.gif";
            case "cat":
                return "cat.gif";
            case "christmas":
                return "christmas.gif";
            case "church":
                return "church.gif";
            case "cilinic":
                return "cilinic.gif";
            case "dasara":
                return "dasara.gif";
            case "december":
                return "december.gif";
            case "did you finish homework":
                return "did you finish homework.gif";
            case "do you have money":
                return "do you have money.gif";
            case "do you want something to drink":
                return "do you want something to drink.gif";
            case "do you watch tv":
                return "do you watch tv.gif";
            case "don't worry":
                return "dont worry.gif";
            case "flower is beautiful":
                return "flower is beautiful.gif";
            case "good afternoon":
                return "good afternoon.gif";
            case "good morning":
                return "good morning.gif";
            case "good question":
                return "good question.gif";
            case "grapes":
                return "grapes.gif";
            case "hello":
                return "hello.gif";
            case "hindu":
                return "hindu.gif";
            case "hyderabad":
                return "hyderabad.gif";
            case "i am a clerk":
                return "i am a clerk.gif";
            case "i am fine":
                return "i am fine.gif";
            case "i am thinking":
                return "i am thinking.gif";
            case "i am tired":
                return "i am tired.gif";
            case "i go to a theatre":
                return "i go to a theatre.gif";
            case "i had to say something but i forgot":
                return "i had to say something but i forgot.gif";
            case "i like pink colour":
                return "i like pink colour.gif";
            case "i love to shop":
                return "i love to shop.gif";
            case "job":
                return "job.gif";
            case "july":
                return "july.gif";
            case "june":
                return "june.gif";
            case "karnataka":
                return "karnataka.gif";
            case "kerala":
                return "kerala.gif";
            case "krishna":
                return "krishna.gif";
            case "lets go for lunch":
                return "lets go for lunch.gif";
            case "mango":
                return "mango.gif";
            case "may":
                return "may.gif";
            case "mile":
                return "mile.gif";
            case "mumbai":
                return "mumbai.gif";
            case "nagpur":
                return "nagpur.gif";
            case "nice to meet you":
                return "nice to meet you.gif";
            case "open the door":
                return "open the door.gif";
            case "please call me later":
                return "please call me later.gif";
            case "please wait for sometime":
                return "please wait for sometime.gif";
            case "police station":
                return "police station.gif";
            case "post office":
                return "post office.gif";
            case "pune":
                return "pune.gif";
            case "punjab":
                return "punjab.gif";
            case "saturday":
                return "saturday.gif";
            case "shall i help you":
                return "shall i help you.gif";
            case "shall we go together tomorrow":
                return "shall we go together tommorow.gif";
            case "shop":
                return "shop.gif";
            case "sign language interpreter":
                return "sign language interpreter.gif";
            case "sit down":
                return "sit down.gif";
            case "stand up":
                return "stand up.gif";
            case "take care":
                return "take care.gif";
            case "temple":
                return "temple.gif";
            case "there was traffic jam":
                return "there was traffic jam.gif";
            case "thursday":
                return "thursday.gif";
            case "toilet":
                return "toilet.gif";
            case "tomato":
                return "tomato.gif";
            case "tuesday":
                return "tuesday.gif";
            case "usa":
                return "usa.gif";
            case "village":
                return "village.gif";
            case "wednesday":
                return "wednesday.gif";
            case "what are you doing":
                return "what are you doing.gif";
            case "what is the problem":
                return "what is the problem.gif";
            case "what is today's date":
                return "what is today's date.gif";
            case "what is your father do":
                return "what is your father do.gif";
            case "what is your mobile number":
                return "what is your mobile number.gif";
            case "what is your name":
                return "what is your name.gif";
            case "whats up":
                return "whats up.gif";
            case "where is the bathroom":
                return "where is the bathroom.gif";
            case "where is the police station":
                return "where is the police station.gif";
            case "you are wrong":
                return "you are wrong.gif";
            default:
                return "";
        }

    }

    private String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}