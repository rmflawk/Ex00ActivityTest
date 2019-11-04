package com.youngstudio.ex00activitytest;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    EditText et_name;
    EditText et_nikname;
    EditText et_title;
    EditText et_main;

    TextView tv;

    String dbName="Data.db"; //database파일명
    String tableName="member"; //표 이름

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et_name= findViewById(R.id.et_name);
        et_nikname= findViewById(R.id.et_nikname);
        et_title= findViewById(R.id.et_title);
        et_main= findViewById(R.id.et_main);
        tv= findViewById(R.id.tv);



        //dbName으로 데이터베이스 파일 생성 또는 열기
        db= this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        //만들어진 DB파일에 "member"라는 이름으로 테이블 생성 작업 수행
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + "(num integer primary key autoincrement, name text not null,nikname text, title text, main text);");

        //제목줄(ActionBar) 객체를 얻어오기
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Second Activity");


    }

    public void clickBtn(View view) {
        //완료버튼을 누르면
        //EditText에  써있는 글씨를 얻어와서
        //Intent에게 추가시키고 Activity 종료
        String name= et_name.getText().toString();
        String nikname= et_nikname.getText().toString();
        String title= et_title.getText().toString();
        String main= et_main.getText().toString();

        //데이터베이스에 데이터를 삽입(insert)하는 쿼리문 실행
        db.execSQL("INSERT INTO "+ tableName+"(name, nikname, title, main) VALUES('"+name+"','"+nikname+"','"+title+"','"+main+"')");


        AlertDialog dialog = new AlertDialog.Builder(this).setMessage("작성을 완료하시겠습니까?")
                .setNegativeButton("아니오", null).setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str_name = et_name.getText().toString();
                String str_nik = et_nikname.getText().toString();
                String str_title = et_title.getText().toString();
                String str_main = et_main.getText().toString();

                //데이터를 복귀할 Intent에게 추가하기
                Intent intent = getIntent();
                intent.putExtra("name", str_name);
                intent.putExtra("nik", str_nik);
                intent.putExtra("title", str_title);
                intent.putExtra("main", str_main);

                //이 액티비티의 결과라고 설정!!
                setResult(RESULT_OK, intent);


                //액티비티종료
                finish();
            }
        }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    public void clickBtn2(View view) {
        AlertDialog dialog= new AlertDialog.Builder(this).setMessage("정말로 취소하시겠습니까?")
                .setNegativeButton("아니오", null).setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create();

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public void clickBtn3(View view) {

        Cursor cursor= db.rawQuery("SELECT * FROM "+tableName, null);//두번째 파라미터 : 검색조건
        // Cursor객체 : 결과 table을 참조하는
        // 객체 Cursor객체를 통해 결과 table의 값들을 읽어오는 것임.
        if(cursor==null) return;

        StringBuffer buffer= new StringBuffer();

        while ( cursor.moveToNext() ){
            int num= cursor.getInt(0);
            String name= cursor.getString(1);
            String nikname= cursor.getString(2);
            String title= cursor.getString(3);
            String main= cursor.getString(4);

            buffer.append(num+"  "+name+"  "+nikname+"  "+title+"  "+main+"\n");
        }

        //데이터를 복귀할 Intent에게 추가하기
        Intent intent = getIntent();
        intent.putExtra("name", buffer.toString());

       //Page3ListAdapter p = new Page3ListAdapter(buffer.toString());

        //이 액티비티의 결과라고 설정!!
        setResult(RESULT_OK, intent);

        //액티비티종료
        finish();
        //new AlertDialog.Builder(this).setMessage(buffer.toString()).setPositiveButton("OK", null).create().show();
    }


    public void clickBtn4(View view) {
        String name= et_name.getText().toString();
        db.execSQL("DELETE FROM "+tableName+" WHERE name=?",new String[]{name});

    }


}//Second Activity

