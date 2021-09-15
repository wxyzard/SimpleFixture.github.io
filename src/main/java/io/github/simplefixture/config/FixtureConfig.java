package io.github.simplefixture.config;

import io.github.simplefixture.NumberValueType;
import io.github.simplefixture.StringValueType;
import io.github.simplefixture.theme.DefaultTheme;
import io.github.simplefixture.theme.Theme;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixtureConfig {

    private int intDigitSize;
    private int floatDigitSize;
    private int longDigitSize;
    private int doubleDigitSize;
    private int maxCollectionSize;
    private StringValueType valueType;
    private NumberValueType numberType;
    private Theme theme;
    private Map<String, Object> values = new ConcurrentHashMap<>();


    public static class Builder {
        private int intDigitSize=4;
        private int floatDigitSize=1;
        private int longDigitSize=8;
        private int doubleDigitSize=12;
        private int maxCollectionSize=2;
        private StringValueType valueType = StringValueType.FIELD;;
        private NumberValueType numberType= NumberValueType.SEQUENCE;
        private Theme theme = new DefaultTheme();

        public Builder() {

        }

        public Builder intDigitSize(int intDigitSize) {
            this.intDigitSize = intDigitSize;
            return this;    // 이렇게 하면 . 으로 체인을 이어갈 수 있다.
        }
        public Builder floatDigitSize(int floatDigitSize) {
            this.floatDigitSize = floatDigitSize;
            return this;
        }
        public Builder longDigitSize(int longDigitSize) {
            this.longDigitSize = longDigitSize;
            return this;
        }
        public Builder doubleDigitSize(int doubleDigitSize) {
            this.doubleDigitSize = doubleDigitSize;
            return this;
        }
        public Builder maxCollectionSize(int maxCollectionSize) {
            this.maxCollectionSize = maxCollectionSize;
            return this;
        }
        public Builder valueType(StringValueType stringValueType) {
            this.valueType = stringValueType;
            return this;
        }
        public Builder numberValueType(NumberValueType numberValueType) {
            this.numberType = numberValueType;
            return this;
        }

        public Builder theme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public FixtureConfig build() {
            return new FixtureConfig(this);
        }
    }

    public FixtureConfig(Builder builder){
        this.maxCollectionSize = builder.maxCollectionSize;
        this.floatDigitSize = builder.floatDigitSize;
        this.intDigitSize = builder.intDigitSize;
        this.longDigitSize = builder.longDigitSize;
        this.doubleDigitSize = builder.doubleDigitSize;
        this.valueType = builder.valueType;
        this.numberType = builder.numberType;
        this.theme = builder.theme;
    }

    public FixtureConfig(){
        this.maxCollectionSize = 2;
        this.floatDigitSize = 1;
        this.intDigitSize = 4;
        this.longDigitSize = 8;
        this.doubleDigitSize = 12;
        this.valueType = StringValueType.FIELD;
        this.numberType = NumberValueType.SEQUENCE;
        this.theme = new DefaultTheme();
    }

    public FixtureConfig(int maxCollectionSize){
        if(maxCollectionSize<0){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
    }

    public FixtureConfig(int maxCollectionSize, StringValueType valueType){
        if(maxCollectionSize<0 || maxCollectionSize<=0 || valueType == null){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
        this.valueType = valueType;
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
    public Theme getTheme() {
        return theme;
    }

    public boolean isSequenceNumberType(){
        return getNumberValueType().equals(NumberValueType.SEQUENCE);
    }

    public Map getValues() {
        return values;
    }

    public void setProperty(String fieldKey, Object value) {
        this.values.put(fieldKey, value);
    }
}
