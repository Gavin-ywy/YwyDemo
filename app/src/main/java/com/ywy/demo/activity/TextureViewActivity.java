package com.ywy.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Surface;
import android.view.TextureView;

import com.ywy.demo.R;
import com.ywy.demo.utils.BaseLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TextureView是Android 4.0之后加入的，低版本么这个类。T
 * extureView必须工作在开启硬件加速的环境中，
 * 也即配置文件里Activity的设置项里：android:hardwareAccelerated="true"
 * 默认的这个属性就是true，因此不用再写了。但如果写成false，
 * 可以看到onSurfaceTextureAvailable()这个回调就进不来了；
 */
public class TextureViewActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    private static final String TAG = TextureViewActivity.class.getSimpleName();
    private TextureView mTextureView;
    private MediaPlayer mMediaPlayer;
    private Surface mSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_view);

        initView();

    }

    private void initView() {

        mTextureView = findViewById(R.id.textureview);

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        BaseLog.logE(TAG, "1  Available");
        mSurface = new Surface(surface);
        new PlayerVideo().start();//开启一个线程去播放视频
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        BaseLog.logE(TAG, "2  SizeChanged : " + width + "  height : " + height);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        BaseLog.logE(TAG, "3  Destroyed");
        surfaceTexture = null;
        mSurface = null;
        mMediaPlayer.stop();
        mMediaPlayer.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        BaseLog.logE(TAG, "4  Updated");
    }


    private class PlayerVideo extends Thread {
        @Override
        public void run() {
            try {
                File file = new File(Environment.getExternalStorageDirectory() + "/ansen.mp4");
                if (!file.exists()) {//文件不存在
                    copyFile();
                }
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(file.getAbsolutePath());
                mMediaPlayer.setSurface(mSurface);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface PlayerController {
        public void play();
    }

    /**
     * 如果sdcard没有文件就复制过去
     */
    private void copyFile() {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open("video.mp4");
            String newFileName = Environment.getExternalStorageDirectory() + "/video.mp4";
            out = new FileOutputStream(newFileName);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            BaseLog.logE(TAG, e.getMessage());
        }
    }

}
