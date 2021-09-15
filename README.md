# simplefixture.github.io
Write maintainable unit tests, faster.

SimpleFixture makes it easier for developers to do Test-Driven Development by automating non-relevant Test Fixture Setup, allowing the Test Developer to focus on the essentials of each test case.


## Dependency

maven
```maven
<>
    <groupId>io.github.wxyzard</groupId>
    <artifactId>simplefixture</artifactId>
    <version>0.1.1</version>
</>
```

gradle
```gradle
testCompile "io.github.wxyzard:simplefixture:0.1.1"
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

Auto Generate Value
```java
//returns 'sample fixture'
Sample sample = fixture.create(Sample.class);

```

Modify Value
```java
//returns 'sample fixture'
Fixture fixture = new Fixture();
        Sample sample = fixture
                .setProperty("nickName", "wizard")
                .create(Sample.class);

Assertions.assertEquals(order.getName(), "name");
Assertions.assertEquals(order.getNickName(), "john");

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Mozilla Public License version 2.0](https://www.mozilla.org/en-US/MPL/2.0/)

