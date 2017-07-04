package com.daiduo.lightning.actors.mobs.npcs;

import com.daiduo.lightning.Dungeon;
import com.daiduo.lightning.actors.buffs.Buff;
import com.daiduo.lightning.actors.mobs.Mob;
import com.daiduo.lightning.effects.CellEmitter;
import com.daiduo.lightning.effects.particles.ElmoParticle;
import com.daiduo.lightning.items.Heap;
import com.daiduo.lightning.items.Item;
import com.daiduo.lightning.messages.Messages;
import com.daiduo.lightning.scenes.GameScene;
import com.daiduo.lightning.sprites.ShopkeeperSprite;
import com.daiduo.lightning.sprites.StallSellerSprite;
import com.daiduo.lightning.windows.WndBag;
import com.daiduo.lightning.windows.WndTradeItem;

/**
 * Created by qq on 2017/7/4.
 */

public class StallSeller extends NPC {

    {
        spriteClass = StallSellerSprite.class;

        properties.add(Property.IMMOVABLE);
    }

    @Override
    protected boolean act() {

        throwItem();

        sprite.turnTo( pos, Dungeon.hero.pos );
        spend( TICK );
        return true;
    }

    @Override
    public void damage( int dmg, Object src ) {
        flee();
    }

    @Override
    public void add( Buff buff ) {
        flee();
    }

    public void flee() {
        for (Heap heap: Dungeon.level.heaps.values()) {
            if (heap.type == Heap.Type.FOR_SALE) {
                CellEmitter.get( heap.pos ).burst( ElmoParticle.FACTORY, 4 );
                heap.destroy();
            }
        }

        destroy();

        sprite.killAndErase();
        CellEmitter.get( pos ).burst( ElmoParticle.FACTORY, 6 );
    }

    @Override
    public boolean reset() {
        return true;
    }

    public static WndBag sell() {
        return GameScene.selectItem( itemSelector, WndBag.Mode.FOR_SALE, Messages.get(Shopkeeper.class, "sell"));
    }

    private static WndBag.Listener itemSelector = new WndBag.Listener() {
        @Override
        public void onSelect( Item item ) {
            if (item != null) {
                WndBag parentWnd = sell();
                GameScene.show( new WndTradeItem( item, parentWnd ) );
            }
        }
    };

    @Override
    public boolean interact() {
        sell();
        return false;
    }
}
