package com.daiduo.lightning.actors.mobs.npcs;

import com.daiduo.lightning.Assets;
import com.daiduo.lightning.Dungeon;
import com.daiduo.lightning.actors.Char;
import com.daiduo.lightning.actors.buffs.Buff;
import com.daiduo.lightning.items.Item;
import com.daiduo.lightning.items.potions.PotionOfHealing;
import com.daiduo.lightning.items.potions.PotionOfStrength;
import com.daiduo.lightning.messages.Messages;
import com.daiduo.lightning.scenes.GameScene;
import com.daiduo.lightning.sprites.BlacksmithSprite;
import com.daiduo.lightning.windows.WndAlchemist;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

/**
 * Created by badcw on 2017/7/27.
 */

public class Alchemist extends NPC {
    {
        spriteClass = BlacksmithSprite.class;

        properties.add(Property.IMMOVABLE);
    }

    @Override
    protected boolean act() {
        throwItem();
        return super.act();
    }

    @Override
    public boolean interact() {
        GameScene.show( new WndAlchemist( this, Dungeon.hero ) );
        return false;
    }
    
    public static String verify(Item item1, Item item2 ) {
        
        if (item1.cursed || item2.cursed) {
            return Messages.get(Blacksmith.class, "cursed");
        }

        if (item1.level() < 0 || item2.level() < 0) {
            return Messages.get(Blacksmith.class, "degraded");
        }

        return null;
    }

    public static void mix( Item item1, Item item2 ) {
                
        item1.detach( Dungeon.hero.belongings.backpack );
        item2.detach( Dungeon.hero.belongings.backpack );
        
        if (item1 instanceof PotionOfHealing && item2 instanceof PotionOfHealing){
            PotionOfStrength S =new PotionOfStrength();
            Dungeon.level.drop( S, Dungeon.hero.pos ).sprite.drop();

        }else if(item1 instanceof PotionOfStrength&& item2 instanceof PotionOfStrength){
            PotionOfHealing S =new PotionOfHealing();
            Dungeon.level.drop( S, Dungeon.hero.pos ).sprite.drop();
        }
        
        
        
        
        Sample.INSTANCE.play( Assets.SND_EVOKE );
        
        Quest.reforged = true;

    }

    @Override
    public int defenseSkill( Char enemy ) {
        return 1000;
    }

    @Override
    public void damage( int dmg, Object src ) {
    }

    @Override
    public void add( Buff buff ) {
    }

    @Override
    public boolean reset() {
        return true;
    }

    public static class Quest {

        private static boolean spawned;

        private static boolean alternative;
        private static boolean given;
        private static boolean completed;
        private static boolean reforged;

        public static void reset() {
            spawned = false;
            given = false;
            completed = true;
            reforged = false;
        }

        private static final String NODE = "blacksmith";

        private static final String SPAWNED = "spawned";
        private static final String ALTERNATIVE = "alternative";
        private static final String GIVEN = "given";
        private static final String COMPLETED = "completed";
        private static final String REFORGED = "reforged";

        public static void storeInBundle(Bundle bundle) {

            Bundle node = new Bundle();

            node.put(SPAWNED, spawned);

            if (spawned) {
                node.put(ALTERNATIVE, alternative);
                node.put(GIVEN, given);
                node.put(COMPLETED, completed);
                node.put(REFORGED, reforged);
            }

            bundle.put(NODE, node);
        }

        public static void restoreFromBundle(Bundle bundle) {

            Bundle node = bundle.getBundle(NODE);

            if (!node.isNull() && (spawned = node.getBoolean(SPAWNED))) {
                alternative = node.getBoolean(ALTERNATIVE);
                given = node.getBoolean(GIVEN);
                completed = node.getBoolean(COMPLETED);
                reforged = node.getBoolean(REFORGED);
            } else {
                reset();
            }
        }
    }

}
