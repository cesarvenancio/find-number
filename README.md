# Find Number - Programming Challenge

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info

This project is a Java solution design for the "Find-Number" challenge.

### Main Goal

Get a JSON file from a specific folder and check if a specific number is present in the JSON

### Solution

1. Get number
2. Validate Number
3. Get JSON 
4. Check if number is present in JSON
	
## Technologies

This project was created using:
* Java 8
* jackson-databind 2.9.8
* Junit 4.12
* lombok 1.18.6
		
## Setup

### Execute using maven

```
$ mvn exec:java
```

### OR

### Build the .jar

If you are familiar with Maven, you can use it to build the .jar so you can test the code.
Go to your CLI again and execute the command.

```
$ mvn clean install
```

A `.jar` will be available in the `target` folder.

### Execute the .jar in Your CLI

```
$ java -jar ./target/findnumber-1.0.jar {filePath optional}
```