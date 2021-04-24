package com.sedatepace.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sedatepace.sqlite.db.MyDBHelper;

public class MainActivity extends AppCompatActivity {
    MyDBHelper myDBHelper;
    EditText edtName, edtSubject, edtScore;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName     = (EditText)findViewById(R.id.editView1);
        edtSubject  = (EditText)findViewById(R.id.editView2);
        edtScore    = (EditText)findViewById(R.id.editView3);

        txtResult = (TextView)findViewById(R.id.txtView2);

        btnInit = (Button)findViewById(R.id.btn1);
        btnInsert = (Button)findViewById(R.id.btn2);
        btnSelect = (Button)findViewById(R.id.btn3);

        //MyDBHelper 초기화
        myDBHelper = new MyDBHelper(this);

        //초기화 버튼 클릭 이벤트
        btnInit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();

                txtResult.setText("");
                edtName.setText("");
                edtSubject.setText("");
                edtScore.setText("");
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                String name = edtName.getText().toString();
                String subject = edtSubject.getText().toString();
                String score = edtScore.getText().toString();

                String sql = "INSERT INTO sdb VALUES("
                        + null +", '"
                        + name + "', '"
                        + subject + "', "
                        + score +");";
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL(sql);
                sqlDB.close();
                Toast.makeText(getApplicationContext(), name+", "+subject+", "+score+" 저장완료", Toast.LENGTH_SHORT).show();
                sqlDB.close();

                InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM sdb",null);

                String Title = "성적 결과 내용";
                String list ="";

                while(cursor.moveToNext()){
                    list += cursor.getString(0) +", "+ cursor.getString(1) +", "+ cursor.getString(2)+", "+cursor.getString(3) + " \r\n";
                }
                Log.e("결과", list);
                txtResult.setText(list);
                cursor.close();
                sqlDB.close();
            }
        });

    }
}