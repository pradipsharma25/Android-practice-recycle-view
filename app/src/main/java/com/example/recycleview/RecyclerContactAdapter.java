package com.example.recycleview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> arrContact;
    RecyclerContactAdapter(Context context,ArrayList<ContactModel> arrContact){
        this.context = context;
        this.arrContact = arrContact;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imgContact.setImageResource(arrContact.get(position).img);
        holder.txtname.setText(arrContact.get(position).name);
        holder.txtNumber.setText(arrContact.get(position).number);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context,R.style.CustomDialog);
                dialog.setContentView(R.layout.add_contact);

                TextView newcnt = dialog.findViewById(R.id.newcnt);
                EditText editAddName = dialog.findViewById(R.id.editAddName);
                EditText editAddNum = dialog.findViewById(R.id.editAddNum);
                Button editSubmitBtn = dialog.findViewById(R.id.editSubmitBtn);

                editAddName.setText(arrContact.get(position).name);
                editAddNum.setText(arrContact.get(position).number);
                editSubmitBtn.setText("Update");

                newcnt.setText("Update Contact");

                editSubmitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editAddName.getText().toString();
                        String number = editAddNum.getText().toString();
                        arrContact.set(position,new ContactModel(R.drawable.contact,name,number)); //add and set are different. Add is used to add new value and set is used to update existing value.
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }

                });
                dialog.show();
            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //This will show in android as a pop up dialogue. We can customize it in run time.
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure to delete?")
                        .setIcon()//You can set you custome icon here
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrContact.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                return true;
            }

        });
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtname, txtNumber;
        ImageView imgContact;
        LinearLayout llRow;
        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
            llRow = itemView.findViewById(R.id.llRow);

        }
    }
}
