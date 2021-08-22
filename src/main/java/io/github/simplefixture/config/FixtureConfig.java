package io.github.simplefixture.config;

import io.github.simplefixture.NumberValueType;
import io.github.simplefixture.StringValueType;
import io.github.simplefixture.theme.DefaultTheme;
import io.github.simplefixture.theme.Theme;

public class FixtureConfig {

    private int intDigitSize;
    private int floatDigitSize;
    private int longDigitSize;
    private int doubleDigitSize;
    private int maxCollectionSize;
    private boolean onlyPositive;
    private StringValueType valueType;
    private NumberValueType numberType;
    private Theme theme;

    public FixtureConfig(){
        this.maxCollectionSize = 2;
        this.floatDigitSize = 1;
        this.intDigitSize = 3;
        this.longDigitSize = 5;
        this.doubleDigitSize = 7;
        this.valueType = StringValueType.FIELD;
        this.numberType = NumberValueType.SEQUENCE;
        this.onlyPositive = true;
        this.theme = new DefaultTheme();
    }

    public FixtureConfig(int maxCollectionSize){
        if(maxCollectionSize<0){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
    }

    public FixtureConfig(int maxCollectionSize, StringValueType valueType, boolean onlyPositive){
        if(maxCollectionSize<0 || maxCollectionSize<=0 || valueType == null){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
        this.valueType = valueType;
        this.onlyPositive = onlyPositive;
    }

    public void setTheme(Theme theme){
        this.theme = theme;
    }
    public int getMaxCollectionSize() {
        return maxCollectionSize;
    }
    public int getIntegerDigitSize() {
        return intDigitSize;
    }
    public int getLongDigitSize() {
        return longDigitSize;
    }
    public int getFloatDigitSize() {
        return floatDigitSize;
    }
    public int getDoubleDigitSize() {
        return doubleDigitSize;
    }
    public StringValueType getValueType() {
        return valueType;
    }
    public NumberValueType getNumberValueType() {
        return numberType;
    }
    public boolean isOnlyPositive() {
        return onlyPositive;
    }
    public Theme getTheme() {
        return theme;
    }

    public boolean isSequenceNumberType(){
        return getNumberValueType().equals(NumberValueType.SEQUENCE);
    }

}
