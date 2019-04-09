// I, Thanh Tran, 000737993 certify that this material is my original work. No other person's work has been used without due acknowledgement.
package thanhtran.mohawk.bookviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String uri = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startDownload(View view){
        DownloadAsyncTask dl = new DownloadAsyncTask(this);
        // Build call to Webservice
        //https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=AIzaSyA5c6IgZgZnLxuDUSINo40zLH-151dCaF4
        String uri = "https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=AIzaSyA5c6IgZgZnLxuDUSINo40zLH-151dCaF4";
        /*
        String sYear = "2017";
        if (!sYear.equals("")) {
            // Filter - numbers (Year) no quotes on value, strings have quotes on value
            uri += "?filter=[{\"type\":\"number\",\"column\":\"Year\",\"value\":" + sYear + "}]";
        }*/

        dl.execute(uri);
    }

    public void testFunction(View view){

    }
}
