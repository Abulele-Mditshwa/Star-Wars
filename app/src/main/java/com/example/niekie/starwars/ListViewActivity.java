package com.example.niekie.starwars;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import static com.example.niekie.starwars.MainActivity.NewHope;


public class ListViewActivity extends AppCompatActivity {

public static TextView txtdata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        txtdata = (TextView)findViewById(R.id.textView2); //XML reference



        if (getIntent()!=null && getIntent().hasExtra(
                Intent.EXTRA_TEXT)) { txtdata.setText(getIntent().getStringExtra(
                Intent.EXTRA_TEXT));
            txtdata.setText(NewHope);
        }



        }//end onCreate method









    }//ends AppCompatActivity

