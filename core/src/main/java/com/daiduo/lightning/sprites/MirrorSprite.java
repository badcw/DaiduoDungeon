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
package com.daiduo.lightning.sprites;

import com.daiduo.lightning.Dungeon;
import com.daiduo.lightning.actors.Char;
import com.daiduo.lightning.actors.mobs.npcs.MirrorImage;
import com.watabou.noosa.TextureFilm;

public class MirrorSprite extends MobSprite {
	
	private static final int FRAME_WIDTH	= 14;
	private static final int FRAME_HEIGHT	= 15;
	
	public MirrorSprite() {
		super();
		
		texture( Dungeon.hero.heroClass.spritesheet() );
		updateArmor( 0 );
		idle();
	}
	
	@Override
	public void link( Char ch ) {
		super.link( ch );
		updateArmor( ((MirrorImage)ch).tier );
	}
	
	public void updateArmor( int tier ) {
		TextureFilm film = new TextureFilm( HeroSprite.tiers(), tier, FRAME_WIDTH, FRAME_HEIGHT );

		idle = new Animation( 1, true );
		idle.frames( film, 0, 0, 0, 1, 0, 0, 1, 1 );

		run = new Animation( 20, true );
		switch(Dungeon.hero.heroClass) {
			case WARRIOR:
			case HUNTRESS:
			case MAGE:
				run.frames(film, 2, 3, 4, 5, 6, 7);
				break;
			case ROGUE:
				run.frames(film, 2, 3, 4, 5, 6);
				break;
		}

		die = new Animation( 20, false );
		switch(Dungeon.hero.heroClass) {
			case WARRIOR:
				die.frames(film, 8, 9, 10, 11, 12, 11);
				break;
			case ROGUE:
				die.frames(film, 7, 8, 9, 10, 9);
				break;
			case HUNTRESS:
				die.frames(film, 8, 9, 10, 11, 12, 11);
				break;
			case MAGE:
				die.frames(film, 8, 9, 10, 11, 12, 11);
				break;
		}

		attack = new Animation(15, false);
		attack.frames(film, 13, 14, 15, 0);

		idle();
	}
}
