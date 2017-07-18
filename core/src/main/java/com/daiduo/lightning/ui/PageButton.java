package com.daiduo.lightning.ui;

import com.daiduo.lightning.Assets;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Button;

/**
 * Created by badcw on 2017/7/17.
 */

public class PageButton extends Button {

    protected Image image;

    public PageButton() {
        super();

        width = image.width;
        height = image.height;
    }

    @Override
    protected void createChildren() {
        super.createChildren();

        image = Page.TWO.get();
        add( image );
    }

    @Override
    protected void layout() {
        super.layout();

        image.x = x;
        image.y = y;
    }

    @Override
    protected void onTouchDown() {
        image.brightness( 1.5f );
        Sample.INSTANCE.play( Assets.SND_CLICK );
    }

    @Override
    protected void onTouchUp() {
        image.resetColor();
    }


}
