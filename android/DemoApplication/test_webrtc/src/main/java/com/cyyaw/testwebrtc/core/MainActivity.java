package com.cyyaw.testwebrtc.core;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cyyaw.testwebrtc.App;
import com.cyyaw.testwebrtc.LauncherActivity;
import com.cyyaw.testwebrtc.R;
import com.cyyaw.testwebrtc.core.socket.IUserState;
import com.cyyaw.testwebrtc.core.socket.SocketManager;
import com.cyyaw.testwebrtc.core.voip.Consts;
import com.cyyaw.testwebrtc.core.voip.VoipReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements IUserState {
    private static final String TAG = "MainActivity";
    boolean isFromCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_user, R.id.navigation_room, R.id.navigation_setting).build();
        // 設置ActionBar跟随联动
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // 设置Nav跟随联动
        NavigationUI.setupWithNavController(navView, navController);
        // 设置登录状态回调
        SocketManager.getInstance().addUserStateCallback(this);

        isFromCall = getIntent().getBooleanExtra("isFromCall", false);
        Log.d(TAG, "onCreate isFromCall = " + isFromCall);
        if (isFromCall) { //无权限，来电申请权限会走这里
            initCall();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart isFromCall = " + isFromCall);
    }

    @Override
    public void userLogin() {

    }

    private void initCall() {
        //在前台了，发送广播 调起权限判断弹窗
        Intent viop = new Intent();
        Intent intent = getIntent();
        viop.putExtra("room", intent.getStringExtra("room"));
        viop.putExtra("audioOnly", intent.getBooleanExtra("audioOnly", false));
        viop.putExtra("inviteId", intent.getStringExtra("inviteId"));
        viop.putExtra("inviteUserName", intent.getStringExtra("inviteUserName"));
        viop.putExtra("userList", intent.getStringExtra("userList"));
        viop.setAction(Consts.ACTION_VOIP_RECEIVER);
        viop.setComponent(new ComponentName(App.getInstance().getPackageName(), VoipReceiver.class.getName()));
        sendBroadcast(viop);
    }

    @Override
    public void userLogout() {
        if (!this.isFinishing()) {
            Intent intent = new Intent(this, LauncherActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            super.onBackPressed();
        }

    }
}
