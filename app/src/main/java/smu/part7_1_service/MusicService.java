package smu.part7_1_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MusicService extends Service {

    public MusicService() {}

    IBinder binder = new MusicServiceBinder();
    public class MusicServiceBinder extends Binder{
        public MusicService getService(){
            return MusicService.this;
        }
    }
    private MediaPlayer mp;


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
        // TODO: Return the communication channel to the service.
    }

    public void onCreate(){
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.pet);
        mp.setLooping(true);
    }

    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);
        Toast.makeText(getApplicationContext(), "서비스 연결", Toast.LENGTH_SHORT).show();
    }


}