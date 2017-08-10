package com.grishalive.manlist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddEntryActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCancel, btnOk;
    EditText editTextDate, editTextName, editTextPhone;
    Calendar date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnOk = (Button) findViewById(R.id.buttonOk);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextName = (EditText) findViewById(R.id.editTextLastName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        date = Calendar.getInstance();

        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        editTextDate.setOnClickListener(this);
        setInitialDate();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editTextDate:
                setDate(view);
                break;
            case R.id.buttonOk:
                returnResults();
                break;
            case R.id.buttonCancel:
                finish();
                break;
        }
    }

    private void returnResults() {
        if(isInputDataValid()) {
            Intent data = new Intent();
            data.putExtra(QueryStrings.COLUMN_NAME_NAME,
                    editTextName.getText().toString());
            data.putExtra(QueryStrings.COLUMN_NAME_DATE,
                    editTextDate.getText().toString());
            data.putExtra(QueryStrings.COLUMN_NAME_PHONE,
                    editTextPhone.getText().toString());
            this.setResult(RESULT_OK, data);
            finish();
        }
    }

    private boolean isInputDataValid() {
        if (isEmpty(editTextName) || isEmpty(editTextPhone)) {
            Toast.makeText(this, R.string.some_fields_are_empty, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmpty(EditText text) {
        return text.getText().toString().trim().length() == 0;
    }

    private void setInitialDate() {
        editTextDate.setText(DateUtils.formatDateTime(this,
                date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
        ));
    }

    public void setDate(View v) {
        new DatePickerDialog(this, d,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };
}
