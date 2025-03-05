package com.example.recycleview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerContactAdapter adapter;
    ArrayList<ContactModel> arrContacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerContact;

        FloatingActionButton btnOpenDialog;

        recyclerContact = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this,R.style.CustomDialog);
                dialog.setContentView(R.layout.add_contact);

                EditText editAddName = dialog.findViewById(R.id.editAddName);
                EditText editAddNum = dialog.findViewById(R.id.editAddNum);
                Button editSubmitBtn = dialog.findViewById(R.id.editSubmitBtn);

                editSubmitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    String name = editAddName.getText().toString();
                    String number = editAddNum.getText().toString();

                    arrContacts.add(new ContactModel(name,number));
                    adapter.notifyItemInserted(arrContacts.size()-1);

                    recyclerContact.scrollToPosition(arrContacts.size()-1);

                    dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        recyclerContact.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)); //you can set another layout instead of GridLayout.

        arrContacts.add(new ContactModel(R.drawable.aditya,"Aditya","986554342"));
        arrContacts.add(new ContactModel(R.drawable.sid,"Siddartha","986554342"));
        arrContacts.add(new ContactModel(R.drawable.musiraf,"Musarif","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"A","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Ad","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adi","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adit","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adity","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adita","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adiya","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adtya","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Aitya","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"ditya","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Aaditya","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adaitya","986554342"));
        arrContacts.add(new ContactModel(R.drawable.contact,"Adiatya","986554342"));

        adapter= new RecyclerContactAdapter(this,arrContacts);
        recyclerContact.setAdapter(adapter);

    }
}