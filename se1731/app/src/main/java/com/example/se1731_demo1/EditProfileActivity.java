package com.example.se1731_demo1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity  {
private Button btnSaveProfile;
private EditText editFirstName,editLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edit_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSaveProfile = findViewById(R.id.bt_save_profile);
    editFirstName = findViewById(R.id.tv_edit_first_name);
    btnSaveProfile.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent resultIntent = new Intent();
            resultIntent.putExtra("firstName", editFirstName.getText().toString());
            setResult(2, resultIntent); // Trả kết quả về Activity gọi
            finish();
        }
    });

    }
}