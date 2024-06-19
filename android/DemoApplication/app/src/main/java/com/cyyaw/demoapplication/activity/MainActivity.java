package com.cyyaw.demoapplication.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.cyyaw.common.BaseAppCompatActivity;
import com.cyyaw.common.permission.PermissionsCode;
import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.FloatMarkWindowService;
import com.cyyaw.demoapplication.service.FloatWindowLogService;
import com.cyyaw.demoapplication.service.FloatWindowTaskService;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener {


    public static final String TAG = MainActivity.class.getName();
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView previewView;


    // ========================================================================================================================================================
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previewView = findViewById(R.id.previewView);//初始化

        findViewById(R.id.btn_read_file).setOnClickListener(this);
        findViewById(R.id.btn_write_file).setOnClickListener(this);
        findViewById(R.id.btn_openFloatWin).setOnClickListener(this);
        findViewById(R.id.btn_openAccessibilityService).setOnClickListener(this);
        findViewById(R.id.btn_go_my_view).setOnClickListener(this);
        findViewById(R.id.btn_camera).setOnClickListener(this);

        findViewById(R.id.btn_audio).setOnClickListener(this);
        findViewById(R.id.btn_picture).setOnClickListener(this);
        findViewById(R.id.btn_video).setOnClickListener(this);
        findViewById(R.id.btn_location).setOnClickListener(this);
        findViewById(R.id.openBluetooth).setOnClickListener(this);
        findViewById(R.id.ReadBluetooth).setOnClickListener(this);
        findViewById(R.id.linkBluetooth).setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_openAccessibilityService) {
            requestPermissionsFn(PermissionsCode.BIND_ACCESSIBILITY_SERVICE, () -> {
                Toast.makeText(this, "无障碍功能开启成功----", Toast.LENGTH_SHORT).show();
            });
        } else if (id == R.id.btn_openFloatWin) {
            requestPermissionsFn(PermissionsCode.SYSTEM_ALERT_WINDOW, () -> {
                Toast.makeText(this, "开启浮窗成功----", Toast.LENGTH_SHORT).show();
                showFloatWin();
            });
        } else if (id == R.id.btn_camera) {
            requestPermissionsFn(PermissionsCode.CAMERA, () -> {
                Toast.makeText(this, "摄像头申请成功成功----", Toast.LENGTH_SHORT).show();
                startCamera();
            });
        } else if (id == R.id.btn_read_file) {
            requestPermissionsFn(PermissionsCode.READ_EXTERNAL_STORAGE, () -> {
                Toast.makeText(this, "有读文件权限----" + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
                File mVideoFile = new File(getApplication().getFilesDir().getPath());
                File[] files = mVideoFile.getParentFile().listFiles();
                StringBuffer sb = new StringBuffer("大小:" + files.length + "\r\n");
                for (int i = 0; i < files.length; i++) {
                    String name = files[i].getName();
                    sb.append(name + "\r\n");
                }
                TextView pageInfo = findViewById(R.id.pageInfo);
                pageInfo.setText("路径:" + sb);
            });
        } else if (id == R.id.btn_write_file) {
            // 检查权限是否已经被授予
            requestPermissionsFn(PermissionsCode.WRITE_EXTERNAL_STORAGE, () -> {
                Toast.makeText(this, "有写文件权限----" + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
                File mVideoFile = new File(getApplication().getFilesDir().getPath());
                File[] files = mVideoFile.getParentFile().listFiles();
                StringBuffer sb = new StringBuffer("大小:" + files.length + "\r\n");
                for (int i = 0; i < files.length; i++) {
                    String name = files[i].getName();
                    sb.append(name + "\r\n");
                }
                TextView pageInfo = findViewById(R.id.pageInfo);
                pageInfo.setText("路径:" + sb);
            });
        } else if (id == R.id.btn_audio) {
            requestPermissionsFn(PermissionsCode.READ_MEDIA_AUDIO, () -> {
                Toast.makeText(this, "音频", Toast.LENGTH_SHORT).show();
            });

        } else if (id == R.id.btn_picture) {
            requestPermissionsFn(PermissionsCode.READ_MEDIA_IMAGES, () -> {
                Toast.makeText(this, "图片", Toast.LENGTH_SHORT).show();
            });

        } else if (id == R.id.btn_video) {
            requestPermissionsFn(PermissionsCode.READ_MEDIA_VIDEO, () -> {
                Toast.makeText(this, "视频", Toast.LENGTH_SHORT).show();
            });
        } else if (id == R.id.btn_location) {

            requestPermissionsFn(PermissionsCode.ACCESS_FINE_LOCATION, () -> {
                requestPermissionsFn(PermissionsCode.ACCESS_BACKGROUND_LOCATION, () -> {
                    Toast.makeText(this, "GPS已开启", Toast.LENGTH_SHORT).show();
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    List<String> allProviders = locationManager.getAllProviders();
                    if (null != allProviders) {
                        for (int i = 0; i < allProviders.size(); i++) {
                            Log.d(TAG, "=====" + allProviders.get(i));
                        }
                    }
                    // 创建 LocationListener 实例
                    LocationListener locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            // 当位置变化时调用此方法
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            // 在此处处理获取到的经纬度信息
                            Log.d(TAG, "=====" + longitude);
                            Log.d(TAG, "=====" + latitude);
                            locationManager.removeUpdates(this);
                        }
                    };

                    // 请求位置更新
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                });
            });
        } else if (id == R.id.btn_go_my_view) {
            Intent intent = new Intent(this, ViewTestActivity.class);
            startActivity(intent);
        } else if (id == R.id.openBluetooth) {
            requestPermissionsFn(PermissionsCode.BLUETOOTH_CONNECT, () -> {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null) {
                    // 设备不支持蓝牙
                    Toast.makeText(this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!bluetoothAdapter.isEnabled()) {
                    // 蓝牙未打开，请求打开蓝牙
                    Toast.makeText(this, "蓝牙未打开, 正在申请打开...", Toast.LENGTH_SHORT).show();
                    activityResult.launch(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
                } else {
                    // 蓝牙已经打开，执行后续操作
                    Toast.makeText(this, "蓝牙已经打开", Toast.LENGTH_SHORT).show();
                }

            }, () -> {
            });


        } else if (id == R.id.ReadBluetooth) {
            requestPermissionsFn(PermissionsCode.BLUETOOTH_CONNECT, () -> {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
                    // 蓝牙未打开，请求打开蓝牙
                    Toast.makeText(this, "蓝牙未打开, 正在申请打开...", Toast.LENGTH_SHORT).show();
                    activityResult.launch(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
                } else {
                    // 蓝牙已经打开，执行后续操作
                    Toast.makeText(this, "蓝牙已经打开, 开始搜索...", Toast.LENGTH_SHORT).show();
                    // 开始扫描
                    BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
                    bluetoothLeScanner.startScan(new ScanCallback() {
                        @Override
                        @SuppressLint("MissingPermission")
                        public void onScanResult(int callbackType, ScanResult result) {
                            super.onScanResult(callbackType, result);
                            BluetoothDevice device = result.getDevice();
                            String name = device.getName();
                            String address = device.getAddress();
                            Log.d(TAG, "== name:" + name + "   address:" + address);
                        }
                    });

                }
            }, () -> {
            });
        } else if (id == R.id.linkBluetooth) {
            requestPermissionsFn(PermissionsCode.BLUETOOTH_CONNECT, () -> {

                Toast.makeText(this, "有蓝牙连接权限", Toast.LENGTH_SHORT).show();
            });
        }
    }

    /**
     *
     */
    private synchronized void showFloatWin() {
        Intent in = new Intent(MainActivity.this, FloatWindowLogService.class);
        // 日志信息
        ComponentName windowLog = startService(in);
        Intent ins = new Intent(MainActivity.this, FloatWindowTaskService.class);
        startService(ins);
        // 创建操作跟踪红点
        Intent inx = new Intent(MainActivity.this, FloatMarkWindowService.class);
        //
        ComponentName componentNamex = startService(inx);
    }

    /**
     *
     */
    private void startCamera() {
        // 请求 CameraProvider
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        //检查 CameraProvider 可用性，验证它能否在视图创建后成功初始化
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this));
    }

    /**
     * 选择相机并绑定生命周期和用例
     */
    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview);

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}