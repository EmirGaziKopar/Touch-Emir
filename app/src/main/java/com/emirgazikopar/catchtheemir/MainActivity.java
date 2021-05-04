package com.emirgazikopar.catchtheemir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    TextView textView2;
    TextView textView;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView10;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int var =0;
    long second = 0;
    int tmp = 0;
    SharedPreferences shared;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView10 = findViewById(R.id.imageView10);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);


        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView10,imageView7,imageView8,imageView9,imageView10}; //Bu şekilde süslü parantez açarsak istediğimiz elemanları yazabiliriz
        hide();
        SharedPreferences shared = this.getSharedPreferences("com.emirgazikopar.catchtheemir",MODE_PRIVATE);

        tmp = shared.getInt("anahtar",0);

        textView2.setText("skore: "+tmp);

        CountDownTimer start = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Left: " + millisUntilFinished / 1000);
                second = (millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                makeText(getApplicationContext(), "Your score:"+score, LENGTH_LONG).show();
                textView.setText("Finished!");
                handler.removeCallbacks(runnable);
                imageView.setEnabled(false);
                imageView2.setEnabled(false);
                imageView3.setEnabled(false);
                imageView4.setEnabled(false);
                imageView5.setEnabled(false);
                imageView10.setEnabled(false);
                imageView7.setEnabled(false);
                imageView8.setEnabled(false);
                imageView9.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setCancelable(false);//code to block pressing outside of the dialog box
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);


                    }

                });
                alert.setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        makeText(MainActivity.this,"Yazıklar Olsun", LENGTH_LONG).show();
                        var =1;
                    }

                });

                alert.show();

            }


        }.start();


    }
    public void tekrar (View view){
        if (var==1){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    public void skor(View view){

        score++;
        textView2.setText("score:"+score);

            if(second<=1){
                shared.edit().putInt("anahtar",score).apply();
            }




    }
    public void hide(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,300);
            }
            
         // bu kodla dizideki butun elemanlar görünmez oldu
        }; //ImageView den image tanımladık Burada image ile istediğimiz elemanı çağırabiliriz
        handler.post(runnable);
    }
}