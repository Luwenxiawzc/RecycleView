package com.com.jnu.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputShopItemActivity extends AppCompatActivity {

    public static final int RESULT_CODE_SUCCESS = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_shop_item);

        EditText editTextTitle=findViewById(R.id.edittext_shop_item_title);
        Button buttonOk=findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("title", editTextTitle.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_CODE_SUCCESS, intent);
                InputShopItemActivity.this.finish();
            }
        });
    }
}