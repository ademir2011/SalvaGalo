package com.example.ademi.salvagalo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, FirstService.class));

        Toast.makeText(MainActivity.this, "Serviço instanciado !", Toast.LENGTH_SHORT).show();

        finish();
    }
}
