package com.daiduo.lightning.levels;


import com.daiduo.lightning.Assets;
import com.daiduo.lightning.actors.Actor;
import com.daiduo.lightning.actors.mobs.Mob;
import com.daiduo.lightning.actors.mobs.npcs.Alchemist;
import com.daiduo.lightning.actors.mobs.npcs.StallSeller;
import com.daiduo.lightning.items.Item;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class FirstLevel extends Level {

    private enum State{
        START
    }
    private State state;
    private ArrayList<Item> storedItems = new ArrayList<>();
    private static final String STATE	        = "state";
    private static final String STORED_ITEMS    = "storeditems";


    {
        color1 = 0x801500;
        color2 = 0xa68521;

    }
    @Override
    public String tilesTex() {
        return Assets.TILES_SEWERS;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_CAVES;
    }

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle(bundle);
        bundle.put( STATE, state );
        bundle.put( STORED_ITEMS, storedItems);
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle(bundle);
        state = bundle.getEnum( STATE, State.class );
        for (Bundlable item : bundle.getCollection(STORED_ITEMS)){
            storedItems.add( (Item)item );
        }
    }

    @Override
    protected boolean build() {

        map = MAP_START.clone();
        decorate();

        buildFlagMaps();
        cleanWalls();

        state = State.START;
        entrance = 5+2*32;
        exit = 5+28*32;


        return true;
    }

    @Override
    protected void decorate() {
        //do nothing, all decorations are hard-coded.
    }

    @Override
    protected void createMobs() {
        createStallSeller();
        createAlchemist();
    }

    protected void createStallSeller(){
        Mob stallSeller = new StallSeller();
        stallSeller.pos = 7+2*32;
        mobs.add(stallSeller);
    }


    protected void createAlchemist(){
        Mob alchemist = new Alchemist();
        alchemist.pos = 7-4+2*32;
        mobs.add(alchemist);
    }
    
    
    public Actor respawner() {
        return null;
    }

    @Override
    protected void createItems() {
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



    private static final int W = Terrain.WALL;
    private static final int D = Terrain.DOOR;
    private static final int L = Terrain.LOCKED_DOOR;
    private static final int e = Terrain.EMPTY;
    private static final int S = Terrain.SIGN;

    private static final int T = Terrain.INACTIVE_TRAP;

    private static final int E = Terrain.ENTRANCE;
    private static final int X = Terrain.EXIT;

    private static final int M = Terrain.WALL_DECO;
    private static final int P = Terrain.PEDESTAL;

    private static final int[] MAP_START =
            {       W, W, W, W, W, M, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, e, E, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, S, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, W, D, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, W, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, M, W, W, e, W, W, M, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, M, W, W, e, W, W, M, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, M, W, W, e, W, W, M, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, M, W, W, e, W, W, M, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, e, e, e, W, e, W, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, M, W, W, e, W, W, M, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, W, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, M, W, D, W, M, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, e, e, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, e, e, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, e, e, X, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, e, e, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, e, e, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
                    W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W};


}


