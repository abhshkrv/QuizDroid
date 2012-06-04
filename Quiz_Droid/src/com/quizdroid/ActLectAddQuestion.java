package com.quizdroid;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.quizdroid.model.Quiz;
import com.quizdroid.model.QuizOption;
import com.quizdroid.model.QuizQuestion;

import android.R.layout;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ActLectAddQuestion extends Activity implements OnClickListener {
	ImageView imgAddQuiz;
	ImageView imgNext;
	ImageView imgBack;
	TextView txtQuizCode;
	TextView txtQuizNo;
	EditText txtQuizDescription;
	Quiz objQuiz;
	int i = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_lect_add_question);
		Bundle extras = getIntent().getExtras();
		String strValue = extras.getString("strValue");

		objQuiz = (Quiz) deserializeObject(extras.getByteArray("quiz"));

		txtQuizCode = (TextView) findViewById(R.id.txtQuizCode);
		txtQuizNo = (TextView) findViewById(R.id.txtQuestionNo);
		txtQuizDescription = (EditText) findViewById(R.id.txtQuizDescription);

		imgAddQuiz = (ImageView) findViewById(R.id.imgAddQuiz);
		imgNext = (ImageView) findViewById(R.id.imgNext);
		imgBack = (ImageView) findViewById(R.id.imgBack);
		imgAddQuiz.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				i = i + 1;
				LinearLayout layoutQuestion = (LinearLayout) findViewById(R.id.layoutQuestion);
				LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.FILL_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				LinearLayout newLayout = new LinearLayout(
						ActLectAddQuestion.this);
				newLayout.setId(i);
				newLayout.setLayoutParams(layoutParams);
				newLayout.setOrientation(LinearLayout.HORIZONTAL);
				
				QuizOption objOption = new QuizOption();
				objOption.setIptionId(i);
				objOption.setOptionName("");
				objQuiz.getActiveQuestion().addAnswerOption(objOption);

				EditText txtNewOption = new EditText(ActLectAddQuestion.this);
				txtNewOption.setText("sample");
				txtNewOption.setId(i);
				
				txtNewOption.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT, 3));
				newLayout.addView(txtNewOption);

				Button btnDel = new Button(ActLectAddQuestion.this);
				btnDel.setText("Del");
				btnDel.setId(i);
				btnDel.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT, 1));
				btnDel.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						LinearLayout layoutQuestion = (LinearLayout) findViewById(R.id.layoutQuestion);
						LinearLayout layoutOption = (LinearLayout) findViewById(v
								.getId());
						layoutQuestion.removeView(layoutOption);
						for (int i = 0 ; i < objQuiz.getActiveQuestion().getAnswersOptions().toArray().length; i ++)
						{
							if ( ((QuizOption)objQuiz.getActiveQuestion().getAnswersOptions().toArray()[i]).getIptionId() == v.getId() )
							{
								objQuiz.getActiveQuestion().getAnswersOptions().remove(i);
							
							}
						}

					}
				}

				);
				newLayout.addView(btnDel);
				
				Button imgSel = new Button (ActLectAddQuestion.this);
				Bitmap image = BitmapFactory.decodeResource(ActLectAddQuestion.this.getResources(), R.drawable.untick);  
				//imgSel.setImageBitmap(image);
				imgSel.setText("");
				//imgSel.setImageResource(R.drawable.tick);
				imgSel.setId(100+i);
				imgSel.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT, 1));
				btnDel.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Button selButton = (Button) findViewById(v
								.getId());
						
					}
				}

				);
				newLayout.addView(imgSel);
				layoutQuestion.addView(newLayout);

			}
		});

		imgNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveQuizOptions();
				if (txtQuizDescription.getText().toString().trim().length() == 0) {
					return;
				} else {
					objQuiz.getActiveQuestion().setQuestionName(
							txtQuizDescription.getText().toString());
				}
				for (int i = 0; i < objQuiz.getQuestions().size(); i++) {
					if (((QuizQuestion) objQuiz.getQuestions().toArray()[i])
							.getQuestionId() == objQuiz.getActiveQuestion()
							.getQuestionId()) {
						if (i != objQuiz.getQuestions().size() - 1) {
							objQuiz.setActiveQuestion((QuizQuestion) objQuiz
									.getQuestions().toArray()[i + 1]);
							fillScreen();
							return;
						} else {
							QuizQuestion objQuestion = new QuizQuestion();
							objQuestion.setQuestionName(txtQuizDescription
									.getText().toString());
							objQuestion.setQuestionId(objQuiz.getQuestions()
									.size() + 1);
							objQuiz.addQuestion(objQuestion);
							objQuiz.setActiveQuestion(objQuestion);
							fillScreen();
							return;
						}
					}
				}

			}
		});

		imgBack.setOnClickListener(new View.OnClickListener() {
			
			@Override			
			public void onClick(View v) {
				saveQuizOptions();
				if (txtQuizDescription.getText().toString().trim().length() == 0) {
					
				} else {
					objQuiz.getActiveQuestion().setQuestionName(
							txtQuizDescription.getText().toString());
				}
				// TODO Auto-generated method stub
				for (int i = 0; i < objQuiz.getQuestions().size(); i++) {
					if (((QuizQuestion) objQuiz.getQuestions().toArray()[i])
							.getQuestionId() == objQuiz.getActiveQuestion()
							.getQuestionId()) {
						if (i != 0)
							objQuiz.setActiveQuestion((QuizQuestion) objQuiz
									.getQuestions().toArray()[i - 1]);
						fillScreen();
						return;
					}
				}
			}
		});

		fillScreen();
	}

	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imgAddQuiz:
			i = i + 1;
			LinearLayout layoutQuestion = (LinearLayout) findViewById(R.id.layoutQuestion);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			EditText txtNewOption = new EditText(ActLectAddQuestion.this);
			txtNewOption.setText("sample");
			txtNewOption.setId(i);
			txtNewOption.setLayoutParams(params);
		}

	}

	public static Object deserializeObject(byte[] b) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(b));
			Object object = in.readObject();
			in.close();

			return object;
		} catch (ClassNotFoundException cnfe) {
			Log.e("deserializeObject", "class not found error", cnfe);

			return null;
		} catch (IOException ioe) {
			Log.e("deserializeObject", "io error", ioe);

			return null;
		}
	}

	public void fillScreen() {

		txtQuizCode.setText(objQuiz.getQuizCode());

		if (objQuiz.getQuestions() == null) {
			txtQuizNo.setText("1 of 1");
			QuizQuestion objQuestion = new QuizQuestion();
			objQuestion.setQuestionName("");
			objQuestion.setQuestionId(1);
			objQuiz.addQuestion(objQuestion);
			objQuiz.setActiveQuestion(objQuestion);
		} else {
			if (objQuiz.getActiveQuestion() != null) {
				txtQuizNo.setText(objQuiz.getActiveQuestion().getQuestionId()
						+ " of " + objQuiz.getQuestions().size());
				txtQuizDescription.setText(objQuiz.getActiveQuestion()
						.getQuestionName());
			}

		}
		if (objQuiz.getActiveQuestion() != null) {
			txtQuizDescription.setText(objQuiz.getActiveQuestion()
					.getQuestionName());
			loadQuizOptions();
		}
	}
	
	public void saveQuizOptions()
	{
		for (int i = 0 ; i < objQuiz.getActiveQuestion().getAnswersOptions().toArray().length; i ++)
		{
			EditText txtOptionName = (EditText) findViewById(((QuizOption)objQuiz.getActiveQuestion().getAnswersOptions().toArray()[i]).getIptionId());
			((QuizOption)objQuiz.getActiveQuestion().getAnswersOptions().toArray()[i]).setOptionName(txtOptionName.getText().toString());
			
		}
	}
	
	public void loadQuizOptions()
	{
		if (objQuiz.getActiveQuestion().getAnswersOptions()== null)
			return;
		for (int i = 0 ; i < objQuiz.getActiveQuestion().getAnswersOptions().toArray().length; i ++)
		{
			EditText txtOptionName = (EditText) findViewById(((QuizOption)objQuiz.getActiveQuestion().getAnswersOptions().toArray()[i]).getIptionId());
			((QuizOption)objQuiz.getActiveQuestion().getAnswersOptions().toArray()[i]).setOptionName(txtOptionName.getText().toString());
			
			LinearLayout layoutQuestion = (LinearLayout) findViewById(R.id.layoutQuestion);
			LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			LinearLayout newLayout = new LinearLayout(
					ActLectAddQuestion.this);
			newLayout.setId(i+1);
			newLayout.setLayoutParams(layoutParams);
			newLayout.setOrientation(LinearLayout.HORIZONTAL);
			
			QuizOption objOption = new QuizOption();
			objOption.setIptionId(i);
			objOption.setOptionName("");
			objQuiz.getActiveQuestion().addAnswerOption(objOption);

			EditText txtNewOption = new EditText(ActLectAddQuestion.this);
			txtNewOption.setText(((QuizOption)objQuiz.getActiveQuestion().getAnswersOptions().toArray()[i]).getOptionName());
			txtNewOption.setId(i);
			
			txtNewOption.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT, 3));
			newLayout.addView(txtNewOption);

			Button btnDel = new Button(ActLectAddQuestion.this);
			btnDel.setText("Del");
			btnDel.setId(i);
			btnDel.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT, 1));
			btnDel.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					LinearLayout layoutQuestion = (LinearLayout) findViewById(R.id.layoutQuestion);
					LinearLayout layoutOption = (LinearLayout) findViewById(v
							.getId());
					layoutQuestion.removeView(layoutOption);
					for (int i = 0 ; i < objQuiz.getActiveQuestion().getAnswersOptions().toArray().length; i ++)
					{
						if ( ((QuizOption)objQuiz.getActiveQuestion().getAnswersOptions().toArray()[i]).getIptionId() == v.getId() )
						{
							objQuiz.getActiveQuestion().getAnswersOptions().remove(i);
						
						}
					}

				}
			}

			);
			newLayout.addView(btnDel);
			
			Button imgSel = new Button (ActLectAddQuestion.this);
			Bitmap image = BitmapFactory.decodeResource(ActLectAddQuestion.this.getResources(), R.drawable.untick);  
			//imgSel.setImageBitmap(image);
			imgSel.setText("");
			//imgSel.setImageResource(R.drawable.tick);
			imgSel.setId(100+i);
			imgSel.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT, 1));
			btnDel.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Button selButton = (Button) findViewById(v
							.getId());
					
				}
			}

			);
			newLayout.addView(imgSel);
			layoutQuestion.addView(newLayout);

			
		}
	}
}
