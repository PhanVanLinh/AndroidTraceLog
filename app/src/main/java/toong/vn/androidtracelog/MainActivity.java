package toong.vn.androidtracelog;

import android.Manifest;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RequestPermissionHandler mRequestPermissionHandler;
    StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestPermissionHandler = new RequestPermissionHandler();
        Debug.startMethodTracing("sample");

        requestPermissions();
        a();
        b();
        Debug.stopMethodTracing();
//        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

    private void a() {
        for (int i = 0; i < 10000; i++) {
            builder.append("" + i);
        }
    }
    private void b() {
        for (int i = 0; i < 10000; i++) {
            builder.append("" + i);
        }
    }

    private void requestPermissions(){
        mRequestPermissionHandler.requestPermission(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        }, 123, new RequestPermissionHandler.RequestPermissionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "request permission success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this, "request permission failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mRequestPermissionHandler.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
