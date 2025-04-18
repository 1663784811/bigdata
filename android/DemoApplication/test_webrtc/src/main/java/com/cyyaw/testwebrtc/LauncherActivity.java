package com.cyyaw.testwebrtc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.testwebrtc.core.MainActivity;
import com.cyyaw.testwebrtc.core.socket.IUserState;
import com.cyyaw.testwebrtc.core.socket.SocketManager;
import com.cyyaw.testwebrtc.temp1.Temple1Activity;
import com.cyyaw.testwebrtc.temp2.Temple2Activity;


public class LauncherActivity extends AppCompatActivity implements IUserState {
    private EditText etUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initView();

        if (SocketManager.getInstance().getUserState() == 1) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void initView() {
        etUser = findViewById(R.id.et_user);
        etUser.setText(App.getInstance().getUsername());
    }


    @Override
    public void userLogin() {
        // 开启新页面
        startActivity(new Intent(this, MainActivity.class));
        // 结束当前页面
        finish();
    }

    @Override
    public void userLogout() {

    }

    public void login(View view) {
        String username = etUser.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "please input your name", Toast.LENGTH_LONG).show();
            return;
        }
        // 设置用户名
        App.getInstance().setUsername(username);
        // 添加登录回调
        SocketManager.getInstance().addUserStateCallback(this);
        // 连接socket:登录
        SocketManager.getInstance().connect(Urls.WS, username, 0);
    }


    public void temple1(View view) {
        startActivity(new Intent(this, Temple1Activity.class));
    }

    public void temple2(View view) {
        startActivity(new Intent(this, Temple2Activity.class));

    }
}
