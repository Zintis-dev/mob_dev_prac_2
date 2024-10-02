package com.example.a2ndpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = {"Zintis Susinskis", "Klāvs Renārs Tūtins", "Pauls Jānis Andersons"};

        Button go_to_second_act = findViewById(R.id.go_to_2nd_act_btn);
        go_to_second_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Button dialog = findViewById(R.id.dialog_button);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok_button, null)
                        .setNeutralButton(R.string.close_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast(getString(R.string.close_clicked_text));
                            }
                        })
                        .setMultiChoiceItems(names, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    showToast(names[which] + " checked!");
                                } else {
                                    showToast(names[which] + " unselected!");
                                }
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

                Button okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast(getString(R.string.ok_clicked_text));
                    }
                });
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}