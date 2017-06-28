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
package com.daiduo.lightning.ui;

import android.opengl.GLES20;

import com.daiduo.lightning.Assets;
import com.watabou.noosa.Game;
import com.watabou.noosa.NoosaScript;
import com.watabou.noosa.NoosaScriptNoLighting;
import com.watabou.noosa.SkinnedBlock;
import com.watabou.noosa.ui.Component;

import static com.watabou.noosa.Game.dispHeight;
import static com.watabou.noosa.Game.dispWidth;

public class Archs extends Component {

	private SkinnedBlock arcsBg;
	private static float offsB = 0;


	public boolean reversed = false;

	@Override
	protected void createChildren() {
		arcsBg = new SkinnedBlock( 1, 1, Assets.ARCS_BG );
		arcsBg.offsetTo( 0,  offsB );
		add( arcsBg );

	}


	@Override
	protected void layout() {
		arcsBg.size( dispWidth, dispHeight );
		arcsBg.offset( dispWidth , 0 );

	}

}
