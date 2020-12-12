<p align="center">
    <img src="https://i.imgur.com/eRRdNud.png" width="100" />
</p>

<!--<p align="center">
    <a href="https://github.com/QuiiBz/squid/actions">
        <img src="https://github.com/QuiiBz/squid/workflows/Lint/badge.svg" />
    </a>
    <a href="https://travis-ci.com/QuiiBz/squid">
        <img src="https://travis-ci.com/QuiiBz/squid.svg?branch=canary" />
    </a>
    <a href="https://www.code-inspector.com/project/4175/score/svg">
        <img src="https://www.code-inspector.com/project/4175/score/svg" />
    </a>
    <a href="https://github.com/QuiiBz/squid/issues">
        <img src="https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat" />
    </a>
</p>-->

**Vine** is a Java PreProcessor that let you use one codebase while having multiple Minecraft versions. Created and used by [PvPHall Client](https://pvphall.com).

## Table of content
- [Example](#example)
- [Usage](#usage)
  - [CLI](#cli)
  - [API](#api)
- [Compiling ](#compiling)
- [License](#license)

## Example
I want to set the title of the window based on the Minecraft version:
```java
public void setTitle() {

    //# if MC=1710
    String version = "1.7.10";
    //# elseif MC=189
    //$ String version = "1.8.9";
    //# endif

    Display.setTitle("Minecraft " + version);
}
```
The preprocessed file ran with `mcVersion` 1710 will be:
```java
public void setTitle() {

    String version = "1.7.10";

    Display.setTitle("Minecraft " + version);
}
```
And the same file preprocessed with `mcVersion` 189 will be:
```java
public void setTitle() {

    String version = "1.8.9";

    Display.setTitle("Minecraft " + version);
}
```

See package `com.pvphall.vine.preprocessor.expressions` to see all available expressions (`if`, `else`...).

## Usage
### CLI
The CLI requires 3 arguments:
- `input` The input folder/file to preprocess
- `output` The output folder/file where the preprocessed files will be created
- `mcVersion` The Minecraft version to use. It should be the version number without any dots. (Example: 1.8.9 -> 189)

Example:
```
java -jar Vine-1.0-SNAPSHOT.jar --input ../Client/src/main/java/com/pvphall/client --output ../Client-1.7.10/src/main/java/com/pvphall/client --mcVersion 1710
```

### API
As the CLI, you will need two `File` objects, for the `input` and `output`, and a `String` representing the `mcVersion`.

Example:
```java
IPreProcessor preProcessor = PreProcessor.makePreProcessor(inputFile, outputFile);
preProcessor.preprocess(new FileProcessor(mcVersion));
```

## Compiling
1. Clone or download the sources files of this repo
2. Make sure you have Maven available on your IDE
3. Run `mvn clean install`. Grab the compiled jar at `projectroot/target/Vine-VERSION.jar`;

## License
Vine is licenced under [MIT](https://choosealicense.com/licenses/mit/) license.