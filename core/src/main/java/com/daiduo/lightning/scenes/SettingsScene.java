package com.daiduo.lightning.scenes;

import com.daiduo.lightning.ShatteredPixelDungeon;
import com.daiduo.lightning.classes.Challenges;
import com.daiduo.lightning.messages.Messages;
import com.daiduo.lightning.ui.Archs;
import com.daiduo.lightning.ui.CheckBox;
import com.daiduo.lightning.ui.ExitButton;
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

    private StartScene.GameButton settings1;

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

        ExitButton exitButton = new ExitButton();
        exitButton.setPos( w - exitButton.width() ,0 );
        add( exitButton );

        settings1 = new StartScene.GameButton( Messages.get(this,"settings")){
            @Override
            protected void onClick(){

            }
        };
    }



}
