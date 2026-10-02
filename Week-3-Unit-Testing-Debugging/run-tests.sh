#!/bin/sh
set -e
mkdir -p out
javac -d out src/main/java/*.java VerificationRunner.java
java -cp out VerificationRunner
