package com.example.se1731_demo1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.se1731_demo1.Intenkey;
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerCampus;
    private Button btnLogin;
    private EditText editUsername;
    private EditText editPassword;
    private RadioButton rdbManager;
    private RadioButton rdbStaff;
    private CheckBox checkbox;
    private String campus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tìm các view trong layout
        spinnerCampus = findViewById(R.id.spinner_campus);
        btnLogin = findViewById(R.id.btn_Login);
        editUsername = findViewById(R.id.edt_username);
        editPassword = findViewById(R.id.edt_password);
        rdbManager = findViewById(R.id.radio_button_manager);
        rdbStaff = findViewById(R.id.radio_button_staff);
        checkbox = findViewById(R.id.chk_remember);

        // Thiết lập Adapter cho Spinner
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.campus, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCampus.setAdapter(arrayAdapter);
        spinnerCampus.setOnItemSelectedListener(this);

        // Thiết lập sự kiện OnClickListener cho nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                String role = "";

                if (rdbStaff.isChecked()) {
                    role = "Staff";
                } else if (rdbManager.isChecked()) {
                    role = "Manager";
                } else {
                    role = "Guest";
                }

                boolean rememberMe = checkbox.isChecked();

                // Hiển thị thông báo khi người dùng nhấn nút login
                Toast.makeText(MainActivity.this, "Username: " + username + "\nRole: " + role + "\nRemember me: " + rememberMe + "\nCampus: " + campus, Toast.LENGTH_SHORT).show();

                // Kiểm tra trường username có trống không
                if (username.trim().length() == 0) {
                    editUsername.setError("Username is required");
                }
                if (password.trim().length() == 0) {
                    editPassword.setError("Password is require");
                } else {
                    Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                    intent.putExtra(Intenkey.USERNAME,username);
                    intent.putExtra(Intenkey.PASSWORD,password);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Lấy giá trị campus từ Spinner
        campus = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Selected Campus: " + campus, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Xử lý khi không có item nào được chọn
    }
}
