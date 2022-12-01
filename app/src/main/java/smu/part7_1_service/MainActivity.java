package smu.part7_1_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button musicStart, musicPlay, musicPause, musicStop;

    // 객체 생성 및 코드추가
    MusicService musicService;

    // 연동할 Service 객체 생성
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MusicServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicStart = findViewById(R.id.buttonStart);
        musicPlay = findViewById(R.id.buttonPlay);
        musicPause = findViewById(R.id.buttonPause);
        musicStop = findViewById(R.id.buttonStop);

        musicStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service = new Intent(MainActivity.this, MusicService.class);
                startService(service);
                // 연동할 서비스 객체와 bind
                bindService(service, conn, BIND_AUTO_CREATE);
            }
        });
        musicPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // bind된 Service 객체 함수 실행
                musicService.play();
            }
        });
        musicPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicService.pause();
            }
        });
        musicStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service = new Intent(MainActivity.this, MusicService.class);
                unbindService(conn);
                stopService(service);
            }
        });
    }
}