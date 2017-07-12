/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2016 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.daiduo.lightning.actors.mobs;

import com.daiduo.lightning.actors.Char;
import com.daiduo.lightning.items.food.MysteryMeat;
import com.daiduo.lightning.sprites.CrabSprite;
import com.watabou.utils.Random;

public class Crab extends Mob {

	{
		spriteClass = CrabSprite.class;
		
		HP = HT = 40;
		defenseSkill = 7;
		
		EXP = 4;
		maxLvl = 10;
		
		loot = new MysteryMeat();
		lootChance = 0.167f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 2 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 1;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 4);
	}
}
