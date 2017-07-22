package com.daiduo.lightning.scenes;

import com.daiduo.lightning.ShatteredPixelDungeon;
import com.daiduo.lightning.classes.Challenges;
import com.daiduo.lightning.messages.Messages;
import com.daiduo.lightning.ui.Archs;
import com.daiduo.lightning.ui.CheckBox;
import com.daiduo.lightning.ui.OptionSlider;
import com.daiduo.lightning.ui.Window;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Group;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.audio.Music;

import java.util.ArrayList;

import static android.R.attr.checked;
import static com.daiduo.lightning.ui.Window.TITLE_COLOR;

/**
 * Created by qq on 2017/7/21.
 */

public class SettingsScene extends BlankScene {
    @Override
    public void create() {
        super.create();

        uiCamera.visible = false;

        int w = Camera.main.width;
        int h = Camera.main.height;

        Archs archs = new Archs();
        archs.setSize(w, h);
        add(archs);

        fadeIn();
    }
    private class AudioTab extends Group {
        public AudioTab() {
            OptionSlider musicVol = new OptionSlider(Messages.get(this, "music_vol"), "0", "10", 0, 10) {
                @Override
                protected void onChange() {
                    Music.INSTANCE.volume(getSelectedValue() / 10f);
                    ShatteredPixelDungeon.musicVol(getSelectedValue());
                }
            };
            musicVol.setSelectedValue(ShatteredPixelDungeon.musicVol());
            musicVol.setRect(0, 0, 112, 25);
            add(musicVol);

        }
    }
}
