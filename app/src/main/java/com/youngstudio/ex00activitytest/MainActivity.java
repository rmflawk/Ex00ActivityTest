package com.youngstudio.ex00activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    MyAdapter adapter;
    ViewPager pager;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TabLayout tabLayout;

    ActionBarDrawerToggle drawerToggle;


    int p;

    ArrayList<String> datas= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btn= findViewById(R.id.btn);
        //액티비티에게 btn객체를 ContextMenu로 등록
        //this.registerForContextMenu(btn);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        pager= findViewById(R.id.pager);
        adapter= new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        drawerLayout= findViewById(R.id.layout_drawer);
        navigationView= findViewById(R.id.nav);

        //item icon색조를 적용하지 않도록..
        navigationView.setItemIconTintList(null);

        //드로우어 조절용 토글버튼 객체 생성
        drawerToggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.app_name,R.string.app_name);
        drawerToggle.syncState();//삼선메뉴
        drawerLayout.addDrawerListener(drawerToggle);//돌게만드는거

        //액션바에 제목이 자동표시 되지 않도록..
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tabLayout= findViewById(R.id.layout_tab);


        tabLayout.setupWithViewPager(pager);

        //제목줄에 서브제목설정하기
        getSupportActionBar().setSubtitle("Home");

        //탭변경 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSupportActionBar().setSubtitle(tab.getText());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch ( item.getItemId() ){
                    case R.id.menu_01: // 클릭게임
                        Intent intent1= new Intent();
                        intent1.setAction("click");
                        startActivity(intent1);
                        break;

                    case R.id.menu_02: // 숫자야구게임
                        Intent intent2= new Intent();
                        intent2.setAction("numbase");
                        startActivity(intent2);
                        break;

                    case R.id.menu_03: // 약국어플
                        Intent intent3= new Intent();
                        intent3.setAction("medi");
                        startActivity(intent3);
                        break;

                    case R.id.menu_04: // 스톱워치
                        Intent intent4= new Intent();
                        //intent2.setAction("click");
                        intent4.setAction("stopthread");
                        startActivity(intent4);
                        break;

                    case R.id.menu_05: // 스톱워치
                        Intent intent5= new Intent();
                        intent5.setAction("movie");
                        startActivity(intent5);
                        break;

                    case R.id.menu_06: // Pager
                        //Toast.makeText(MainActivity.this, "준비중입니다.", Toast.LENGTH_SHORT).show();
                        Intent intent6= new Intent();
                        intent6.setAction("pager");
                        startActivity(intent6);
                        break;
                    case R.id.menu_07: //
                        Toast.makeText(MainActivity.this, "준비중입니다.", Toast.LENGTH_SHORT).show();
//                        Intent intent7= new Intent();
//                        intent7.setAction("pager");
//                        startActivity(intent7);
                        break;
                    case R.id.menu_08: //
                        Toast.makeText(MainActivity.this, "준비중입니다.", Toast.LENGTH_SHORT).show();
//                        Intent intent8= new Intent();
//                        intent8.setAction("pager");
//                        startActivity(intent8);
                        break;
                }

                //Drawer를 닫기...
                drawerLayout.closeDrawer(navigationView);


                return false;
            }
        });


//        //리스트의 항목을 롱클릭하는 것을 듣는 리스너
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//                p = position;
//                AlertDialog dialog= new AlertDialog.Builder(MainActivity.this).setMessage("정말로 삭제하시겠습니까?")
//                        .setNegativeButton("아니오", null).setPositiveButton("네", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                datas.remove(p);
//                                adapter.notifyDataSetChanged();
//                            }
//                        }).create();
//
//                dialog.setCanceledOnTouchOutside(false);
//                dialog.show();
//                return true;
//            }
//        });
    }//oncreate




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //menu폴더안에 context.xml문서를 읽어와서
        //Menu객체를 만들어주는(부풀려주는 inflate) 객체를 Context로 부터 얻어오기
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.option, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }



    //컨텍스트메뉴의 아이템을 선택했을때
    //자동으로 실행되는 콜백메소드
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.menu_camera:
                //Toast.makeText(this,"search", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.menu_call:
                Intent intent2=new Intent(Intent.ACTION_DIAL);
                Uri uri= Uri.parse("tel:01012345678");
                intent2.setData(uri);
                startActivity(intent2);
                break;
        }
        return super.onContextItemSelected(item);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String str_name= data.getStringExtra("name");
                    String str_nik= data.getStringExtra("nik");
                    String str_title= data.getStringExtra("title");
                    String str_main= data.getStringExtra("main");

                    if(str_name==null){str_name="";}
                    if(str_nik==null){str_nik="";}
                    if(str_title==null){str_title="";}
                    if(str_main==null){str_main="";}

                    datas.add(str_name + "\n" + str_nik+ "\n" + str_title + "\n" + str_main);
                    //adapter.notifyDataSetChanged();

                    //리스트뷰의 스크롤 위치 지정
                    //listView.setSelection(datas.size()-1);

                    //리스트뷰의 항목이 비어있을때 보여지는 뷰를 설정
                    //TextView tvEmptyList= findViewById(R.id.tv_empty_list);
                    //listView.setEmptyView(tvEmptyList);
                }
                break;
        }

    }//onActivityResult

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_camera:
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.menu_call:
                Intent intent2=new Intent(Intent.ACTION_DIAL);
                Uri uri= Uri.parse("tel:01012345678");
                intent2.setData(uri);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }





    public void clickBtn1(View view) {
        Intent intent= new Intent(this,SecondActivity.class);
        startActivityForResult(intent,1);
    }

    public void clickBtn2(View view) {
//        //DEFAULT LAUNCHER
    }

//    public void Btn(View view){
//        PopupMenu popup= new PopupMenu(this,view);
//
//        getMenuInflater().inflate(R.menu.option,popup.getMenu());
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.menu_camera:
//                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivity(intent);
//                        break;
//                    case R.id.menu_call:
//                        Intent intent2=new Intent(Intent.ACTION_DIAL);
//                        Uri uri= Uri.parse("tel:01012345678");
//                        intent2.setData(uri);
//                        startActivity(intent2);
//                        break;
//                }
//                return false;
//            }
//        });
//
//    }

}//MainActivity

