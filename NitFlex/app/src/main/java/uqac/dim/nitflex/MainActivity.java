package uqac.dim.nitflex;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void printMessage(View view) {
        Log.i("DIM", "Nitflex Log !");
    }

    public void eventItem1(MenuItem item) {
        Log.i("DIM", "Item 1 selected !");
    }

    public void eventItem2(MenuItem item) {
        Log.i("DIM", "Item 2 selected !");
    }
}