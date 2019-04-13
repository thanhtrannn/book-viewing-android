package thanhtran.mohawk.bookviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BookDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("log", "BookDetail activity started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // find and set TextView
        TextView textView_title = findViewById(R.id.textView_title);
        TextView textView_subtitle = findViewById(R.id.textView_subtitle);
        TextView textView_publisher = findViewById(R.id.textView_publisher);
        TextView textView_publisherDate = findViewById(R.id.textView_publisherDate);
        TextView textView_Description = findViewById(R.id.textView_description);

        // set textView with intent data
        textView_title.setText(getIntent().getStringExtra("title"));
        textView_subtitle.setText(getIntent().getStringExtra("subtitle"));
        Log.d("debug", "Insert data to text view");
        // handles empty data
        String publisher = getIntent().getStringExtra("publisher");
        if (publisher == null){
            publisher = "Publisher not available";
        }
        textView_publisher.setText(publisher);
        String publishedDate = getIntent().getStringExtra("publishedDate");
        if (publishedDate == null){
            publishedDate = "Published date is not available";
        }
        textView_publisherDate.setText(publishedDate);
        String description = getIntent().getStringExtra("description");
        if (description == null){
            description = "Description not available";
        }
        textView_Description.setText(description);
        // handles images
        String imageLink = "";
        imageLink = getIntent().getStringExtra("imagelink");
        if (imageLink != null || imageLink != "")
        {
            new DownloadImageTask().execute(imageLink);
        }
        Log.d("log", "BookDetails activity completed");
    }
    // Class to handle images
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {

            Bitmap bmp = null;
            Log.d("debug", "do background for image: " + urls[0]);
            // URL connection must be done in a try/catch
            try {
                URL url = null;
                url = new URL(urls[0]);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            }
            catch (MalformedURLException e) {
                Log.d("error", e.getMessage());
            }
            catch (IOException e) {
                Log.d("error", e.getMessage());
            }
            Log.d("debug", "done ");
            return bmp;
        }

        // Display the image in UI
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                Log.d("log", "Set image to image view");
                // Find the ImageView object to use
                ImageView imageView = (ImageView) findViewById(R.id.image_book);
                imageView.setImageBitmap(result);

            }
        }
    }
}
