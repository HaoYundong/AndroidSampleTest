package com.sample.gofdemo.工厂方法模式;

/**
 * Created by haoyundong on 2016/11/3.
 */

public class XinHuaFactory extends ColleagueFactory {

    @Override
    public Colleague createColleague() {
        return new XinHua();
    }
}
