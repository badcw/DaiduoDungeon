package com.daiduo.lightning.items.artifacts;

import com.daiduo.lightning.Dungeon;
import com.daiduo.lightning.actors.buffs.Buff;
import com.daiduo.lightning.actors.hero.Hero;
import com.daiduo.lightning.actors.hero.HeroClass;
import com.daiduo.lightning.items.Item;
import com.daiduo.lightning.items.scrolls.ScrollOfTeleportation;
import com.daiduo.lightning.scenes.GameScene;
import com.daiduo.lightning.scenes.InterlevelScene;
import com.daiduo.lightning.sprites.ItemSpriteSheet;
import com.watabou.noosa.Game;

import java.util.ArrayList;

/**
 * Created by badcw on 2017/7/2.
 */

public class Transpotation extends Artifact {

    public static final String AC_RETURN	= "RETURN";
    {
        image = ItemSpriteSheet.TRANSPORTATION;

    }

    public int returnDepth	= -1;
    public int returnPos1 = 100;
    public int returnPos2 = 5+2*32;
    
    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_RETURN );

        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );
            if (action == AC_RETURN ) {
                if (returnDepth == Dungeon.depth) {
                    ScrollOfTeleportation.appear(hero, 100);
                    Dungeon.level.press(100, hero);
                    Dungeon.observe();
                    GameScene.updateFog();
                } else {

                    Buff buff = Dungeon.hero.buff(TimekeepersHourglass.timeFreeze.class);
                    if (buff != null) buff.detach();

                    InterlevelScene.mode = InterlevelScene.Mode.RETURN;
                    InterlevelScene.returnDepth = returnDepth;
                    if (Dungeon.hero.heroClass == HeroClass.WARRIOR || Dungeon.hero.heroClass == HeroClass.ROGUE) {
                        InterlevelScene.returnPos = returnPos1;
                        Game.switchScene(InterlevelScene.class);
                    }else {
                        InterlevelScene.returnPos = returnPos2;
                        Game.switchScene(InterlevelScene.class);
                    }
                }
            }


    }




    @Override
    protected ArtifactBuff passiveBuff() {
        return new chaliceRegen();
    }

    public class chaliceRegen extends ArtifactBuff {

    }

    @Override
    public Item upgrade() {
        return super.upgrade();
    }

}
