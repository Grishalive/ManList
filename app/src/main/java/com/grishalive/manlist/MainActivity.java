package com.grishalive.manlist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    DBHelper dbHelper;
    Button btnAdd, btnExit;
    ListView lv;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        lv = (ListView)findViewById(R.id.listViewEntries);
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnExit = (Button)findViewById(R.id.buttonExit);

        btnAdd.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        ArrayList<Model> entries = getAllEntriesFromDB(QueryStrings.TABLE_NAME);
        adapter = new CustomAdapter(this, entries);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                Intent intent = new Intent(this, AddEntryActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.buttonExit:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;
        }
    }

    private ArrayList<Model> getAllEntriesFromDB(String tableName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(tableName, null, null, null, null, null, QueryStrings.COLUMN_NAME_NAME);
        ArrayList<Model> entries = new ArrayList<>();
        if (c.moveToFirst()) {
            int dateColIndex = c.getColumnIndex(QueryStrings.COLUMN_NAME_NAME);
            int nameColIndex = c.getColumnIndex(QueryStrings.COLUMN_NAME_DATE);
            int phoneColIndex = c.getColumnIndex(QueryStrings.COLUMN_NAME_PHONE);
            do {
                entries.add(new Model(c.getString(dateColIndex),
                        c.getString(nameColIndex),
                        c.getString(phoneColIndex)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return entries;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String name = data.getStringExtra(QueryStrings.COLUMN_NAME_NAME);
        String date = data.getStringExtra(QueryStrings.COLUMN_NAME_DATE);
        String phone = data.getStringExtra(QueryStrings.COLUMN_NAME_PHONE);
        Model model = new Model(name, date, phone);
        adapter.add(model);
        writeInDB(model);
        adapter.notifyDataSetChanged();
    }

    private void writeInDB(Model model) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QueryStrings.COLUMN_NAME_NAME, model.getName());
        cv.put(QueryStrings.COLUMN_NAME_DATE, model.getDate());
        cv.put(QueryStrings.COLUMN_NAME_PHONE, model.getPhone());
        db.insert(QueryStrings.TABLE_NAME, null, cv);
        db.close();
    }
}
