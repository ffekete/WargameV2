package com.mygdx.mechwargame.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Environment;

import java.io.IOException;
import java.io.Writer;

public class SaveLoadManager {

    public static void save() {

        FileHandle f = Gdx.files.external(Gdx.files.getExternalStoragePath() + "GalacticMercenaries/save.sav");

        try {
            f.file().getParentFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            f.file().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer w = f.writer(false);
        try {
            w.append("as");
            w.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
