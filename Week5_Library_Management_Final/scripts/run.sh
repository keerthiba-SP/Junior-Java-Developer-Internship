#!/bin/sh
if [ ! -f target/library-management-final-1.0.jar ]; then
  echo "JAR not found. Run: mvn clean package"
  exit 1
fi
java -jar target/library-management-final-1.0.jar
