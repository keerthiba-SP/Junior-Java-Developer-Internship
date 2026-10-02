#!/bin/sh
set -e
mkdir -p out
javac -d out src/main/java/*.java
java -cp out Library_Management
