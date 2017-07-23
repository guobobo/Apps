package com.example.iu.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.base.BaseActivity;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginSuccessActivity extends BaseActivity {


    @Bind(R.id.login_success_backImage)
    ImageView loginSuccessBackImage;
    @Bind(R.id.avatarImage_loginsuccess)
    ImageView avatarImageLoginsuccess;
    @Bind(R.id.linear_loginsuccess_tuoxiang)
    LinearLayout linearLoginsuccessTuoxiang;
    @Bind(R.id.mNickname_loginsuccess)
    TextView mNicknameLoginsuccess;
    @Bind(R.id.relative_loginsuccess_nicheng)
    RelativeLayout relativeLoginsuccessNicheng;
    @Bind(R.id.Btn_loginsuccess)
    Button BtnLoginsuccess;
    //avatarImage_loginsuccess

    private File appDir;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_success;
    }

    @Override
    public void initView() {

        sharedPreferences = getSharedPreferences("uri", MODE_PRIVATE);
        edit = sharedPreferences.edit();

        Intent intent = getIntent();

        String mNickname = intent.getStringExtra("mNickname");

        mNicknameLoginsuccess.setText(mNickname);
    }

    @OnClick({R.id.login_success_backImage, R.id.linear_loginsuccess_tuoxiang, R.id.relative_loginsuccess_nicheng, R.id.Btn_loginsuccess})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_success_backImage:
                finish();
                break;
            case R.id.linear_loginsuccess_tuoxiang:


                View inflate = LayoutInflater.from(this).inflate(R.layout.personal_paizhao_dialog, null);
                final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,350,true);
                popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
                TextView xiangce = (TextView) inflate.findViewById(R.id.xiangce);
                TextView paiyizhang = (TextView) inflate.findViewById(R.id.paiyizhang);
                TextView quxiao = (TextView) inflate.findViewById(R.id.quxiao);
                xiangce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getImageFromAlbum();
                    }
                });

                paiyizhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getImageFromCamera();
                    }
                });
                quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


                break;
            case R.id.relative_loginsuccess_nicheng:
                break;
            case R.id.Btn_loginsuccess:

                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);

                SharedPreferences.Editor edit = login.edit();

                edit.clear();

                boolean commit = edit.commit();

                if (commit) {
                    Toast.makeText(LoginSuccessActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginSuccessActivity.this, PersonalActivity.class));
                    finish();
                }

                break;
        }
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Uri uri = data.getData();
            edit.putString("uriForCamera",String.valueOf(uri));
            edit.commit();
            cropRawPhoto(uri);
        } else if (requestCode == 2000) {
            Uri uri = Uri.parse(sharedPreferences.getString("uriForCamera",""));
            cropRawPhoto(uri);

        }else if(requestCode == 3000) {
            Bundle extras = data.getExtras();
            if(extras !=null) {
                Bitmap data1 = extras.getParcelable("data");
                avatarImageLoginsuccess.setImageBitmap(data1);
//                data1.recycle();
            }
        }
    }



    //    从本地相册 获取图片
    protected void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 1000);
    }

    //    从照相机获取图片
    protected void getImageFromCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uriForCamera = Uri.fromFile(createImageStoragePath());
        edit.putString("uriForCamera",String.valueOf(uriForCamera));
        edit.commit();

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForCamera);
        startActivityForResult(intent, 2000);
    }

    private File createImageStoragePath() {
        if (hasSdcard()) {
            appDir = new File("/sdcard/testImage/");
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date =  new Date();
            String str = simpleDateFormat.format(date);
            String fileName = str + ".jpg";
            File file = new File(appDir, fileName);
            return file;
        } else {
            return null;
        }
    }


    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


}