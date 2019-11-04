package com.youngstudio.ex00activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
public class Page3Fragment extends Fragment {

    ListView listView;
    Page3ListAdapter adapter;

    ArrayList<String> datas= new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //대량의 데이터 추가하기
        datas.add("aaa");
        datas.add("bbb");
        datas.add("ccc");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_page3, container, false);
    }

    //위 onCreateView가 실행된 후 자동 실행되는 메소드
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView= view.findViewById(R.id.listview);
        adapter= new Page3ListAdapter(datas, getActivity());
        listView.setAdapter(adapter);


        //리스트뷰의 아이템을 클릭하면...
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
