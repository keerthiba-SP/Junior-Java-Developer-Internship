@echo off
if not exist target\library-management-final-1.0.jar (
    echo JAR not found. Run: mvn clean package
    exit /b 1
)
java -jar target\library-management-final-1.0.jar
