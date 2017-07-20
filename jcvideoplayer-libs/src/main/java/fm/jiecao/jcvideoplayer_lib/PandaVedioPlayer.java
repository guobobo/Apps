package fm.jiecao.jcvideoplayer_lib;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PandaVedioPlayer extends JCVideoPlayer {

    public ImageView ivBack;//返回按钮
    public TextView tvTitle;//顶部标题
    public ImageView ivThumb;//全屏背景
    public ProgressBar pbLoading;//加载视频进度条
    public ImageButton btn_collect;//收藏按钮
    public ImageButton btn_share;//分享按钮
    public Button btn_clarity;//清晰度按钮
    public ImageButton btn_volume;//清晰度按钮
    public SeekBar seekbar_volume;//音量进度

    public static final String TAG = "PandaVedioPlayer";

    protected static Timer mDismissControlViewTimer;//  1.5秒的消失计时器
    protected static JCBuriedPointStandard jc_BuriedPointStandard;//节操隐藏标准
    private AudioManager systemService;
    private int currentVolume;
    private int maxVolume;
    private int lastVolume;

    private boolean isCollect = false;
    private static int currentPosition;

    public PandaVedioPlayer(Context context) {
        super(context);
    }

    public PandaVedioPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pandatv_pullscreen;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        systemService = ((AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE));
        maxVolume = systemService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = systemService.getStreamVolume(AudioManager.STREAM_MUSIC);

        ivBack = (ImageView) findViewById(R.id.back);
        tvTitle = (TextView) findViewById(R.id.title);
        ivThumb = (ImageView) findViewById(R.id.thumb);
        pbLoading = (ProgressBar) findViewById(R.id.loading);
        btn_collect = (ImageButton) findViewById(R.id.btn_collect);
        btn_share = (ImageButton) findViewById(R.id.btn_share);
        btn_clarity = (Button) findViewById(R.id.btn_clarity);
        btn_volume = (ImageButton) findViewById(R.id.btn_volume);
        seekbar_volume = (SeekBar) findViewById(R.id.seekbar_volume);

        ivBack.setOnClickListener(this);
        ivThumb.setOnClickListener(this);

        btn_collect.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        btn_clarity.setOnClickListener(this);
        btn_volume.setOnClickListener(this);

        seekbar_volume.setMax(maxVolume);//设置拖动条最大值
        seekbar_volume.setProgress(currentVolume);//设置现在的值
        setVolumeImage(currentVolume);//设置现在的声音图标

        lastVolume = currentVolume;
        //拖动条的监听
        seekbar_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentVolume = progress;
                Log.d(TAG, "currentVolume:" + currentVolume);
                if (progress==0) {
                    btn_volume.setBackgroundResource(R.drawable.volume_no);
                    systemService.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_PLAY_SOUND);
                }else {
                    btn_volume.setBackgroundResource(R.drawable.volume_continue);
                    systemService.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_PLAY_SOUND);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    /**
     * 设置声音图片
     *
     * @param currentVolume
     */
    public void setVolumeImage(int currentVolume) {
        if (currentVolume == 0) {
           btn_volume.setBackgroundResource(R.drawable.volume_no);
        } else {
            btn_volume.setBackgroundResource(R.drawable.volume_continue);
        }
    }
    private Object object ;
    //开始准备
    @Override
    public void setUrlAndObject(String url, Map<String, String> mapHeadData, Object... objects) {
        if (objects.length == 0) return;
        this.object = objects;
        //这里调用了父类的setUrlAndObject方法
        super.setUrlAndObject(url, mapHeadData, objects);
        //设置标题
        tvTitle.setText(objects[0].toString());
    }

    @Override
    public void setStateAndUi(int state) {
        super.setStateAndUi(state);
        switch (CURRENT_STATE) {
            case CURRENT_STATE_NORMAL: //在正常状态时
                changeUiToNormal();
                startTimer(10000);
                break;
            case CURRENT_STATE_PREPAREING: //在准备状态时
                changeUiToShowUiPrepareing();
                break;
            case CURRENT_STATE_PLAYING: //在播放状态时
                changeUiToShowUiPlaying();
                startTimer(6000);
                break;
            case CURRENT_STATE_PAUSE: //在暂停状态时
                changeUiToShowUiPause();

                break;
            case CURRENT_STATE_ERROR: //在异常状态时
                changeUiToError();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.start) {
            if (TextUtils.isEmpty(url)) {
                Toast.makeText(getContext(), "No url", Toast.LENGTH_SHORT).show();
                return;
            }
            if (CURRENT_STATE == CURRENT_STATE_NORMAL) {
                if (jc_BuriedPointStandard != null) {
                    jc_BuriedPointStandard.onClickStartThumb(url, objects);
                }
                prepareVideo();//准备播放视频
                startTimer(6000);
            }
        }else if (i == R.id.surface_container) {
            //当播放状态时可以点击最外层布局
            startTimer(6000);
        }else if (i == R.id.thumb) {
            //当准备状态时只能点击背景
            if (CURRENT_STATE == CURRENT_STATE_PREPAREING) {
                if (llBottomContainer.getVisibility() == View.VISIBLE) {
                    llTopContainer.setVisibility(View.INVISIBLE);
                    llBottomContainer.setVisibility(View.INVISIBLE);
                }else{
                    llTopContainer.setVisibility(View.VISIBLE);
                    llBottomContainer.setVisibility(View.VISIBLE);
                    startTimer(5000);
                }
            }
        } else if (i == R.id.back) {
            //返回按钮
            backFullscreen();
        } else if (i == R.id.btn_collect) {

            if (isCollect) {
                //已收藏，取消收藏
                showToast("已取消收藏");
                isCollect = false;
                btn_collect.setBackgroundResource(R.drawable.play_fullsrcee_collect);
                //dosomething to not collect
            }else{
                //未收藏，点击收藏
                showToast("已添加，请到[我的收藏]中查看");
                isCollect = true;
                btn_collect.setBackgroundResource(R.drawable.play_fullsrcee_collect_true);
                //dosomething to collect
            }

        } else if (i == R.id.btn_share) {
            //分享
            Toast.makeText(getContext(), "点击了分享", Toast.LENGTH_SHORT).show();




        } else if (i == R.id.btn_clarity) {
            CheckClarity();
        } else if (i == R.id.btn_volume) {
            if (currentVolume == 0) {
                //如果是静音
                btn_volume.setBackgroundResource(R.drawable.volume_continue);
                if (lastVolume ==0) {
                    //代表第一次进入就是静音
                    seekbar_volume.setProgress(maxVolume);
                    systemService.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, AudioManager.FLAG_PLAY_SOUND);
                }else {
                    seekbar_volume.setProgress(lastVolume);
                    systemService.setStreamVolume(AudioManager.STREAM_MUSIC, lastVolume, AudioManager.FLAG_PLAY_SOUND);
                }
            } else {
                //如果不是静音
                lastVolume = currentVolume;//保存音量状态
                btn_volume.setBackgroundResource(R.drawable.volume_no);
                systemService.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_PLAY_SOUND);
                seekbar_volume.setProgress(0);
            }
        }
    }

    private void showToast(String text) {
        Toast toast = Toast.makeText(getContext(), "恭喜您收藏成功啦", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View inflate = View.inflate(getContext(), R.layout.item_toast, null);
        ((TextView) inflate.findViewById(R.id.tv_toast)).setText(text);
        toast.setView(inflate);
        toast.show();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        if (id == R.id.surface_container) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    cancelTimer();
                    break;
                case MotionEvent.ACTION_UP:
                    startTimer(6000);
                    if (!changePosition && !changeVolume) {
                        showAndHideUI();
                    }
                    break;
            }
        } else if (id == R.id.progress) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    cancelTimer();
                    break;
                case MotionEvent.ACTION_UP:
                    startTimer(6000);
                    break;
            }
        }
        return super.onTouch(v, event);
    }

    private void showAndHideUI() {
        if (llBottomContainer.getVisibility() == View.VISIBLE) {
            changeUiToClear();
        } else {
            changeUiToShowUiPlaying();
        }
    }

    private void changeUiToNormal() {
        Log.d(TAG, "正常状态正常状态正常状态正常状态正常状态正常状态正常状态正常状态正常状态");
        llTopContainer.setVisibility(View.VISIBLE);//顶部布局
        llBottomContainer.setVisibility(View.VISIBLE);//底部布局
        ivStart.setVisibility(View.VISIBLE);//播放按钮
        pbLoading.setVisibility(View.INVISIBLE);//加载条
        ivThumb.setVisibility(View.VISIBLE);//背景图
        updateIvStartState();
    }

    private void changeUiToShowUiPrepareing() {
        Log.d(TAG, "准备状态准备状态准备状态准备状态准备状态准备状态准备状态准备状态准备状态");
        llTopContainer.setVisibility(View.VISIBLE);
        llBottomContainer.setVisibility(View.VISIBLE);
        ivStart.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.VISIBLE);
        ivThumb.setVisibility(View.VISIBLE);
    }

    private void changeUiToShowUiPlaying() {
        Log.d(TAG, "播放状态播放状态播放状态播放状态播放状态播放状态播放状态播放状态播放状态");
        llTopContainer.setVisibility(View.VISIBLE);
        llBottomContainer.setVisibility(View.VISIBLE);
        ivStart.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.INVISIBLE);
        ivThumb.setVisibility(View.INVISIBLE);
        updateIvStartState();
    }

    private void changeUiToShowUiPause() {
        Log.d(TAG, "暂停状态暂停状态暂停状态暂停状态暂停状态暂停状态暂停状态暂停状态暂停状态");
        llTopContainer.setVisibility(View.VISIBLE);
        llBottomContainer.setVisibility(View.VISIBLE);
        ivStart.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.INVISIBLE);
        ivThumb.setVisibility(View.INVISIBLE);
        updateIvStartState();
    }

    private void changeUiToClear() {
        Log.d(TAG, "清理ui清理ui清理ui清理ui清理ui清理ui清理ui清理ui清理ui清理ui清理ui");
        llTopContainer.setVisibility(View.INVISIBLE);
        llBottomContainer.setVisibility(View.INVISIBLE);
        ivStart.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.INVISIBLE );
        ivThumb.setVisibility(View.INVISIBLE);
    }

    private void changeUiToError() {
        Log.d(TAG, "异常状态异常状态异常状态异常状态异常状态异常状态异常状态异常状态异常状态");
        llTopContainer.setVisibility(View.INVISIBLE);
        llBottomContainer.setVisibility(View.INVISIBLE);
        ivStart.setVisibility(View.INVISIBLE);
        pbLoading.setVisibility(View.INVISIBLE);
        ivThumb.setVisibility(View.INVISIBLE);
        updateIvStartState();
    }

    //更新开始播放的按钮
    private void updateIvStartState() {
        Log.d(TAG, "开始更新播放按钮状态updateIvStartState");
        if (CURRENT_STATE == CURRENT_STATE_PLAYING) {
            ivStart.setBackgroundResource(R.drawable.pla_pause);
        } else if (CURRENT_STATE == CURRENT_STATE_ERROR) {
            ivStart.setBackgroundResource(R.drawable.pla_error);
        } else {
            ivStart.setBackgroundResource(R.drawable.pla_continue);
        }
        AudioManager systemService = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        int streamVolume = systemService.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d(TAG, "streamVolume:" + streamVolume);
        if (streamVolume == 0) {
            btn_volume.setBackgroundResource(R.drawable.volume_no);
        } else {
            btn_volume.setBackgroundResource(R.drawable.volume_continue);
        }
    }

    private void startTimer(int time) {
        cancelTimer();
        mDismissControlViewTimer = new Timer();
        mDismissControlViewTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getContext() != null && getContext() instanceof Activity) {
                    ((Activity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (CURRENT_STATE != CURRENT_STATE_NORMAL
                                    && CURRENT_STATE != CURRENT_STATE_ERROR) {
                                //当前状态不在正常并且不在异常状态时
                                llBottomContainer.setVisibility(View.INVISIBLE);
                                llTopContainer.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        }, time);
    }

    private void cancelTimer() {
        Log.d(TAG, "计时器结束");
        if (mDismissControlViewTimer != null) {
            mDismissControlViewTimer.cancel();
            mDismissControlViewTimer = null;
        }
    }

    public static void setJcBuriedPointStandard(JCBuriedPointStandard jcBuriedPointStandard) {
        jc_BuriedPointStandard = jcBuriedPointStandard;
        JCVideoPlayer.setJcBuriedPoint(jcBuriedPointStandard);
    }

    @Override
    public void onCompletion() {
        super.onCompletion();
        cancelTimer();
    }

    private TextView liuchang,gaoqing,biaoqing;
    private View liuchangLine;
    private static final byte QING_XI_JISHU = 1;
    private static final byte QING_XI_GAOQING = 2;
    private byte currentQingXiDu = QING_XI_GAOQING;
    private PopupWindow mPopupQingXiWindow;
    private void CheckClarity() {
        View view = View.inflate(getContext(), R.layout.popwindow_qingxidu,
                null);
        liuchang = (TextView) view
                .findViewById(R.id.app_video_qingxidu_liuchang);
        gaoqing = (TextView) view
                .findViewById(R.id.app_video_qingxidu_gaoqing);
        view.findViewById(R.id.app_video_qingxidu_gaoqing_underline)
                .setVisibility(View.GONE);
        if (currentQingXiDu == QING_XI_JISHU) {
            liuchang.setBackgroundColor(getResources()
                    .getColor(R.color.media_qingxidu));
        } else if (currentQingXiDu == QING_XI_GAOQING) {
            gaoqing.setBackgroundColor(getResources()
                    .getColor(R.color.media_qingxidu));
        }
        liuchang.setOnClickListener(popWindowListener);
        gaoqing.setOnClickListener(popWindowListener);


        int vieheight = findViewById(R.id.btn_clarity).getMeasuredHeight();
        int vieWidth = findViewById(R.id.btn_clarity).getMeasuredWidth();
        Log.d(TAG, "高为" + vieheight + "宽为" + vieWidth);
        mPopupQingXiWindow = new PopupWindow(view, vieWidth + vieWidth
                * 2 / 8, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupQingXiWindow.setOutsideTouchable(true);
        mPopupQingXiWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupQingXiWindow.showAsDropDown(
                btn_clarity,
                0 - vieWidth * 1 / 8, (int) (0 - vieheight * 2.5));
    }

    public void setQHListener(QHlistener qhListener){
        this.qHlistener = qhListener;
    }
    interface QHlistener{
        void OnQH();
    }
    private QHlistener qHlistener ;
    OnClickListener popWindowListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.app_video_qingxidu_liuchang) {
                //如果是流畅，也就是极速
                if (currentQingXiDu == QING_XI_JISHU) {
                    Log.d(TAG, "点击了流畅");
                    mPopupQingXiWindow.dismiss();
                    return;
                }
                //首先取消popwindow
                currentQingXiDu = QING_XI_JISHU;
                Toast.makeText(getContext(), "您已切换至流畅播放", Toast.LENGTH_SHORT).show();
                liuchang.setBackgroundColor(getResources()
                        .getColor(R.color.media_qingxidu));
                gaoqing.setBackgroundColor(getResources().getColor(R.color.transparent_half_up));
                btn_clarity.setText("流畅");
                mPopupQingXiWindow.dismiss();
                //切换线路
                ChangeVideo();
            }else if(v.getId() == R.id.app_video_qingxidu_gaoqing) {
                if (currentQingXiDu == QING_XI_GAOQING) {
                    Log.d(TAG, "点击了高清");
                    mPopupQingXiWindow.dismiss();
                    return;
                }
                //首先取消popwindow
                currentQingXiDu = QING_XI_GAOQING;
                Toast.makeText(getContext(), "您已切换至高清播放", Toast.LENGTH_SHORT).show();
                gaoqing.setBackgroundColor(getResources()
                        .getColor(R.color.media_qingxidu));
                liuchang.setBackgroundColor(getResources().getColor(R.color.transparent_half_up));
                btn_clarity.setText("高清");
                mPopupQingXiWindow.dismiss();
                //切换线路
            }
        }
    };

    private void ChangeVideo() {
        currentPosition = JCMediaManager.mediaPlayer.getCurrentPosition();
        JCVideoPlayer.releaseAllVideos();
        qHlistener.OnQH();

    }

    private String newurl = "http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4";
}
