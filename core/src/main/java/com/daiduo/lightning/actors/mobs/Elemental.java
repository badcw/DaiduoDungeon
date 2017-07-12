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
import com.daiduo.lightning.actors.buffs.Buff;
import com.daiduo.lightning.actors.buffs.Burning;
import com.daiduo.lightning.actors.buffs.Chill;
import com.daiduo.lightning.actors.buffs.Frost;
import com.daiduo.lightning.effects.Speck;
import com.daiduo.lightning.items.potions.PotionOfLiquidFlame;
import com.daiduo.lightning.items.wands.WandOfFireblast;
import com.daiduo.lightning.items.weapon.enchantments.Blazing;
import com.daiduo.lightning.levels.Level;
import com.daiduo.lightning.sprites.ElementalSprite;
import com.watabou.utils.Random;

import java.util.HashSet;

public class Elemental extends Mob {

	{
		spriteClass = ElementalSprite.class;
		
		HP = HT = 80;
		defenseSkill = 30;
		
		EXP = 10;
		
		flying = true;
		
		loot = new PotionOfLiquidFlame();
		lootChance = 0.1f;

		properties.add(Property.DEMONIC);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 16, 26 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 25;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 5);
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		if (Random.Int( 2 ) == 0) {
			Buff.affect( enemy, Burning.class ).reignite( enemy );
		}
		
		return damage;
	}
	
	@Override
	public void add( Buff buff ) {
		if (buff instanceof Burning) {
			if (HP < HT) {
				HP++;
				sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
			}
		} else if (buff instanceof Frost || buff instanceof Chill) {
				if (Level.water[this.pos])
					damage( Random.NormalIntRange( HT / 2, HT ), buff );
				else
					damage( Random.NormalIntRange( 1, HT * 2 / 3 ), buff );
		} else {
			super.add( buff );
		}
	}
	
	private static final HashSet<Class<?>> IMMUNITIES = new HashSet<>();
	static {
		IMMUNITIES.add( Burning.class );
		IMMUNITIES.add( Blazing.class );
		IMMUNITIES.add( WandOfFireblast.class );
	}
	
	@Override
	public HashSet<Class<?>> immunities() {
		return IMMUNITIES;
	}
}
