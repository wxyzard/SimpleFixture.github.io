# simplefixture.github.io
Write maintainable unit tests, faster.

SimpleFixture makes it easier for developers to do Test-Driven Development by automating non-relevant Test Fixture Setup, allowing the Test Developer to focus on the essentials of each test case.


## Dependency

maven
```maven
<>
    <groupId>io.github.wxyzard</groupId>
    <artifactId>simplefixture</artifactId>
    <version>0.1.4</version>
</>
```

gradle
```gradle
testCompile "io.github.wxyzard:simplefixture:0.1.4"
```

## Usage

```java
@Getter
class Sample{
    private String name;
    private String nickName;
    private List<String> phoneNumbers;
}


```

Basic 

Auto Generate Fixture
```java
//returns 'sample fixture'
Sample sample = fixture.create(Sample.class);

Assertions.assertEquals(order.getName(), "name"); // default value is the same as the field name.
Assertions.assertEquals(order.getNickName(), "nickname"); // default value is all lowcase
Assertions.assertEquals(order.getShipmentList().size(), 2); // default Collection size is 2

```

Json Generate Fixture
```java

Fixture fixture = new Fixture();
        Order order = fixture
                .create("{\"orderNumber\": \"1234\",\"orderName\": \"user\",\"zipcode\": \"1234\"
                ,\"isTelco\": \"true\", \"createdAt\": \"2021-01-01 12:00:00\", \"updatedAt\": \"2021-01-01 12:00:00\"}"
                ,Order.class);

        Assertions.assertEquals(order.getOrderName(), "user");
        Assertions.assertEquals(order.getOrderNumber(), 1234L);
        Assertions.assertEquals(order.getZipcode(), "1234");


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

FixtureConfig config = new FixtureConfig.Builder // you can change Fixture configuration
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
|stringValueType|string value style|FieldNameType|
|numberValueType|number value style|SequenceType|
|Theme|theme|DefaultTheme()|


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Mozilla Public License version 2.0](https://www.mozilla.org/en-US/MPL/2.0/)

