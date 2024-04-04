package uqac.dim.personnage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Personnage mage;
    private Personnage guerrier;
    private Personnage personnageCourant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mage = new Mage();
        guerrier = new Guerrier();
        personnageCourant = mage;
        chargerPersonnage();

        RadioGroup choixPersonnage = (RadioGroup)findViewById(R.id.radioGroup);
        choixPersonnage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonGuerrier) {
                    personnageCourant = guerrier;
                } else {
                    personnageCourant = mage;
                }
                chargerPersonnage();
            }
        });

        CheckBox activerModification = (CheckBox)findViewById(R.id.modifStat);
        activerModification.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i("DIM", "activer : " + isChecked);
                ((EditText) findViewById(R.id.nom)).setEnabled(isChecked);
                ((EditText) findViewById(R.id.pv)).setEnabled(isChecked);
                ((EditText) findViewById(R.id.ca)).setEnabled(isChecked);
                ((EditText) findViewById(R.id.dmg)).setEnabled(isChecked);
                ((EditText) findViewById(R.id.pm)).setEnabled(isChecked);
                ((Switch)findViewById(R.id.alignement)).setEnabled(isChecked);
            }
        });

        Switch alignement = (Switch)findViewById(R.id.alignement);
        alignement.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i("DIM", "changer alignement : " + isChecked);
                if (isChecked)
                    alignement.setText(alignement.getTextOn());
                else
                    alignement.setText(alignement.getTextOff());
            }
        });
    }


    private void chargerPersonnage(){

        Log.i("DIM", "chargerProfil");

        ((EditText)findViewById(R.id.nom)).setText(personnageCourant.getNom());
        ((TextView)findViewById(R.id.typePerso)).setText(personnageCourant.getClasse());
        ((EditText)findViewById(R.id.pv)).setText(personnageCourant.getPV() + "");
        ((EditText)findViewById(R.id.ca)).setText(personnageCourant.getCA() + "");
        ((EditText)findViewById(R.id.dmg)).setText(personnageCourant.getDMG() + "");

        Switch alignement = ((Switch)findViewById(R.id.alignement));
        if (personnageCourant.getAlignement() == Personnage.Alignement.BON)
            alignement.setChecked(false);
        else
            alignement.setChecked(true);

        if (personnageCourant instanceof Mage){
            ((RadioGroup)findViewById(R.id.radioGroup)).check(R.id.radioButtonMage);
            ((EditText)findViewById(R.id.pm)).setText(((Mage)personnageCourant).getPM() + "");
            ((ImageView)findViewById(R.id.imagePerso)).setImageResource(R.drawable.mage);
            findViewById(R.id.magicPts).setVisibility(View.VISIBLE);
            findViewById(R.id.pm).setVisibility(View.VISIBLE);
        }
        else{
            ((RadioGroup)findViewById(R.id.radioGroup)).check(R.id.radioButtonGuerrier);
            ((ImageView)findViewById(R.id.imagePerso)).setImageResource(R.drawable.guerrier);
            findViewById(R.id.magicPts).setVisibility(View.GONE);
            findViewById(R.id.pm).setVisibility(View.GONE);
        }
    }

    public void sauvegarderProfil(View v){

        Log.i("DIM", "sauvegarderProfil");
        personnageCourant.setNom(((EditText)findViewById(R.id.nom)).getText().toString());
        personnageCourant.setPV(Integer.parseInt(((EditText)findViewById(R.id.pv)).getText().toString()));
        personnageCourant.setCA(Integer.parseInt(((EditText)findViewById(R.id.ca)).getText().toString()));
        personnageCourant.setDMG(Integer.parseInt(((EditText)findViewById(R.id.dmg)).getText().toString()));

        if (((Switch)findViewById(R.id.alignement)).isChecked())
            personnageCourant.setAlignement(Personnage.Alignement.MAUVAIS);
        else
            personnageCourant.setAlignement(Personnage.Alignement.BON);

        if (personnageCourant instanceof Mage)
            ((Mage)personnageCourant).setPM(Integer.parseInt(((EditText)findViewById(R.id.pm)).getText().toString()));
        chargerPersonnage();
    }

    public void nouveauProfil(View v){

        Log.i("DIM", "nouveauProfil");

        ((EditText)findViewById(R.id.nom)).setText("");
        ((EditText)findViewById(R.id.pv)).setText("5");
        ((EditText)findViewById(R.id.ca)).setText("5");
        ((EditText)findViewById(R.id.dmg)).setText("5");
        ((Switch)findViewById(R.id.alignement)).setChecked(false);
        ((CheckBox)findViewById(R.id.modifStat)).setChecked(true);

        if (personnageCourant instanceof Mage)
            ((EditText)findViewById(R.id.pm)).setText("5");
    }

    public void reinitialiserProfil(View v){

        Log.i("DIM", "reinitialiserProfil");

        if (personnageCourant instanceof Mage){
            mage = new Mage();
            personnageCourant = mage;
        }
        else{
            guerrier = new Guerrier();
            personnageCourant = guerrier;
        }
        chargerPersonnage();
    }
}