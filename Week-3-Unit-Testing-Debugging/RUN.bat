@echo off
setlocal
if not exist out mkdir out
javac -d out src\main\java\*.java
if errorlevel 1 (
  echo.
  echo Compilation failed. Please install Java JDK 17 or newer and ensure javac is in PATH.
  pause
  exit /b 1
)
echo.
echo Starting Library Management System...
echo.
java -cp out Library_Management
endlocal
