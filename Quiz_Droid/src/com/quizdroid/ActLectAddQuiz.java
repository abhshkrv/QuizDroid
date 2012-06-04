package com.quizdroid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.quizdroid.model.Quiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ActLectAddQuiz extends Activity {
ImageView imgAddQuiz;
EditText txtQuizCode;
EditText txtDescription;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lect_add_quiz);
        txtQuizCode = (EditText) findViewById(R.id.txtQuizCode);
        txtDescription = (EditText) findViewById(R.id.txtQuizDescription);
        imgAddQuiz = (ImageView)findViewById(R.id.imgAddQuiz);
        imgAddQuiz.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (txtQuizCode.getText().toString().trim().length() == 0){
					Toast.makeText(ActLectAddQuiz.this,"Quiz Code is mandatory",5000);
					return;
				}
				if (txtDescription.getText().toString().trim().length() == 0){
					Toast.makeText(ActLectAddQuiz.this,"Quiz Description is mandatory",5000);
					return;
				}
				Quiz objQuiz = new Quiz();
				objQuiz.setQuizCode(txtQuizCode.getText().toString());
				Intent myIntent = new Intent(ActLectAddQuiz.this,
						ActLectAddQuestion.class);
				myIntent.putExtra("quiz", serializeObject(objQuiz));
				myIntent.putExtra("strValue", "value_ok");
				startActivity(myIntent);
			}
		});
    }
    
    public static byte[] serializeObject(Object o) { 
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
     
        try { 
          ObjectOutput out = new ObjectOutputStream(bos); 
          out.writeObject(o); 
          out.close(); 
     
          // Get the bytes of the serialized object 
          byte[] buf = bos.toByteArray(); 
     
          return buf; 
        } catch(IOException ioe) { 
          Log.e("serializeObject", "error", ioe); 
     
          return null; 
        } 
      } 

}
