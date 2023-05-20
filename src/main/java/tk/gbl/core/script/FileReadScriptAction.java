package tk.gbl.core.script;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/6/12
 * Time: 17:04
 *
 * @author Tian.Dong
 */
public class FileReadScriptAction extends ScriptAction {

    File file;

    public FileReadScriptAction(String filePath) {
        file = new File(filePath);
    }

    @Override
    public void doAction(GameClient gameClient) throws IOException {
        if (file == null || !file.exists()) {
            return;
        }
        List<String> scriptStringList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                scriptStringList.add(line.trim());
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        for (String scriptString : scriptStringList) {
            gameClient.doDoDoScriptAction(scriptString);
        }
    }
}
