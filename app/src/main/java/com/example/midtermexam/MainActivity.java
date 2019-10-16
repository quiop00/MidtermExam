package com.example.midtermexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtTen;
    EditText edtTuoi;
    EditText edtDiaChi;
    Button btnAddData;
    ListView lvInfo;
    ArrayList<Item> listItem;
    Item item;
    CustomAdapter customAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listItem=new ArrayList<Item>();
        customAdapter=new CustomAdapter(MainActivity.this,listItem);
        lvInfo.setAdapter(customAdapter);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTen.getText().toString().equals("")||edtTuoi.getText().toString().equals("")||edtDiaChi.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this,"Please input all",Toast.LENGTH_SHORT).show();
                }else
                if(Integer.parseInt(edtTuoi.getText().toString())<1||Integer.parseInt(edtTuoi.getText().toString())>99){
                    Toast.makeText(MainActivity.this,"Do tuoi khong dung (1-99)",Toast.LENGTH_SHORT).show();
                }
                else
                if(checkName(edtTen.getText().toString(),edtTuoi.getText().toString(),edtDiaChi.getText().toString())){
                    Toast.makeText(MainActivity.this,"Trung du lieu",Toast.LENGTH_SHORT).show();
                }
                else {
                    String name=edtTen.getText().toString();
                    String age=edtTuoi.getText().toString();
                    String address=edtDiaChi.getText().toString();
                    item=new Item();
                    item.setTen(name);
                    item.setTuoi(age);
                    item.setDiachi(address);
                    listItem.add(item);
                    customAdapter.notifyDataSetChanged();

                }
            }
        });
        lvInfo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
               delete(pos);
                return false;
            }
        });

    }
    public void delete(final int pos){
        AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn xóa không?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listItem.remove(pos);
                customAdapter.notifyDataSetChanged();
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }
    public void init(){
        edtTen=(EditText) findViewById(R.id.edt_hoten);
        edtTuoi=(EditText) findViewById(R.id.edt_tuoi);
        edtDiaChi=(EditText) findViewById(R.id.edt_diachi);
        btnAddData=(Button) findViewById(R.id.btn_add);
        lvInfo=(ListView) findViewById(R.id.lv_info);
    }
    public boolean checkName(String name,String age,String address){
        for(Item a:listItem){
            if(a.getTen().equals(name)&&a.getTuoi().equals(age)&&a.getDiachi().equals(address))
                return true;
        };
        return false;
    }
}
