package com.daiduo.lightning.ui;

import com.daiduo.lightning.Assets;
import com.daiduo.lightning.actors.hero.HeroClass;
import com.watabou.noosa.Image;

/**
 * Created by badcw on 2017/7/17.
 */

public enum Page {

    ONE,
    TWO,
    THREE;

    public Image get() {
        return get( this );
    }

    public static Image get( Page type ) {
        Image icon = new Image( Assets.PAGES );
        switch (type) {
            case ONE:
                icon.frame( icon.texture.uvRect( 0, 0, 8, 8 ) );
                break;
            case TWO:
                icon.frame( icon.texture.uvRect( 0, 0, 16, 16 ) );
                break;
            case THREE:
                icon.frame( icon.texture.uvRect( 0, 8, 7, 13 ) );
                break;
        }
        return icon;
    }

}
