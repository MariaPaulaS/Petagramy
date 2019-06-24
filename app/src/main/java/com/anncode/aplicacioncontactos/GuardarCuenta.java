package com.anncode.aplicacioncontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.anncode.recyclerviewfragments.R;

public class GuardarCuenta extends AppCompatActivity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_cuenta);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }


    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){

            case android.R.id.home:

                Intent intento= new Intent(GuardarCuenta.this, MainActivity.class);
                startActivity(intento);
                finish();
                break;

        }

        return true;

    }
}
