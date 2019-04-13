// I, Thanh Tran, 000737993 certify that this material is my original work. No other person's work has been used without due acknowledgement.
package thanhtran.mohawk.bookviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_error = findViewById(R.id.textView_error);
        textView_error.setText("");
    }

    public void startDownload(View view){
        String search = "";
        String filter = "";
        Log.d("log", "Button pressed, beginning api uri construction");
        DownloadAsyncTask dl = new DownloadAsyncTask(this);
        EditText editText_search = findViewById(R.id.editText_search);
        search = editText_search.getText().toString();
        switch(view.getId()){
            case R.id.btn_author:
                filter="inauthor";
                break;
            case R.id.btn_both:
                filter="intitle:" + search + "+inauthor";
                break;
            case R.id.btn_title:
                filter="intitle";
                break;

        }
        String uri = "https://www.googleapis.com/books/v1/volumes?q=" + filter + ":" + search + "&projection=lite&key=AIzaSyA5c6IgZgZnLxuDUSINo40zLH-151dCaF4";
        dl.execute(uri);
        editText_search.setText("");

    }

}
