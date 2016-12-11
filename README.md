[![Build Status](https://travis-ci.org/JeP42/robotframework-formatcompare.svg?branch=master)](https://travis-ci.org/JeP42/robotframework-formatcompare) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.jep42/robotframework-formatcompare/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.jep42/robotframework-formatcompare)



## robotframework-formatcompare


### Description

This library provides keywords to compare different data structures (csv, xml, json) via mapfiles. The mapfile specifies the elements which are compared with each other and defines certain rules for this compare operation.

### Keywords

See the [keyword documentation](https://jep42.github.io/robotframework-formatcompare/RobotFormatCompare.html) for information about the provided keywords.

### Usage

Since the library is implemented in Java it can be used together with Jython just by importing the library within the test file. To be able to
use it with Python the library has to be imported as remote library (refer to the [RobotFrameworkUserGuide](http://robotframework.org/robotframework/latest/RobotFrameworkUserGuide.html#remote-library-interface) for more information about Remote Libraries)

#### Jython

- download latest robotframework-formatcompare-jar ([robotframework-formatcompare-0.0.1.jar](https://repo.maven.apache.org/maven2/com/github/jep42/robotframework-formatcompare/0.0.1/robotframework-formatcompare-0.0.1.jar))
- add the jar to classpath (as well as its dependencies)
- import the library

```
*** Settings ***
Library    com.github.jep42.robotformatcompare.RobotFormatCompare
```

#### Remote Library Server

- download the remote-server-jar ([robotframework-formatcompare-remoteserver-0.0.1-jar-with-dependencies.jar](https://repo.maven.apache.org/maven2/com/github/jep42/robotframework-formatcompare-remoteserver/0.0.1/robotframework-formatcompare-remoteserver-0.0.1-jar-with-dependencies.jar))
- start it via command line ``java -jar <remote-server-jar> --port 8270``
- import the library

```
*** Settings ***
Library    Remote    http://localhost:8270/RobotFormatCompare
```


####Robot Testcase Sample: Compare JSON with XML


```
*** Test Cases ***
Compare Download Formats JSON And XML
    ...
    Initialize Json Format Handler    GMT+01:00    dd.MM.yyyy HH:mm:ss    dd.MM.yyyy    " ,2"
    Initialize Xml Format Handler    GMT+01:00     yyyyMMddHHmmss    yyyyMMddHHmmss    " .2"
    Compare Json With XML    ${TESTDATA}/json2xml.mapfile    ${TEMPDIR}/jsondownload.json    ${TEMPDIR}/xmldownload.xml
    ...
```

The test case above references the mapfile `${TESTDATA}/json2xml.mapfile` which maps elements from a Json (JSONPath expression) to elements of an XML (XPath expression) structure:

```
$.data[0].firstname;Neverland/Persons/Person[1]/Firstname;STRING;=;;
$.data[0].lastname;Neverland/Persons/Person[1]/Lastname;STRING;=;;
$.data[0].email;Neverland/Persons/Person[1]/Email;STRING;=;;
$.data[0].email;Neverland/Persons/Person[1]/Email2;STRING;!=;;
$.data[0].sex;Neverland/Persons/Person[1]/Firstname;STRING;=;;
```

####Robot Testcase Sample: Compare CSV with XML

```
Compare Download Formats CSV And XML
    ...
    Initialize Csv Format Handler    GMT+01:00    dd.MM.yyyy HH:mm:ss    dd.MM.yyyy    " ,2"    0
    Initialize Xml Format Handler    GMT+01:00     yyyyMMddHHmmss    yyyyMMddHHmmss    " .2"
    Compare Csv With XML    ${TESTDATA}/csv2xml.mapfile    ${TEMPDIR}/csvdownload.csv    ${TEMPDIR}/xmldownload.xml
    ...
```

The test case above references the mapfile `${TESTDATA}/csv2xml.mapfile` which maps elements from a CSV (EasyCSVMap selector expression) to elements of an XML (XPath expression) structure:

```
{1}.firstname;Neverland/Persons/Person[1]/Firstname;STRING;=;;
{1}.lastname;Neverland/Persons/Person[1]/Lastname;STRING;=;;
{1}.email;Neverland/Persons/Person[1]/Email;STRING;=;;
{1}.email;Neverland/Persons/Person[1]/Email2;STRING;!=;;
{1}.sex;Neverland/Persons/Person[1]/Sex;STRING;=;;
```


### Dependencies
- org.robotframework:javalib-core:1.2.1 ([Download](https://mvnrepository.com/artifact/org.robotframework/javalib-core/1.2.1) from Maven Central)
- com.google.code.findbugs:jsr305:2.0.1 ([Download](https://mvnrepository.com/artifact/com.google.code.findbugs/jsr305/2.0.1) from Maven Central)
- org.slf4j:slf4j-api:1.7.21 ([Download](https://mvnrepository.com/artifact/org.slf4j/slf4j-api/1.7.21) from Maven Central)
- com.github.jep42:robotframework-easycsvmap:0.0.2 ([Download](https://mvnrepository.com/artifact/com.github.jep42/robotframework-easycsvmap/0.0.2) from Maven Central)
- com.jayway.jsonpath:json-path:2.2.0 ([Download](https://mvnrepository.com/artifact/com.jayway.jsonpath/json-path/2.2.0) from Maven Central)
- [only for sub-module remoteserver] com.github.ombre42:jrobotremoteserver:3.0 ([Download](https://mvnrepository.com/artifact/com.github.ombre42/jrobotremoteserver/3.0) from Maven Central)


### Build from source

- Clone the repository
- Execute ``mvn install`` on the root folder of the project to build the reactor which consist of two modules
- Sub-module "formatcompare": The keyword library
- Sub-module "remoteserver": The remote server which already contains the keyword library




