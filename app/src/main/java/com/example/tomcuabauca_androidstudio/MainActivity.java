package com.example.tomcuabauca_androidstudio;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //khai báo
    private Spinner sptomcua;
    private List<String>list;
    EditText editchon;
    Button btnstart , btnreset;
    EditText edit1 , edit2 , edit3 , editmoney , editketqua , editmoneynhan;
    ImageView imageView6 , imageView7 , imageView8 , imageView9 , imageView10 , imageView11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add("");
        list.add("Tôm");
        list.add("Cua");
        list.add("Bầu");
        list.add("Cá");
        list.add("Gà");
        list.add("Nai");
        // ánh xạ
        sptomcua = findViewById(R.id.sptomcua);
        editchon = findViewById(R.id.editchon);
        btnstart = findViewById(R.id.btnstart);
        btnreset = findViewById(R.id.btnreset);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        editmoney = findViewById(R.id.editmoney);
        editketqua = findViewById(R.id.editketqua);
        editmoneynhan = findViewById(R.id.editmoneynhan);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNumber1 = (int) (Math.random() * 6) + 1;
                int randomNumber2 = (int) (Math.random() * 6) + 1;
                int randomNumber3 = (int) (Math.random() * 6) + 1;

                String[] animals = {"Tôm", "Cua", "Bầu", "Cá", "Gà", "Nai"};

                // Hiển thị tên con vật tương ứng trong các EditText
                edit1.setText(animals[randomNumber1 - 1]); // Chuyển đổi số 1-6 thành chỉ số mảng 0-5
                edit2.setText(animals[randomNumber2 - 1]);
                edit3.setText(animals[randomNumber3 - 1]);
                // lấy con vật thì spinner
                String selectedAnimal = editchon.getText().toString();
                int count = 0;
                int money = 0;

                // Kiểm tra nếu EditText cho số tiền không rỗng
                if (!editmoney.getText().toString().isEmpty()) {
                    try {
                        money = Integer.parseInt(editmoney.getText().toString().replace(".", "").trim());
                    } catch (NumberFormatException e) {
                        // Xử lý lỗi khi không thể chuyển đổi
                        editmoneynhan.setText("Số tiền không hợp lệ");
                        return; // Dừng thực hiện nếu có lỗi
                    }
                } else {
                    editmoneynhan.setText("Vui lòng nhập số tiền đặt");
                    return; // Dừng thực hiện nếu số tiền không hợp lệ
                }

                // Kiểm tra số trúng
                if (selectedAnimal.equals(animals[randomNumber1 - 1])) {
                    count++;
                }
                if (selectedAnimal.equals(animals[randomNumber2 - 1])) {
                    count++;
                }
                if (selectedAnimal.equals(animals[randomNumber3 - 1])) {
                    count++;
                }

                // Ghi kết quả vào editketqua
                if (count > 0) {
                    editketqua.setText("Trúng " + count + " con " + selectedAnimal);
                } else {
                    editketqua.setText("Không trúng con nào");
                }

                // Định dạng số tiền nhận
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String formattedMoney = formatter.format(count * money);

                // Hiển thị số tiền nhận
                editmoneynhan.setText(formattedMoney );
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ArrayAdapter sptomcuaAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        sptomcua.setAdapter(sptomcuaAdapter);
        sptomcua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String msg = "position" + i + "value" + list.get(i);
                Toast.makeText(MainActivity.this , msg , Toast.LENGTH_SHORT).show();
                editchon.setText(list.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Chưa chọn",Toast.LENGTH_SHORT).show();
            }
        });




        //-----------------------------------------------------------------------------------------------------------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}