package rmkt.inx95.rmkingroot;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String mTargetDir = Environment.getExternalStorageDirectory().getPath()+File.separator+"mrw";
    private static final String mSu = "su";
    private static final String mBusybox = "busybox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView show = (TextView) findViewById(R.id.tv_show);
        ImageButton start = (ImageButton) findViewById(R.id.ib_start);

        copyFileToSD(this);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream is = getApplicationContext().getAssets().open("clean.sh");
                    ArrayList<String> lines = FileUtil.parseInputStream(is);
                    ShellUtil.execute(lines);
                    show.setText("成功,请退出后安装SuperSu等软件.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    void copyFileToSD(Context context) {
        File file = new File(mTargetDir);
        if (file.exists() && file.isDirectory()) return;
        file.mkdir();
        String sourceAbiDir = getABI() + File.separator;
        FileUtil.copyAssetsToFile(context, sourceAbiDir + mSu, mTargetDir + File.separator + mSu);
        FileUtil.copyAssetsToFile(context, mBusybox, mTargetDir + File.separator + mBusybox);
    }


    private String getABI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS[0];
        }
        return Build.CPU_ABI;
    }


}
