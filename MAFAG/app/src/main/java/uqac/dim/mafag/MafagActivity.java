package uqac.dim.mafag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MafagActivity extends AppCompatActivity {

    private static String selectedName = "microsoft";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mafag_activity);
    }

    public void onClickGoogle(View view) {
        if (Objects.equals(selectedName, "google")) {
            ImageButton googleButton = findViewById(R.id.imageButtonGoogle);
            googleButton.setEnabled(false);
        } else {
            selectedName = "google";
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedName", "google");
            resultIntent.putExtra("selectedUrl", "https://www.google.ca/");
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    public void onClickMicrosoft(View view) {
        if (Objects.equals(selectedName, "microsoft")) {
            ImageButton microsoftButton = findViewById(R.id.imageButtonMicrosoft);
            microsoftButton.setEnabled(false);
        } else {
            selectedName = "microsoft";
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedName", "microsoft");
            resultIntent.putExtra("selectedUrl", "https://www.microsoft.com/");
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    public void onClickFacebook(View view) {
        if (Objects.equals(selectedName, "facebook")) {
            ImageButton facebookButton = findViewById(R.id.imageButtonFacebook);
            facebookButton.setEnabled(false);
        } else {
            selectedName = "facebook";
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedName", "facebook");
            resultIntent.putExtra("selectedUrl", "https://www.facebook.com/");
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    public void onClickApple(View view) {
        if (Objects.equals(selectedName, "apple")) {
            ImageButton appleButton = findViewById(R.id.imageButtonApple);
            appleButton.setEnabled(false);
        } else {
            selectedName = "apple";
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedName", "apple");
            resultIntent.putExtra("selectedUrl", "https://www.apple.com/");
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    public void onClickAmazon(View view) {
        if (Objects.equals(selectedName, "amazon")) {
            ImageButton amazonButton = findViewById(R.id.imageButtonAmazon);
            amazonButton.setEnabled(false);
        } else {
            selectedName = "amazon";
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedName", "amazon");
            resultIntent.putExtra("selectedUrl", "https://www.amazon.com/");
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
