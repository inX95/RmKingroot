package rmkt.inx95.rmkingroot;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by inx95 on 16-10-20.
 */

public class ShellUtil {
    public static boolean execute(ArrayList<String> commands) {
        boolean retval = false;

        try {
            if (null != commands && commands.size() > 0) {
                Process process = Runtime.getRuntime().exec("su");

                DataOutputStream os = new DataOutputStream(process.getOutputStream());

                for (String currCommand : commands) {
                    os.writeBytes(currCommand + "\n");
                    os.flush();
                }

                os.writeBytes("exit\n");
                os.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                int read;
                char[] buffer = new char[4096];
                StringBuffer output = new StringBuffer();
                while ((read = reader.read(buffer)) > 0) {
                    output.append(buffer, 0, read);
                }
                reader.close();

                try {
                    int suProcessRetval = process.waitFor();
                    if (255 != suProcessRetval) {
                        retval = true;
                    } else {
                        retval = false;
                    }
                    System.out.println("from android shell: "+output.toString()) ;
                } catch (Exception ex) {
                }
            }
        } catch (IOException ex) {
            Log.w("ROOT", "Can't get root access", ex);
        } catch (SecurityException ex) {
            Log.w("ROOT", "Can't get root access", ex);
        } catch (Exception ex) {
            Log.w("ROOT", "Error executing internal operation", ex);
        }

        return retval;
    }
}
