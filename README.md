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

Auto Generate Value
```java
//returns 'sample fixture'
Sample sample = fixture.create(Sample.class);

Assertions.assertEquals(order.getName(), "name"); // default value is the same as the field name.
Assertions.assertEquals(order.getNickName(), "nickname"); // default value is all lowcase
Assertions.assertEquals(order.getShipmentList().size(), 2); // default Collection size is 2

```

Modify Value
```java
Fixture fixture = new Fixture();
        Sample sample = fixture
                .setProperty("nickName", "wizard") // you can modify values
                .create(Sample.class);

Assertions.assertEquals(order.getName(), "name");
Assertions.assertEquals(order.getNickName(), "wizard"); 

```

Advance

Use Configuration
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
|intDigitSize|테스트2|테스트3|
|floatDigitSize|테스트2|테스트3|
|longDigitSize|테스트2|테스트3|
|doubleDigitSize|테스트2|테스트3|
|maxCollectionSize|테스트2|테스트3|
|StringValueType|테스트2|테스트3|
|NumberValueType|테스트2|테스트3|
|Theme|테스트2|테스트3|


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Mozilla Public License version 2.0](https://www.mozilla.org/en-US/MPL/2.0/)

