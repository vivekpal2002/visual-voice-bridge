package mquinn.sign_language;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button signtotext, voicetotext, datasetButton, aboutUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        signtotext = findViewById(R.id.signtotext);
        voicetotext = findViewById(R.id.voicetotext);
        datasetButton = findViewById(R.id.dataset_button);
        aboutUsButton = findViewById(R.id.about_us_button);

        signtotext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(intent);
            }
        });

        voicetotext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VoiceActivity.class);
                startActivity(intent);
            }
        });

        datasetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageDataset();
            }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTeamName();
            }
        });
    }

    private void openImageDataset() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivity(intent);
    }

    private void displayTeamName() {
        // Replace "Your Team Name" with your actual team name
        Toast.makeText(MainActivity.this, "Our Team Name: Your Team Name", Toast.LENGTH_SHORT).show();
    }
}
