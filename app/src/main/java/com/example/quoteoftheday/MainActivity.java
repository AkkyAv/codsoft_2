package com.example.quoteoftheday;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private Button saveButton;
    private Button shareButton;
    private Button viewSavedButton; // Button to view saved quotes
    private SharedPreferences sharedPreferences;
    private String[] quotes = {
            "Life is what happens when you're busy making other plans.\" - John Lennon",
            "In three words I can sum up everything I've learned about life: it goes on.\" - Robert Frost",
            "The only way to do great work is to love what you do.\" - Steve Job",
            "Be yourself; everyone else is already taken.\" - Oscar Wilde",
            "Success is not final, failure is not fatal: It is the courage to continue that counts.\" - Winston Churchill",
            "The only thing we have to fear is fear itself.\" - Franklin D. Roosevelt",
            "Happiness is not something ready made. It comes from your own actions.\" - Dalai Lama",
            "The journey of a thousand miles begins with one step.\" - Lao Tzu",
            "In the end, we will remember not the words of our enemies, but the silence of our friends.\" - Martin Luther King Jr.",
            "Life is 10% what happens to us and 90% how we react to it.\" - Charles R. Swindoll"
    };
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        saveButton = findViewById(R.id.saveButton);
        shareButton = findViewById(R.id.shareButton);
        viewSavedButton = findViewById(R.id.viewSavedButton); // Initialize the "View Saved Quotes" button
        sharedPreferences = getSharedPreferences("MyQuotes", MODE_PRIVATE);

        generateAndDisplayQuote();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrentQuote(); // Save the current quote as a favorite
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCurrentQuote();
            }
        });

        viewSavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity to view saved quotes
                Intent intent = new Intent(MainActivity.this, SavedQuotesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void generateAndDisplayQuote() {
        // Generate a random index to select a quote from the array
        int randomIndex = new Random().nextInt(quotes.length);

        // Get the quote at the random index
        String randomQuote = quotes[randomIndex];

        // Display the selected quote
        quoteTextView.setText(randomQuote);
    }

    private void saveCurrentQuote() {
        // Get the current quote
        String currentQuote = quoteTextView.getText().toString();

        // Get a reference to the SharedPreferences editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Generate a unique key for the saved quote (e.g., using timestamp)
        long timestamp = System.currentTimeMillis();
        String key = Long.toString(timestamp);

        // Save the quote with the key
        editor.putString(key, currentQuote);
        editor.apply();

        // Provide feedback to the user
        // You can show a Toast or update a TextView to indicate success
        // For simplicity, we'll show a Toast here
        Toast.makeText(this, "Quote saved!", Toast.LENGTH_SHORT).show();
    }
    private void shareCurrentQuote() {
        // Implement the code to share the current quote
        String quoteToShare = quoteTextView.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, quoteToShare);
        startActivity(Intent.createChooser(shareIntent, "Share Quote"));
    }
}
