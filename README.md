Scala Design Patterns
=====================

This repository contains the final Scheduler code that goes with the **Scala Design Patterns** book.

### Project Structure

The source code for the book is presented as amulti-module Maven project.

## Installation

Use the official installer [sbt](https://www.scala-installer.org/download.html)

```bash
brew install sbt
brew install scala

```

## Usage

```bash

clone the project

cd /path/to/repo

```

### Running the Code

Running the code is pretty straightforward from here and anyone with some minor Maven experience should manage.

#### Compiling the Projects

`mvn clean compile`

#### Running the Unit Tests

`mvn clean test`

The test command will also run the compile one.

#### Creating the Jars

`mvn clean package`

The above command will package everything. You can find the jars in the **target** subfolder of the respective module folder.# scala-design-patterns
