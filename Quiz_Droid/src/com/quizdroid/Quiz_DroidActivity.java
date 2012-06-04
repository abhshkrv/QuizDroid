package com.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz_DroidActivity extends Activity {
	Button btnLecturer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnLecturer = (Button)findViewById(R.id.btnLecturer);
        btnLecturer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(Quiz_DroidActivity.this,
						ActLectMenu.class);
				startActivity(myIntent);
			}
		});
    }
    
}