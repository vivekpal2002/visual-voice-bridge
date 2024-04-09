package mquinn.sign_language;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

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
        // This method should map the spoken text to the corresponding GIF file in the assets folder
        // For simplicity, assume we have a method getGifFileNameForText which returns the GIF file name
        String gifFileName = getGifFileNameForText(text);
        if (!gifFileName.isEmpty()) {
            try {
                InputStream inputStream = getAssets().open(gifFileName);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                // Load the GIF from assets folder
                imageViewSign.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error loading GIF", Toast.LENGTH_SHORT).show();
            }
        } else {
            imageViewSign.setImageURI(null);
            Toast.makeText(this, "No sign found for the text", Toast.LENGTH_SHORT).show();
        }
    }

    private String getGifFileNameForText(String text) {
        // Simple mapping for demonstration
        // You might want to implement a more sophisticated mapping based on your requirements
        switch (text.toLowerCase()) {
            case "i am sorry":
                return "i am sorry.gif";
            // Add more cases as needed
            default:
                return "";
        }
    }

}