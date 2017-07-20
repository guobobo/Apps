package fm.jiecao.jcvideoplayer_lib;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class JCFullScreenActivity extends Activity implements PandaVedioPlayer.QHlistener{
  /**
   * 刚启动全屏时的播放状态
   */
  static int CURRENT_STATE = -1;      //当前的状态
  public static String URL;     //视频播放的地址
  public static Map<String, String> mapHeadData = null;//视屏播放参数的集合
  static boolean DIRECT_FULLSCREEN = true;//是否直接充满全屏
  static Class VIDEO_PLAYER_CLASS;//采用哪个视频播放类
  static Object[] OBJECTS;
  PandaVedioPlayer pandaVedioPlayer;
  private AudioManager audioManager;
  private MyVolumeReceiver mVolumeReceiver;
  private String newurl = "http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4";


  //从正常状态下进入视频播放
  static void toActivityFromNormal(Context context, int state, String url, Class videoPlayClass, Object... obj) {
    CURRENT_STATE = state;
    DIRECT_FULLSCREEN = false;
    URL = url;
    VIDEO_PLAYER_CLASS = videoPlayClass;
    OBJECTS = obj;
    Intent intent = new Intent(context, JCFullScreenActivity.class);
    context.startActivity(intent);
  }

  //直接开启全屏Activity播放视频
  public static void toActivity(Context context, String url, Map<String, String> headData, Class videoPlayClass, Object... obj) {
    if (headData!=null) {
      mapHeadData = new HashMap<>();
      mapHeadData.clear();
      mapHeadData.putAll(mapHeadData);
    }
    CURRENT_STATE = JCVideoPlayer.CURRENT_STATE_NORMAL;
    URL = url;
    VIDEO_PLAYER_CLASS = videoPlayClass;
    OBJECTS = obj;
    Intent intent = new Intent(context, JCFullScreenActivity.class);
    context.startActivity(intent);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //注册广播接受者获取系统音量
    audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
    setAndRegisterVolumeReceiver();

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    View decor = this.getWindow().getDecorView();
    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    try {
      Constructor<PandaVedioPlayer> constructor = VIDEO_PLAYER_CLASS.getConstructor(Context.class);
      pandaVedioPlayer = constructor.newInstance(this);
      setContentView(pandaVedioPlayer);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //得到反射对象之后设置一些成员变量
    pandaVedioPlayer.IF_CURRENT_IS_FULLSCREEN = true;//当前是否充满全屏
    pandaVedioPlayer.IF_FULLSCREEN_IS_DIRECTLY = DIRECT_FULLSCREEN;//是否直接充满全屏
    pandaVedioPlayer.setUrlAndObject(URL,mapHeadData==null?null:mapHeadData,OBJECTS);
    pandaVedioPlayer.setStateAndUi(CURRENT_STATE);
    pandaVedioPlayer.setQHListener(this);

    if (pandaVedioPlayer.IF_FULLSCREEN_IS_DIRECTLY) {
      pandaVedioPlayer.ivStart.performClick();
    } else {
      JCVideoPlayer.IF_RELEASE_WHEN_ON_PAUSE = true;
      JCMediaManager.intance().listener = pandaVedioPlayer;
    }
  }

  private void setAndRegisterVolumeReceiver() {
    mVolumeReceiver = new MyVolumeReceiver();
    IntentFilter filter = new IntentFilter();
    filter.addAction("android.media.VOLUME_CHANGED_ACTION");
    registerReceiver(mVolumeReceiver, filter);
  }

  @Override
  public void onBackPressed() {
    pandaVedioPlayer.backFullscreen();
  }


  @Override
  protected void onPause() {
    super.onPause();
    JCVideoPlayer.releaseAllVideos();
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    unregisterReceiver(mVolumeReceiver);
  }

  @Override
  public void OnQH() {
    Log.d("JCFullScreenActivity", "切换");
    String s = Environment.getExternalStorageDirectory() + "/Download/b.mp4";
    JCMediaManager.intance().prepareToPlay(JCFullScreenActivity.this,s,null);
  }

  private class MyVolumeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      //广播接受者内监听系统音量变化更新音量进度条
      if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        pandaVedioPlayer.seekbar_volume.setProgress(currVolume);
      }
    }
  }
}
