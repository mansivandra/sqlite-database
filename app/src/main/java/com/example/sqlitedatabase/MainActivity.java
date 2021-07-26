package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,dob;
    Button insert,update,delete,view;
    Dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        dob=findViewById(R.id.dob);

        insert=findViewById(R.id.insertbtn);
        update=findViewById(R.id.updatetbtn);
        delete=findViewById(R.id.deletebtn);
        view=findViewById(R.id.btnview);

        db =new Dbhelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTXT=dob.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(nameTXT,contactTXT,dobTXT);
                if (checkinsertdata == true)
                {
                    Toast.makeText(MainActivity.this, "new entry insert", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "new entry not insert", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTXT=dob.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(nameTXT,contactTXT,dobTXT);
                if (checkupdatedata == true)
                {
                    Toast.makeText(MainActivity.this, "entry update", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "entry not update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();

                Boolean checkdeletedata = db.deletedata(nameTXT);
                if (checkdeletedata == true)
                {
                    Toast.makeText(MainActivity.this, " entry delete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "entry not delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.getdata();
                if (res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "no entry existed", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("name :"+res.getString(0)+"(\n)");
                    buffer.append("contact :"+res.getString(1)+"(\n)");
                    buffer.append("date of birth :"+res.getString(2)+"(\n\n)");
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}