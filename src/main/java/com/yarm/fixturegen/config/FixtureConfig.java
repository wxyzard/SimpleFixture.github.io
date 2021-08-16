package com.yarm.fixturegen.config;

import com.yarm.fixturegen.NumberValueType;
import com.yarm.fixturegen.StringValueType;
import com.yarm.fixturegen.theme.FCTheme;
import com.yarm.fixturegen.theme.Theme;

public class FixtureConfig {

    private int maxCollectionSize;

    private int numberDigitSize;

    private boolean onlyPositive;

    private StringValueType valueType;

    private NumberValueType numberType;

    private Theme theme;

    public FixtureConfig(){
        this.maxCollectionSize = 2;
        this.numberDigitSize = 5;
        this.valueType = StringValueType.FIELD;
        this.numberType = NumberValueType.SEQUENCE;
        this.onlyPositive = true;
        this.theme = new FCTheme();
    }

    public FixtureConfig(int maxCollectionSize){
        if(maxCollectionSize<0){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
    }

    public FixtureConfig(int maxCollectionSize, int numberDigitSize){
        if(maxCollectionSize<0 || maxCollectionSize<=0){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
        this.numberDigitSize = numberDigitSize;
    }

    public FixtureConfig(int maxCollectionSize, int numberDigitSize, StringValueType valueType, boolean onlyPositive){
        if(maxCollectionSize<0 || maxCollectionSize<=0 || valueType == null){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
        this.numberDigitSize = numberDigitSize;
        this.valueType = valueType;
        this.onlyPositive = onlyPositive;
    }

    public void setTheme(Theme theme){
        this.theme = theme;
    }


    public int getMaxCollectionSize() {
        return maxCollectionSize;
    }

    public int getNumberDigitSize() {
        return numberDigitSize;
    }

    public StringValueType getValueType() {
        return valueType;
    }

    public boolean isOnlyPositive() {
        return onlyPositive;
    }

    public Theme getTheme() {
        return theme;
    }

}
