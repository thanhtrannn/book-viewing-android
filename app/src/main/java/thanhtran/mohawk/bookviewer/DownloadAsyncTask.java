package thanhtran.mohawk.bookviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

// class for rest call
public class DownloadAsyncTask extends AsyncTask<String, Void, String> implements AdapterView.OnItemClickListener {

    // set instance variable
    private Activity myActivity;
    public DownloadAsyncTask(Activity inActivity) {
        myActivity = inActivity;
    }
    Books books;

    @Override
    protected String doInBackground(String... params) {
        Log.d("log", "Starting background task...");
        String results = "";
        try{
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int statusCode = conn.getResponseCode();
            Log.d("log", "Response Code: " + statusCode);
            // Run only if successful GET
            if (statusCode == 200) {
                InputStream inputStream = new BufferedInputStream(conn.getInputStream());

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                // Read through line and write to result
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    results += line;
                }
            }
            Log.d("log", "Results: " + results);
        }
        catch(Exception ex){
            Log.d("error", ex.getMessage());
        }
        return results;
    }

    protected void onPostExecute(String result) {
        TextView textView_error = myActivity.findViewById(R.id.textView_error);
        Log.d("log", "Setting items for list view...");
        // set gson and attach to book class
        Gson gson = new Gson();
        books = gson.fromJson(result, Books.class);
        ListView lv = (ListView) myActivity.findViewById(R.id.listView);
        // set results to list view
        if (books != null && books.totalItems != 0) {
            textView_error.setText("");
            ArrayAdapter<Books.Items> adapter = new ArrayAdapter<Books.Items>(myActivity, android.R.layout.simple_list_item_1, books.items);
            // set onclick listener
            lv.setOnItemClickListener(this);
            lv.setAdapter(adapter);
        }
        else {
            // clear list
            lv.setAdapter(null);
            textView_error.setText(R.string.searchEmpty);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("log", "Item clicked, position: " + position);
        // Set and launch Intent
        Intent intent = new Intent(myActivity, BookDetails.class);

        // Put info into intent
        intent.putExtra("title", books.items.get(position).volumeInfo.title);
        intent.putExtra("subtitle", books.items.get(position).volumeInfo.subtitle);
        if (books.items.get(position).volumeInfo.imageLinks != null){
            intent.putExtra("imagelink", books.items.get(position).volumeInfo.imageLinks.thumbnail);
        }
        intent.putExtra("publisher", books.items.get(position).volumeInfo.publisher);
        intent.putExtra("publishedDate", books.items.get(position).volumeInfo.publishedDate);
        intent.putExtra("description", books.items.get(position).volumeInfo.description);
        myActivity.startActivity(intent);
    }

}
