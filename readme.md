# SimpleDBC
A Java library aims to ease database operations.

Written in pure Java.

## Features
### Supported Databases
- [x] MySQL
- [x] SQLite
- [x] MariaDB (Since 1.1.2)
- [x] PostgreSQL (Since 1.1.2)

## Requirements
- Java 8+

## Installation
### Maven

<details>
<summary>Repository</summary>
    
```xml
<project>
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <name>Jitpack</name>
            <url>https://jitpack.io/</url>
        </repository>
    </repositories>
</project>
```
</details>

<details>
<summary>Dependency</summary>

```xml
<project>
    <dependencies>
        <!-- module `core` -->
        <dependency>
            <groupId>com.github.BlockNeko-11.SimpleDBC</groupId>
            <artifactId>simpledbc-core</artifactId>
            <version>[LATEST RELEASE]</version>
            <scope>compile</scope>
        </dependency>
        
        <!-- module `[YOUR DATABASE NAME]` -->
        <dependency>
            <groupId>com.github.BlockNeko-11.SimpleDBC</groupId>
            <artifactId>simpledbc-[YOUR DATABASE NAME]</artifactId>
            <version>[LATEST RELEASE]</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
</project>
```
</details>

### Gradle

<details>
<summary>Repository</summary>

```gradle
repositories {
    maven {
        name = "Jitpack"
        url = "https://jitpack.io/"
    }
}
```
</details>

<details>
<summary>Dependency</summary>

```groovy
dependencies {
    // module `core`
    implementation "com.github.BlockNeko-11.SimpleDBC:simpledbc-core:[LATEST RELEASE]"
    
    // module `[YOUR DATABASE NAME]`
    implementation "com.github.BlockNeko-11.SimpleDBC:simpledbc-[YOUR DATABASE NAME]:[LATEST RELEASE]"
}
```
</details>

## Build
1. Clone this repository
2. run `./gradlew build` in the project directory
