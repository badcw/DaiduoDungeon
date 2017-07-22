package com.daiduo.lightning.scenes;

import com.daiduo.lightning.Assets;
import com.daiduo.lightning.Badges;
import com.daiduo.lightning.Dungeon;
import com.daiduo.lightning.ShatteredPixelDungeon;
import com.daiduo.lightning.effects.Flare;
import com.daiduo.lightning.effects.Speck;
import com.daiduo.lightning.items.Amulet;
import com.daiduo.lightning.messages.Messages;
import com.daiduo.lightning.ui.Archs;
import com.daiduo.lightning.ui.RedButton;
import com.daiduo.lightning.ui.RenderedTextMultiline;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

/**
 * Created by badcw on 2017/7/21.
 */

public class BlankScene extends PixelScene {

    @Override
    protected void onBackPressed() {
        ShatteredPixelDungeon.switchNoFade(TitleScene.class);
    }

}

