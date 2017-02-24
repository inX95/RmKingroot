package rmkt.inx95.rmkingroot;

import android.content.Context;
import android.os.AsyncTask;
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

	private class ShellTask extends AsyncTask < ArrayList<String>, Void, Boolean >
	{

		@SafeVarargs
		@Override
		protected final Boolean doInBackground ( ArrayList < String >... lines )
		{
			return ShellUtil.execute ( lines [ 0 ] );
		}

		@Override
		protected void onPostExecute ( Boolean ShellTaskResult )
		{
			if ( ShellTaskResult )
			{
				show.setText ( "成功，请退出后安装 SuperSu 等软件。" );
			}
			else
			{
				show.setText ( "失败，真是可怕！" );
			}
		}
	}

    private static final String mTargetDir = Environment.getExternalStorageDirectory().getPath()+File.separator+"mrw";
    private static final String mSu = "su";
    private static final String mBusybox = "busybox";

	private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		show = (TextView) findViewById(R.id.tv_show);
        ImageButton start = (ImageButton) findViewById(R.id.ib_start);

		final ShellTask task = new ShellTask ();

        copyFileToSD(this);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
					show.setText("正在进行清理 KingRoot 作业，请不要进行任何操作⋯⋯");
                    InputStream is = getApplicationContext().getAssets().open("clean.sh");
					//noinspection unchecked
					task.execute ( FileUtil.parseInputStream(is) );
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
