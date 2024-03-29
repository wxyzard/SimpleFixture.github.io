# Simplefixture
Write maintainable unit tests, faster.

SimpleFixture makes it easier for developers to do Test-Driven Development by automating non-relevant Test Fixture Setup, allowing the Test Developer to focus on the essentials of each test case.

## Specification
1. Solving Circular Reference
2. Generate Fixture
3. Json Deserializer
4. Kotlin Compatibility
5. Support User Fixture Template(Theme)


## Dependency

maven
```maven
<groupId>io.github.wxyzard</groupId>
<artifactId>simplefixture</artifactId>
<version>0.5.0</version>
```

gradle
```gradle
testCompile "io.github.wxyzard:simplefixture:0.5.0"
```

## Usage

```java
class Sample{
    private String name;
    private String nickName;
    private List<String> phoneNumbers;
    
    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
}


```


Auto Generate Fixture

```java
//returns 'sample fixture'
Sample sample = fixture.create(Sample.class);

Assertions.assertEquals(order.getName(), "name1"); // default value is the same as the field name1.
Assertions.assertEquals(order.getNickName(), "nickname1"); // default value is all lowcase
Assertions.assertEquals(order.getShipmentList().size(), 2); // default Collection size is 2

```


Json Deserializer
```java

Fixture fixture = new Fixture();
        Sample sample = fixture
                .create("{\"name\": \"user\",\"nickName\": \"wizard\",\"phoneNumbers\": [\"1234\",\"1234\"]}"
                ,Sample.class);

        Assertions.assertEquals(sample.getName(), "user");
        Assertions.assertEquals(sample.getNickName(), "wizard");
        Assertions.assertEquals(sample.getPhoneNumbers().size(), 2);


```


Modify Fixture Property
```java
Fixture fixture = new Fixture();
        Sample sample = fixture
                .setProperty("nickName", "wizard") // you can modify values
                .create(Sample.class);

Assertions.assertEquals(order.getName(), "name");
Assertions.assertEquals(order.getNickName(), "wizard"); 

```


Use Fixture Configuration
```java

FixtureConfig config = new FixtureConfig.Builder() // you can change Fixture configuration
                .maxCollectinSize(3)
                .build();

Fixture fixture = new Fixture();
        Sample sample = fixture
                .config(config) 
                .create(Sample.class); 

Assertions.assertEquals(order.getShipmentList().size, 3); 


```

Configuration
|attribute|description|default|
|------|---|---|
|floatDigitSize|float type digit size|1|
|intDigitSize|int type digit size|4|
|longDigitSize|long type digit size|8|
|doubleDigitSize|double type digit size|12|
|maxCollectionSize|max collection type size|2|
|stringValueType|This value sets the text data display method.  <br/>FIELD: The value is FieldName  <br/>RANDOME: random UUID|FIELD|
|numberValueType|This value sets the numeric data display method.  <br/>SEQUENCE: The value increases by 1  <br/>RANDOME: random number|SEQUENCE|
|Theme|theme|DefaultTheme()|


Use Theme

```java
public interface Theme {

    <T> T getValue(int index, Field field, T value); 
}

```
If you inherit the theme interface, you can implement the getValue() method, which is called every time a value is created in each field and allows you to predefine the value you want in this method.




## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Mozilla Public License version 2.0](https://www.mozilla.org/en-US/MPL/2.0/)

