package thanhtran.mohawk.bookviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BookDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        TextView textView = findViewById(R.id.textView_title);
        textView.setText(getIntent().getStringExtra("title"));
        textView.setText(getIntent().getStringExtra("imagelink"));
    }
}
