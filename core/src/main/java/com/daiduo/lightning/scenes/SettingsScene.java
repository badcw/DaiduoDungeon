package com.daiduo.lightning.scenes;

import com.daiduo.lightning.ShatteredPixelDungeon;
import com.daiduo.lightning.classes.Challenges;
import com.daiduo.lightning.messages.Messages;
import com.daiduo.lightning.ui.Archs;
import com.daiduo.lightning.ui.CheckBox;
import com.daiduo.lightning.ui.Window;
import com.watabou.noosa.Camera;
import com.watabou.noosa.RenderedText;

import java.util.ArrayList;

import static android.R.attr.checked;
import static com.daiduo.lightning.ui.Window.TITLE_COLOR;

/**
 * Created by qq on 2017/7/21.
 */

public class SettingsScene extends BlankScene {
    @Override
    public void create() {
        super.create();

        uiCamera.visible = false;

        int w = Camera.main.width;
        int h = Camera.main.height;

        Archs archs = new Archs();
        archs.setSize(w, h);
        add(archs);


        fadeIn();
    }

        public class WndChallenges extends Window {

            private static final int WIDTH = 108;
            private static final int TTL_HEIGHT = 12;
            private static final int BTN_HEIGHT = 18;
            private static final int GAP = 1;

            private boolean editable;
            private ArrayList<CheckBox> boxes;

            public WndChallenges(int checked, boolean editable) {

                super();

                this.editable = editable;

                RenderedText title = PixelScene.renderText(Messages.get(this, "title"), 9);
                title.hardlight(TITLE_COLOR);
                title.x = (WIDTH - title.width()) / 2;
                title.y = (TTL_HEIGHT - title.height()) / 2;
                PixelScene.align(title);
                add(title);

                boxes = new ArrayList<>();

                float pos = TTL_HEIGHT;
                for (int i = 0; i < Challenges.NAME_IDS.length; i++) {

                    CheckBox cb = new CheckBox(Messages.get(Challenges.class, Challenges.NAME_IDS[i]));
                    cb.checked((checked & Challenges.MASKS[i]) != 0);
                    cb.active = editable;

                    if (i > 0) {
                        pos += GAP;
                    }
                    cb.setRect(0, pos, WIDTH, BTN_HEIGHT);
                    pos = cb.bottom();

                    add(cb);
                    boxes.add(cb);
                }

                resize(WIDTH, (int) pos);
            }

            @Override
            public void onBackPressed() {

                if (editable) {
                    int value = 0;
                    for (int i = 0; i < boxes.size(); i++) {
                        if (boxes.get(i).checked()) {
                            value |= Challenges.MASKS[i];
                        }
                    }
                    ShatteredPixelDungeon.challenges(value);
                }

                super.onBackPressed();
            }
        }
}
