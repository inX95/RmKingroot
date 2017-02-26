package rmkt.inx95.rmkingroot;

import android.content.Context;
import android.os.SystemClock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by inx95 on 16-10-20.
 */

public final class FileUtils {

    public static ArrayList<String> parseFile(String filePath) {
        return parseFile(new File(filePath));
    }

    public static ArrayList<String> parseFile(File file) {
        ArrayList<String> lines = new ArrayList<>();
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    public static void copyAssetsToFile(Context context, String assetFileName, String externalFilePath) {
        try {
            File file = new File(externalFilePath);
            if (file.exists()) {
                File tempFile = new File(file.getAbsolutePath() + SystemClock.uptimeMillis());
                file.renameTo(tempFile);
                tempFile.delete();
            }
            InputStream in = context.getApplicationContext().getAssets().open(assetFileName);
            OutputStream out = new FileOutputStream(externalFilePath);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> parseInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

}
