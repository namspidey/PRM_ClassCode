package com.example.se1731_demo1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.se1731_demo1.bean.Student;
//import com.example.myapplication.IntentKey;

public class UserProfileActivity extends AppCompatActivity {

    TextView tv_firstname, tv_lastname, tvphone;
    Button btnEditProfile;
    Student student;
String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.user_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các TextView từ layout
        tv_firstname = findViewById(R.id.tv_first_name);
        tv_lastname = findViewById(R.id.tv_last_name);
        tvphone = findViewById(R.id.tv_phone);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra(Intenkey.USERNAME);
            String password = intent.getStringExtra(Intenkey.PASSWORD);

            // Gọi hàm để lấy thông tin student từ username
            student = getStudent(username);

            // Hiển thị thông tin lên TextView
            if (student != null) {
                tv_firstname.setText(student.getFirstName());
                tv_lastname.setText(student.getLastName());
            }
        }

        // Cài đặt OnClickListener cho số điện thoại
        tvphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:8005551234");
                Intent it = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(it);
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent1 = new Intent(UserProfileActivity.this, EditProfileActivity.class);
                intent1.putExtra(Intenkey.USERNAME, username);
                startActivity(intent1);
            }
        });
    }


    private Student getStudent(String username) {


        Student student = new Student();


        student.setFirstName("Hoang");
        student.setLastName("Nguyen");


        return student;
    }

}