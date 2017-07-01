package com.daiduo.lightning.skills;

import com.daiduo.lightning.messages.Messages;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

/**
 * Created by badcw on 2017/7/1.
 */

public class Skills implements Bundlable{

    public boolean learned;
    public String name = Messages.get(this, "name");

    public boolean isLearned() {
        return learned;
    }

    public String name() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setLearned(boolean learned) {
        this.learned = learned;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {

    }

    @Override
    public void storeInBundle(Bundle bundle) {

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
