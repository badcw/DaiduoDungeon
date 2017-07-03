package com.daiduo.lightning.items.artifacts;

import com.daiduo.lightning.Assets;
import com.daiduo.lightning.Dungeon;
import com.daiduo.lightning.actors.Actor;
import com.daiduo.lightning.actors.buffs.Buff;
import com.daiduo.lightning.actors.buffs.LockedFloor;
import com.daiduo.lightning.actors.hero.Hero;
import com.daiduo.lightning.actors.mobs.Mob;
import com.daiduo.lightning.items.Item;
import com.daiduo.lightning.items.scrolls.ScrollOfTeleportation;
import com.daiduo.lightning.messages.Messages;
import com.daiduo.lightning.scenes.GameScene;
import com.daiduo.lightning.scenes.InterlevelScene;
import com.daiduo.lightning.sprites.ItemSpriteSheet;
import com.daiduo.lightning.ui.QuickSlotButton;
import com.daiduo.lightning.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

import static com.daiduo.lightning.items.artifacts.LloydsBeacon.AC_SET;
import static com.daiduo.lightning.scenes.InterlevelScene.returnDepth;
import static com.daiduo.lightning.scenes.InterlevelScene.returnPos;

/**
 * Created by badcw on 2017/7/2.
 */

public class Transpotation extends Artifact {

    public static final String AC_RETURN	= "RETURN";
    {
        image = ItemSpriteSheet.TRANSPORTATION;

    }

    private static final String DEPTH	= "depth";
    private static final String POS		= "pos";

    public int returnDepth	= -1;
    public int returnPos = 100;

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put( DEPTH, returnDepth );
        bundle.put( POS, returnPos );

    }



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
                    InterlevelScene.returnPos = returnPos;
                    Game.switchScene(InterlevelScene.class);
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
