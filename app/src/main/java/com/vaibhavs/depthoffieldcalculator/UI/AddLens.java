package com.vaibhavs.depthoffieldcalculator.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

public class AddLens extends AppCompatActivity {

    LensManager lenses;
    int[] iconIDs = new int[]{R.drawable.lens1,R.drawable.lens2,R.drawable.lens3,R.drawable.lens4,R.drawable.lens5};
    int Iconid = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        lenses = LensManager.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        Save_add();
        selectIcon();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_add,Mu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.action_remove:
                Intent i = new Intent(AddLens.this,RemoveLens.class);
                startActivity(i);
                return true;
            case android.R.id.home:
                Toast.makeText(AddLens.this,"Pressed cancel!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED,intent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Save_add() {
        Button Save_btn = findViewById(R.id.btn_Savelens);
        Save_btn.setOnClickListener(v -> {
            EditText make_Et = findViewById(R.id.input_Make);
            String Make = make_Et.getText().toString();
            if(Make.isEmpty()){
                Toast.makeText(AddLens.this,"Make cannot be empty",Toast.LENGTH_SHORT).show();
            }
            EditText max_aperature_Et = findViewById(R.id.input_Aperture);
            double Aperature = Double.parseDouble(max_aperature_Et.getText().toString());
            if(Aperature < 1.4){
                Toast.makeText(AddLens.this,"Aperture cannot be less than 1.4",Toast.LENGTH_SHORT).show();
            }
            EditText Focal_len_Et = findViewById(R.id.input_FocalLength);
            int Flength = Integer.parseInt(Focal_len_Et.getText().toString());
            if(Flength < 0){
                Toast.makeText(AddLens.this,"Focal length cannot be less than 12 or negative",Toast.LENGTH_SHORT).show();
            }
            if(Iconid == 0){
                Toast.makeText(AddLens.this,"Select a lens icon",Toast.LENGTH_SHORT).show();
            }
            if(make_Et.getText().toString().isEmpty()||max_aperature_Et.getText().toString().isEmpty()||Focal_len_Et.getText().toString().isEmpty()||Iconid == 0) {
                Toast.makeText(AddLens.this,"ERROR: Check input!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddLens.this, "Lens Saved!", Toast.LENGTH_SHORT).show();
                lenses.add(new Lens(Make,Aperature,Flength,Iconid));
            }
            Intent intent = new Intent();
            intent.putExtra("Name of the lens1", Make);
            intent.putExtra("Aperture of the lens1", Aperature);
            intent.putExtra("Focal length of the lens1", Flength);
            setResult(Activity.RESULT_OK,intent);
            finish();
        });
    }

    private void selectIcon() {
        ImageButton btn_icon1 = (ImageButton) findViewById(R.id.img_icon1);
        btn_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddLens.this,"Selected Icon 1",Toast.LENGTH_SHORT).show();
                Iconid = iconIDs[0];
            }
        });
        ImageButton btn_icon2 = (ImageButton) findViewById(R.id.img_icon2);
        btn_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddLens.this,"Selected Icon 2",Toast.LENGTH_SHORT).show();
                Iconid = iconIDs[1];
            }
        });
        ImageButton btn_icon3 = (ImageButton) findViewById(R.id.img_icon3);
        btn_icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddLens.this,"Selected Icon 3",Toast.LENGTH_SHORT).show();
                Iconid = iconIDs[2];
            }
        });
        ImageButton btn_icon4 = (ImageButton) findViewById(R.id.img_icon4);
        btn_icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddLens.this,"Selected Icon 4",Toast.LENGTH_SHORT).show();
                Iconid = iconIDs[3];
            }
        });
        ImageButton btn_icon5 = (ImageButton) findViewById(R.id.img_icon5);
        btn_icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddLens.this,"Selected Icon 5",Toast.LENGTH_SHORT).show();
                Iconid = iconIDs[4];
            }
        });
    }
    public  static  Intent makeLaunchIntent(Context context) {
        Intent intent = new Intent(context,AddLens.class);
        return  intent;
    }
}
