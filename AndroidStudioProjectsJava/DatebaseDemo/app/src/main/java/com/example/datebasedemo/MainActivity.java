package com.example.datebasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datebasedemo.database.DataBaseHelper;
import com.example.datebasedemo.recycleviewadapter.RecycleViewAdapter;
import com.example.datebasedemo.recycleviewadapter.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // vars
    private DataBaseHelper dataBaseHelper;
    private EditText name;
    private EditText description;
    private List<Item> items = new ArrayList<>();
//    private CustomAdapter customAdapter;
//    private ListView listView;

    private RecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerView;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper =  new DataBaseHelper(this);
        name = findViewById(R.id.editTextName);
        description = findViewById(R.id.editTextDescription);

        //dataBaseHelper.removeAllData();

        recyclerView = findViewById(R.id.recyclerView);
        recycleViewAdapter = new RecycleViewAdapter(this, items);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
        setListener();
    }


    public void saveData(View view) {
        Log.d(TAG, "saveData: called");

        String name = (String.valueOf(this.name.getText()) != null) ? String.valueOf(this.name.getText()) : "";
        String description = (String.valueOf(this.description.getText()) != null) ? String.valueOf(this.description.getText()) : "";

        dataBaseHelper.addData(name, description);
        items.add(new Item(name, description));

        recycleViewAdapter.notifyDataSetChanged();
    }

    public void getData() {
        String name = null;
        String description = null;
        int id;
        Cursor cursor = dataBaseHelper.getData();

        int nameIndex = cursor.getColumnIndex(DataBaseHelper.getCOL1());
        int descriptionIndex = cursor.getColumnIndex(DataBaseHelper.getCOL2());
        int idIndex = cursor.getColumnIndex(DataBaseHelper.getCOL0());

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            name = cursor.getString(nameIndex);
            description = cursor.getString(descriptionIndex);
            id = cursor.getInt(idIndex);


            items.add(new Item(name, description));
            Log.d("LOG", name + "/ " + description + "/ " + id);

            cursor.moveToNext();
        }

        //customAdapter.notifyDataSetChanged();
        recycleViewAdapter.notifyDataSetChanged();
    }

    private void setListener() {
        RecyclerTouchListener touchListener = new RecyclerTouchListener(this, recyclerView);
        touchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Toast.makeText(getApplicationContext(),items.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                })
                .setSwipeOptionViews(R.id.delete_task,R.id.edit_task)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        switch (viewID){
                            case R.id.delete_task:
                                items.remove(position);
                                recycleViewAdapter.setItemList(items);
                                break;
                            case R.id.edit_task:
                                Toast.makeText(getApplicationContext(),"Edit Not Available",Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });
        recyclerView.addOnItemTouchListener(touchListener);

    }

    private void removeFromList() {

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Item item = items.get(position);
//                String name = item.getName();
//                int nameID = getItemId(name);
//
//                if (nameID == -1) {
//                    toastMessage("Item not found");
//                } else {
//                    dataBaseHelper.deleteName(nameID, name);
//                }
//                return true;
//            }
//        });
    }

    private int getItemId(String name) {
        Cursor cursor = dataBaseHelper.getItemID(name);
        int id = -1;

        int idIndex = cursor.getColumnIndex(DataBaseHelper.getCOL0());

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            id = cursor.getInt(idIndex);

            cursor.moveToNext();
        }

        return id;
    }


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}