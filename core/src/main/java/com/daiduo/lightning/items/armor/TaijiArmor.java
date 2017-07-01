package com.daiduo.lightning.items.armor;

import com.daiduo.lightning.sprites.ItemSpriteSheet;

import static java.lang.Boolean.TRUE;

/**
 * Created by badcw on 2017/7/1.
 */



public class TaijiArmor extends Armor {
    {
        image = ItemSpriteSheet.ARMOR_TAIJI;

        bones = true ;

    }

    public TaijiArmor() {
        super( 1 );
    }

    public int STRReq () {
        return 7;
    }


    @Override
    public Armor inscribe() {
        return super.inscribe();
    }
}
