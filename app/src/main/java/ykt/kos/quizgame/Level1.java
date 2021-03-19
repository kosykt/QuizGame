package ykt.kos.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;

    public int numLeft;//переменная для левой картинки + текст
    public int numRight;//переменная для правой картинки + текст
    Array array = new Array();//создание нового обьекта из класса Array
    Random random = new Random();//генератор случайных чисел
    public int count = 0;//счетчик правильных ответов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создать переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);//устанаить текст

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //код округляющий углы картинки справа
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //код округляющий углы картинки слева
        img_right.setClipToOutline(true);


        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        //развернуть иугру во весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //развернуть иугру во весь экран - конец

        //вызов диалогового окна в начале игры
        dialog = new Dialog(this);//создать новое диалоговое окна
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрыть заголовок
        dialog.setContentView(R.layout.previewdialog);//путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой назад

        //закрыть диалоговое окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.buttonclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            //обрабатывание нажатия кнопки - начало
            @Override
            public void onClick(View v) {
                try {
                    //вернуться к выбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class);//создать намарение перехода
                    startActivity(intent);//старт намарения
                    finish();//закрыть этот класс
                    //вернуться к выбору уровня - начало
                }catch (Exception e){
                    //кода нет
                }
                dialog.dismiss();
            //обрабатывание нажатия кнопки - конец
            }
        });
        //закрыть диалоговое окно - конец

        //кнопка ПРОДОЛЖИТЬ - начало
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }//закрыть диалоговое окна
        });
        //кнопка ПРОДОЛЖИТЬ - конец

        dialog.show();//показать диалоговое окно

        //кнопка НАЗАД - начало
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //борабатывание кнопки НАЗАД - начало
                try {
                    //вернуться назат к квыбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent); //старт намерения
                    finish(); //закрыть этот класс
                    //вернуться назад к выбору уровня -конец
                }catch (Exception e){
                    //здесь кода не будет
                }
                //обрабатывание кнопки НАЗАД - конец
            }
        });
        //кнопка НАЗАД конец

        //массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
                R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,
        };
        //массив для прогресса игры - конец

        //подключение анимации - начало
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
        //подключение анимации - конец

        numLeft = random.nextInt(10); //генерация случайного числа от 0 до 10
        img_left.setImageResource(array.images1[numLeft]); //достать из массива картинку
        text_left.setText(array.text1[numLeft]); //достать из массива текст

        numRight = random.nextInt(10);//генерация случайного числа от 0 до 10
        //цикл не допускающий равенства чисел - начало
        while (numRight == numLeft){
            numRight = random.nextInt(10);
        }
        //цикл не допускающий равенство чисел - конец
        img_right.setImageResource(array.images1[numRight]);//достать из массива картинку
        text_right.setText(array.text1[numRight]);//достать из массива текст

        //обработать нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    //если коснутся картинки - начало
                    img_right.setEnabled(false);//блокирование правой картинки
                    if (numLeft > numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //если коснутся картинки - конец
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    //если отпустил палец - начало
                    if (numLeft > numRight){
                        //если левая картинка больше
                        if (count < 20){
                            count += 1;
                        }
                        // закрашивание ячеек в линии пргресса в серый - начало
                        for (int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // закрашивание ячеек в линии пргресса в серый - конец

                        //определить правильный ответ и закрасить в зеленый - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //определить правильный ответ и закрасить в зеленый - конец
                    }else {
                        //если левая картинка меньше
                    }
                    //если отпустил палец - конец
                }
                //условие касания картинки - конец
                return true;
            }
        });
        //обработать нажатие на левую картинку - конец
    }
    //системная кнопка НАЗАД - начало
    @Override
    public void onBackPressed(){
        //обрабатывание нажатия системной кнопки НАЗАД - начало
        try {
            //вернутся назад к выберу уровня - начало
            Intent intent = new Intent(Level1.this, GameLevels.class);//создание намерения перехода
            startActivity(intent);//старт намерения
            finish();//закрыть этот класс
        }catch (Exception e){

        }
        //обрабатывание нажатия системной кнопки НАЗАД - конец
    }
    //системная кнопка НАЗАД - конец
}