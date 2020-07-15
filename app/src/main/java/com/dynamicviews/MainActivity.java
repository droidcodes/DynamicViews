package com.dynamicviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmitList;

    List<String> teamList = new ArrayList<>();
    ArrayList<Cricketer> cricketersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitList = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSubmitList.setOnClickListener(this);


        teamList.add("Team");
        teamList.add("India");
        teamList.add("Australia");
        teamList.add("England");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_add:

                addView();

                break;

            case R.id.button_submit_list:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(MainActivity.this,ActivityCricketers.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",cricketersList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        cricketersList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View cricketerView = layoutList.getChildAt(i);

            EditText editTextName = (EditText)cricketerView.findViewById(R.id.edit_cricketer_name);
            AppCompatSpinner spinnerTeam = (AppCompatSpinner)cricketerView.findViewById(R.id.spinner_team);

            Cricketer cricketer = new Cricketer();

            if(!editTextName.getText().toString().equals("")){
                cricketer.setCricketerName(editTextName.getText().toString());
            }else {
                result = false;
                break;
            }

            if(spinnerTeam.getSelectedItemPosition()!=0){
                cricketer.setTeamName(teamList.get(spinnerTeam.getSelectedItemPosition()));
            }else {
                result = false;
                break;
            }

            cricketersList.add(cricketer);

        }

        if(cricketersList.size()==0){
            result = false;
            Toast.makeText(this, "Add Cricketers First!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {

        final View cricketerView = getLayoutInflater().inflate(R.layout.row_add_cricketer,null,false);

        EditText editText = (EditText)cricketerView.findViewById(R.id.edit_cricketer_name);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner)cricketerView.findViewById(R.id.spinner_team);
        ImageView imageClose = (ImageView)cricketerView.findViewById(R.id.image_remove);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,teamList);
        spinnerTeam.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
        });

        layoutList.addView(cricketerView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}
