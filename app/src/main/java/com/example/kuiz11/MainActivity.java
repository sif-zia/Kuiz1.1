package com.example.kuiz11;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import kotlin.reflect.KFunction;

public class MainActivity extends AppCompatActivity {
    int crrIndex = 0;
    List<Question> selectedQuestions;
    String mode = "Play";
    int noOfQuestions = 20;
    int posPoint = 5;
    int negPoint = 1;
    long timeLimit = 10 * 60 * 1000;
    private long timeLeftInMillis = timeLimit;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String TAG = new String("MainActivityKuiz");

        ArrayList<Question> questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of Pakistan?", new String[]{"Karachi", "Lahore", "Islamabad", "Quetta"}, "Islamabad"));
        questionList.add(new Question("Who is known as the Father of the Nation in Pakistan?", new String[]{"Liaquat Ali Khan", "Allama Iqbal", "Quaid-e-Azam", "Zulfikar Ali Bhutto"}, "Quaid-e-Azam"));
        questionList.add(new Question("Which river is the longest in Pakistan?", new String[]{"Indus", "Jhelum", "Ravi", "Chenab"}, "Indus"));
        questionList.add(new Question("When did Pakistan become an independent country?", new String[]{"1940", "1947", "1956", "1965"}, "1947"));
        questionList.add(new Question("What is the national language of Pakistan?", new String[]{"Urdu", "Punjabi", "Sindhi", "Pashto"}, "Urdu"));
        questionList.add(new Question("Who was the first Prime Minister of Pakistan?", new String[]{"Liaquat Ali Khan", "Zulfikar Ali Bhutto", "Benazir Bhutto", "Ayub Khan"}, "Liaquat Ali Khan"));
        questionList.add(new Question("What is the highest civilian award in Pakistan?", new String[]{"Nishan-e-Pakistan", "Hilal-e-Imtiaz", "Sitara-e-Jurat", "Tamgha-e-Shujaat"}, "Nishan-e-Pakistan"));
        questionList.add(new Question("What is the national sport of Pakistan?", new String[]{"Cricket", "Hockey", "Football", "Kabaddi"}, "Hockey"));
        questionList.add(new Question("Which city is known as the 'City of Lights'?", new String[]{"Lahore", "Karachi", "Islamabad", "Peshawar"}, "Karachi"));
        questionList.add(new Question("In which year was the Lahore Resolution passed?", new String[]{"1930", "1940", "1947", "1956"}, "1940"));
        questionList.add(new Question("Which is the largest province of Pakistan by area?", new String[]{"Punjab", "Sindh", "Khyber Pakhtunkhwa", "Balochistan"}, "Balochistan"));
        questionList.add(new Question("Who wrote Pakistan's national anthem?", new String[]{"Allama Iqbal", "Faiz Ahmed Faiz", "Hafeez Jalandhari", "Ahmed Faraz"}, "Hafeez Jalandhari"));
        questionList.add(new Question("Which mountain is the highest peak in Pakistan?", new String[]{"K2", "Nanga Parbat", "Broad Peak", "Gasherbrum I"}, "K2"));
        questionList.add(new Question("Which sea lies to the south of Pakistan?", new String[]{"Mediterranean Sea", "Arabian Sea", "Caspian Sea", "Red Sea"}, "Arabian Sea"));
        questionList.add(new Question("Which is the most populous city in Pakistan?", new String[]{"Karachi", "Lahore", "Faisalabad", "Rawalpindi"}, "Karachi"));
        questionList.add(new Question("Who is known as the Poet of the East?", new String[]{"Faiz Ahmed Faiz", "Mirza Ghalib", "Allama Iqbal", "Ahmed Faraz"}, "Allama Iqbal"));
        questionList.add(new Question("What is the official currency of Pakistan?", new String[]{"Dollar", "Rupee", "Taka", "Dinar"}, "Rupee"));
        questionList.add(new Question("Which is the largest desert in Pakistan?", new String[]{"Thar", "Cholistan", "Kharan", "Thal"}, "Thar"));
        questionList.add(new Question("Which Pakistani scientist won the Nobel Prize in Physics?", new String[]{"Abdul Sattar Edhi", "Malala Yousafzai", "Abdus Salam", "Pervez Hoodbhoy"}, "Abdus Salam"));
        questionList.add(new Question("In which year did Pakistan conduct its first nuclear tests?", new String[]{"1974", "1998", "2001", "2005"}, "1998"));
        questionList.add(new Question("Which city is known as the 'Heart of Pakistan'?", new String[]{"Karachi", "Islamabad", "Lahore", "Peshawar"}, "Lahore"));
        questionList.add(new Question("Who was the first female Prime Minister of Pakistan?", new String[]{"Benazir Bhutto", "Fatima Jinnah", "Asma Jahangir", "Malala Yousafzai"}, "Benazir Bhutto"));
        questionList.add(new Question("What is the national animal of Pakistan?", new String[]{"Lion", "Tiger", "Markhor", "Elephant"}, "Markhor"));
        questionList.add(new Question("Which ocean borders Pakistan?", new String[]{"Indian Ocean", "Pacific Ocean", "Atlantic Ocean", "Arabian Sea"}, "Arabian Sea"));
        questionList.add(new Question("Who is the current Prime Minister of Pakistan (2024)?", new String[]{"Imran Khan", "Anwar ul Haq Kakar", "Nawaz Sharif", "Shehbaz Sharif"}, "Shehbaz Sharif"));
        questionList.add(new Question("Which is the largest dam in Pakistan?", new String[]{"Tarbela Dam", "Mangla Dam", "Warsak Dam", "Diamer-Bhasha Dam"}, "Tarbela Dam"));
        questionList.add(new Question("What is the national flower of Pakistan?", new String[]{"Rose", "Sunflower", "Tulip", "Jasmine"}, "Jasmine"));
        questionList.add(new Question("When was the Constitution of Pakistan first enacted?", new String[]{"1956", "1962", "1973", "1985"}, "1973"));
        questionList.add(new Question("Which is the largest mosque in Pakistan?", new String[]{"Badshahi Mosque", "Faisal Mosque", "Shah Jahan Mosque", "Grand Jamia Mosque"}, "Faisal Mosque"));
        questionList.add(new Question("Which Pakistani city is known for the Mohenjo-Daro archaeological site?", new String[]{"Larkana", "Quetta", "Multan", "Sukkur"}, "Larkana"));

        Collections.shuffle(questionList);


        int selectedColor = ContextCompat.getColor(this, R.color.purple_500);
        int unSelectedColor = ContextCompat.getColor(this, R.color.cyan);

        selectedQuestions = questionList.subList(0, noOfQuestions);
        ArrayList<String> selectedAnswers = new ArrayList<>(Collections.nCopies(noOfQuestions, ""));


        Button option1Btn = findViewById(R.id.opt1);
        Button option2Btn = findViewById(R.id.opt2);
        Button option3Btn = findViewById(R.id.opt3);
        Button option4Btn = findViewById(R.id.opt4);
        Button nxtButton = findViewById(R.id.nxt);
        Button prvButton = findViewById(R.id.prv);
        Button sbmButton = findViewById(R.id.sbm);
        Button srtButton = findViewById(R.id.srt);
        TextView subHeadingTextView = findViewById(R.id.question);
        TextView paraTextView = findViewById(R.id.gameScore);
        LinearLayout optRow1 = findViewById(R.id.optRow1);
        LinearLayout optRow2 = findViewById(R.id.optRow2);
        LinearLayout navRow = findViewById(R.id.navRow);
        LinearLayout sbmRow = findViewById(R.id.sbmRow);
        LinearLayout srtRow = findViewById(R.id.srtRow);

        optRow1.setVisibility(View.INVISIBLE);
        optRow2.setVisibility(View.INVISIBLE);
        navRow.setVisibility(View.INVISIBLE);
        sbmRow.setVisibility(View.INVISIBLE);

        srtButton.setOnClickListener(view -> {
            if(Objects.equals(srtButton.getText(), "Start")) {
                optRow1.setVisibility(View.VISIBLE);
                optRow2.setVisibility(View.VISIBLE);
                navRow.setVisibility(View.VISIBLE);
                srtRow.setVisibility(View.INVISIBLE);

                subHeadingTextView.setText("Timer");
                startTimer();

                loadQuestion(selectedQuestions.get(crrIndex), selectedAnswers.get(crrIndex));
                updateIndex(crrIndex);
            }
            else if(Objects.equals(srtButton.getText(), "See Answers")) {
                optRow1.setVisibility(View.VISIBLE);
                optRow2.setVisibility(View.VISIBLE);
                navRow.setVisibility(View.VISIBLE);

                srtRow.setVisibility(View.INVISIBLE);

                resetBtns(option1Btn, option2Btn, option3Btn, option4Btn);

                subHeadingTextView.setText("Answers");
                paraTextView.setTextSize(20);
                mode = "Check";

                crrIndex = 0;
                loadQuestion(selectedQuestions.get(crrIndex), selectedAnswers.get(crrIndex));
                updateIndex(crrIndex);
                prvButton.setEnabled(false);
                nxtButton.setEnabled(true);
            }
        });


        nxtButton.setOnClickListener(view -> {
            crrIndex++;
            prvButton.setEnabled(true);
            loadQuestion(selectedQuestions.get(crrIndex), selectedAnswers.get(crrIndex));
            updateIndex(crrIndex);
            if (crrIndex == noOfQuestions - 1) {
                nxtButton.setEnabled(false);
                sbmRow.setVisibility(View.VISIBLE);
            }
        });

        prvButton.setOnClickListener(view -> {
            crrIndex--;
            nxtButton.setEnabled(true);
            loadQuestion(selectedQuestions.get(crrIndex), selectedAnswers.get(crrIndex));
            updateIndex(crrIndex);
            if (crrIndex == 0)
                prvButton.setEnabled(false);
        });

        option1Btn.setOnClickListener(view -> {
            if(Objects.equals(mode, "Play")) {
                resetBtns(option1Btn, option2Btn, option3Btn, option4Btn);
                selectedAnswers.set(crrIndex, option1Btn.getText().toString());
                option1Btn.setBackgroundColor(selectedColor);
            }
        });

        option2Btn.setOnClickListener(view -> {
            if(Objects.equals(mode, "Play")) {
                resetBtns(option1Btn, option2Btn, option3Btn, option4Btn);
                selectedAnswers.set(crrIndex, option2Btn.getText().toString());
                option2Btn.setBackgroundColor(selectedColor);
            }
        });

        option3Btn.setOnClickListener(view -> {
            if(Objects.equals(mode, "Play")) {
                resetBtns(option1Btn, option2Btn, option3Btn, option4Btn);
                selectedAnswers.set(crrIndex, option3Btn.getText().toString());
                option3Btn.setBackgroundColor(selectedColor);
            }
        });

        option4Btn.setOnClickListener(view -> {
            if(Objects.equals(mode, "Play")) {
                resetBtns(option1Btn, option2Btn, option3Btn, option4Btn);
                selectedAnswers.set(crrIndex, option4Btn.getText().toString());
                option4Btn.setBackgroundColor(selectedColor);
            }
        });

        sbmButton.setOnClickListener(view -> {
            if(Objects.equals(sbmButton.getText(), "Submit")) {
                optRow1.setVisibility(View.INVISIBLE);
                optRow2.setVisibility(View.INVISIBLE);
                navRow.setVisibility(View.INVISIBLE);

                srtRow.setVisibility(View.VISIBLE);
                srtButton.setText("See Answers");
                countDownTimer.cancel();

                int score = 0;
                for(int i = 0; i < noOfQuestions; i++)
                {
                    if(!Objects.equals(selectedAnswers.get(i), "")) {
                        if (selectedQuestions.get(i).checkAnswer(selectedAnswers.get(i)))
                            score += posPoint;
                        else
                            score -= negPoint;
                    }
                    Log.d(TAG, String.format("Selected Answer: %s | Correct Answer: %s", selectedAnswers.get(i), questionList.get(i).getAnswer()));
                }
                paraTextView.setText(score + "/" + posPoint * noOfQuestions);
                paraTextView.setTextSize(64);

                String msg;
                if(score > 0.75 * posPoint * noOfQuestions)
                    msg = "Congratulation! You got a good score:";
                else if(score >= 0.4 * posPoint * noOfQuestions)
                    msg = "Nice Try! Your score is:";
                else
                    msg = "You need to improve your score! Your score is:";

                if(timeLeftInMillis > 0) {
                    int minutes = (int) (timeLeftInMillis / 1000) / 60;
                    int seconds = (int) (timeLeftInMillis / 1000) % 60;

                    String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                    msg = timeLeftFormatted + " time left! " + msg;
                }
                else {
                    msg = "Time's Up! ";
                }

                subHeadingTextView.setText(msg);

                sbmButton.setText("Replay");
            }
            else if(Objects.equals(sbmButton.getText(), "Replay")) {
                optRow1.setVisibility(View.VISIBLE);
                optRow2.setVisibility(View.VISIBLE);
                navRow.setVisibility(View.VISIBLE);

                sbmRow.setVisibility(View.INVISIBLE);
                srtRow.setVisibility(View.INVISIBLE);

                resetBtns(option1Btn, option2Btn, option3Btn, option4Btn);

                sbmButton.setText("Submit");
                paraTextView.setTextSize(20);
                subHeadingTextView.setText("Timer");
                startTimer();
                mode = "Play";

                for(int i = 0; i < noOfQuestions; i++)
                    selectedAnswers.set(i, "");
                Collections.shuffle(questionList);
                selectedQuestions = questionList.subList(0, noOfQuestions);

                crrIndex = 0;
                loadQuestion(selectedQuestions.get(crrIndex), selectedAnswers.get(crrIndex));
                updateIndex(crrIndex);
                prvButton.setEnabled(false);
                nxtButton.setEnabled(true);
            }
        });
    }

    private void resetBtns(Button option1Btn, Button option2Btn, Button option3Btn, Button option4Btn) {
        int unSelectedColor = ContextCompat.getColor(this, R.color.cyan);

        option1Btn.setBackgroundColor(unSelectedColor);
        option2Btn.setBackgroundColor(unSelectedColor);
        option3Btn.setBackgroundColor(unSelectedColor);
        option4Btn.setBackgroundColor(unSelectedColor);
    }

    private void updateIndex(int crrIndex) {
        TextView crrIndexTextView = findViewById(R.id.index);
        crrIndexTextView.setText((crrIndex + 1) + "/" + noOfQuestions);
    }

    private void loadQuestion(Question question, String selectedAnswer) {
        int selectedColor;
        if(Objects.equals(mode, "Play"))
            selectedColor = ContextCompat.getColor(this, R.color.purple_500);
        else
            selectedColor = ContextCompat.getColor(this, R.color.red);
        TextView questionTextView = findViewById(R.id.gameScore);
        Button option1Btn = findViewById(R.id.opt1);
        Button option2Btn = findViewById(R.id.opt2);
        Button option3Btn = findViewById(R.id.opt3);
        Button option4Btn = findViewById(R.id.opt4);

        resetBtns(option1Btn, option2Btn, option3Btn, option4Btn);

        questionTextView.setText(question.getStatement());
        option1Btn.setText(question.getOptions()[0]);
        option2Btn.setText(question.getOptions()[1]);
        option3Btn.setText(question.getOptions()[2]);
        option4Btn.setText(question.getOptions()[3]);

        if(Objects.equals(selectedAnswer, question.getOptions()[0]))
            option1Btn.setBackgroundColor(selectedColor);
        if(Objects.equals(selectedAnswer, question.getOptions()[1]))
            option2Btn.setBackgroundColor(selectedColor);
        if(Objects.equals(selectedAnswer, question.getOptions()[2]))
            option3Btn.setBackgroundColor(selectedColor);
        if(Objects.equals(selectedAnswer, question.getOptions()[3]))
            option4Btn.setBackgroundColor(selectedColor);

        if(Objects.equals(mode, "Check") && !Objects.equals(selectedAnswer, "")) {
            int correctColor = ContextCompat.getColor(this, R.color.green);
            if(Objects.equals(question.getAnswer(), question.getOptions()[0]))
                option1Btn.setBackgroundColor(correctColor);
            if(Objects.equals(question.getAnswer(), question.getOptions()[1]))
                option2Btn.setBackgroundColor(correctColor);
            if(Objects.equals(question.getAnswer(), question.getOptions()[2]))
                option3Btn.setBackgroundColor(correctColor);
            if(Objects.equals(question.getAnswer(), question.getOptions()[3]))
                option4Btn.setBackgroundColor(correctColor);
        }
        else if(Objects.equals(mode, "Check") && Objects.equals(selectedAnswer, "")) {
            int correctColor = ContextCompat.getColor(this, R.color.blue);
            if(Objects.equals(question.getAnswer(), question.getOptions()[0]))
                option1Btn.setBackgroundColor(correctColor);
            if(Objects.equals(question.getAnswer(), question.getOptions()[1]))
                option2Btn.setBackgroundColor(correctColor);
            if(Objects.equals(question.getAnswer(), question.getOptions()[2]))
                option3Btn.setBackgroundColor(correctColor);
            if(Objects.equals(question.getAnswer(), question.getOptions()[3]))
                option4Btn.setBackgroundColor(correctColor);
        }

    }

    private void startTimer() {
        timeLeftInMillis = timeLimit;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Button sbmButton = findViewById(R.id.sbm);
                sbmButton.callOnClick();
            }
        }.start();
    }

    private void updateTimer() {
        TextView timerTextView = findViewById(R.id.question);

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }


}