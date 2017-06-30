package com.daiduo.lightning.levels;

import com.daiduo.lightning.Assets;
import com.daiduo.lightning.actors.mobs.Mob;
import com.daiduo.lightning.actors.mobs.npcs.Shopkeeper;
import com.daiduo.lightning.items.Gold;
import com.daiduo.lightning.levels.painters.Painter;

import java.util.Arrays;

/**
 * Created by qq on 2017/6/30.
 */

public class NewLevel extends Level {

    public static final int SIZE = 7;
    public static int bottomleft = SIZE*10 +1;
    public static int bottomleftvertical = SIZE*(10/2)+1;
    public static int test = (SIZE/3 + 1) * (10/2) + 1;
    {
        color1 = 0x801500;
        color2 = 0xa68521;

    }

    private int pedestal;

    @Override
    public String tilesTex() {
        return Assets.TILES_SEWERS;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_CAVES;
    }

    @Override
    protected boolean build() {

        Arrays.fill( map, Terrain.WALL );
        //starting room
        Painter.fill( this, 1, 1, SIZE, SIZE, Terrain.EMPTY );

        //2nd room
        Painter.fill(this, entrance + 11, 1, 4,6, Terrain.EMPTY_SP);

        Painter.fill(this, entrance + 6, SIZE, 8,1, Terrain.EMPTY);
        Painter.fill(this, entrance+11,SIZE,3,1, Terrain.EMPTY_SP);


        entrance = SIZE * 10 + SIZE / 2 + 1;
        map[entrance] = Terrain.ENTRANCE;
        map[bottomleft +1] = Terrain.BOOKSHELF;


        map[entrance + 6] = Terrain.DOOR;
        map[entrance + 10] = Terrain.ALCHEMY;



        pedestal = (SIZE / 2 + 1) * (10 + 1);
        exit = pedestal;
        map[exit] = Terrain.EXIT;


        feeling = Feeling.NONE;


        return true;
    }

    @Override
    protected void decorate() {

    }

    @Override
    protected void createMobs() {
        createShopKeeper();
    }

    protected void createShopKeeper(){
        Mob shopkeeper = new Shopkeeper();
        shopkeeper.pos = pedestal + 1;
        mobs.add(shopkeeper);


    }


    @Override
    protected void createItems() {
        drop( new Gold(), bottomleft );
    }



    @Override
    public int randomRespawnCell() {
        return -1;
    }

    @Override
    public String tileName( int tile ) {
        switch (tile) {

            default:
                return super.tileName( tile );
        }
    }

    @Override
    public String tileDesc(int tile) {
        switch (tile) {
            default:
                return super.tileDesc( tile );
        }
    }

}
