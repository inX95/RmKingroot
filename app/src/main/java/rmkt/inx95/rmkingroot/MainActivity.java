package rmkt.inx95.rmkingroot;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String mTargetDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "mrw";
    private static final String SU = "su";
    private static final String BUSYBOX = "busybox";
    private static final String HINT_WORKING = "正在进行清理 KingRoot 作业";
    private static final String HINT_SUCCESS = "成功，跳转到安装 SuperSu ！";
    private static final String HINT_FAILED = "失败，真是可怕。。。\n请退出检查是否授予Root权限！";

    private Handler mHandler = new Handler();
    private TextView mShow;
    private Dialog mWorkingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyFileToSD(this);
        initDialog();
        setContentView(R.layout.activity_main);
        mShow = (TextView) findViewById(R.id.tv_show);
        final ImageButton start = (ImageButton) findViewById(R.id.ib_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHint(HINT_WORKING);
                start.setEnabled(false);
                mWorkingDialog.show();
                mShellTask.execute();
            }
        });
    }

    private ShellTask mShellTask = new ShellTask();


    private class ShellTask extends AsyncTask<ArrayList<Void>, Void, Boolean> {

        @SafeVarargs
        @Override
        protected final Boolean doInBackground(ArrayList<Void>... parameters) {

            InputStream is = null;
            try {
                is = getApplicationContext().getAssets().open("clean.sh");
                return ShellUtils.execute(FileUtils.parseInputStream(is));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean shellTaskResult) {
            mWorkingDialog.dismiss();
            showHint(shellTaskResult ?
                    HINT_SUCCESS :
                    HINT_FAILED);
        }
    }

    private void initDialog() {
        mWorkingDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("清理")
                .setMessage(HINT_WORKING)
                .setCancelable(false)
                .create();
    }

    private void showHint(final String hint) {
        if (TextUtils.isEmpty(hint))
            return;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mShow.setText(hint);
                Toast.makeText(MainActivity.this, hint, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void copyFileToSD(Context context) {
        File file = new File(mTargetDir);
        if (file.exists() && file.isDirectory()) return;
        file.mkdir();
        String sourceAbiDir = getABI() + File.separator;
        FileUtils.copyAssetsToFile(context, sourceAbiDir + SU, mTargetDir + File.separator + SU);
        FileUtils.copyAssetsToFile(context, BUSYBOX, mTargetDir + File.separator + BUSYBOX);
    }


    private String getABI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS[0];
        }
        return Build.CPU_ABI;
    }


}
