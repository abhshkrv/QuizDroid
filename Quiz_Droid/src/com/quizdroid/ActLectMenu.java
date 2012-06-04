package com.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ActLectMenu extends Activity {
ImageView imgAddQuiz;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lect_main);
        imgAddQuiz = (ImageView)findViewById(R.id.imgAddQuiz);
        imgAddQuiz.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(ActLectMenu.this,
						ActLectAddQuiz.class);
				startActivity(myIntent);
			}
		});
    }
    
}
