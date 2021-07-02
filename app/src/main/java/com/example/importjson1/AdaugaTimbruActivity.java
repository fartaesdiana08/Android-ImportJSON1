package com.example.importjson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class AdaugaTimbruActivity extends AppCompatActivity {

    EditText denumire;
    EditText calitate;
    SeekBar pret;
    RadioGroup radioGroup;
    DatePicker data_achizitie;
    Spinner culoare;

    Button btnSalveaza;
    RadioButton radioButton;

    public static final String ADD_TIMBRU = "adaugaTimbru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_timbru);

        denumire=findViewById(R.id.denumire);
        calitate=findViewById(R.id.calitate);
        pret=findViewById(R.id.seek_bar);
        radioGroup=findViewById(R.id.radioGroup);
        data_achizitie=findViewById(R.id.data_achizite);

        culoare=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adaptor = ArrayAdapter.createFromResource(this, R.array.culoare,
                android.R.layout.simple_spinner_dropdown_item);
        culoare.setAdapter(adaptor);

        btnSalveaza=findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(denumire.getText().toString().isEmpty())
                {
                    Toast.makeText(AdaugaTimbruActivity.this, R.string.error1,Toast.LENGTH_SHORT).show();
                }
                else if(calitate.getText().toString().isEmpty() )
                {
                    Toast.makeText(AdaugaTimbruActivity.this, R.string.error2,Toast.LENGTH_SHORT).show();
                }
                else{
                    String txt_denumire=denumire.getText().toString();
                    int txt_calitate=Integer.parseInt(calitate.getText().toString());
                    int txt_pret=pret.getProgress();
                    radioButton=findViewById(radioGroup.getCheckedRadioButtonId());
                    String txt_categorie=radioButton.getText().toString();
                    int txt_data_achiz=data_achizitie.getMonth();
                    String txt_culoare=culoare.getSelectedItem().toString().toLowerCase();

                    //intent
                    Timbru timbru = new Timbru(txt_denumire, txt_calitate, txt_pret, txt_categorie, txt_data_achiz, txt_culoare);
                    Intent intent = new Intent();
                    intent.putExtra(ADD_TIMBRU, timbru);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}