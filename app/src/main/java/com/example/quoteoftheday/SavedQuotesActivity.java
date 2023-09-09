package com.example.quoteoftheday;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Map;

public class SavedQuotesActivity extends AppCompatActivity {

    private ListView savedQuotesListView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);

        savedQuotesListView = findViewById(R.id.savedQuotesListView);
        sharedPreferences = getSharedPreferences("MyQuotes", MODE_PRIVATE);

        // Retrieve saved quotes from SharedPreferences
        Map<String, ?> savedQuotesMap = sharedPreferences.getAll();
        ArrayList<String> savedQuotesList = new ArrayList<>();

        for (Map.Entry<String, ?> entry : savedQuotesMap.entrySet()) {
            savedQuotesList.add(entry.getValue().toString());
        }

        // Create an ArrayAdapter to display saved quotes in a ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, savedQuotesList);
        savedQuotesListView.setAdapter(adapter);
    }
}
