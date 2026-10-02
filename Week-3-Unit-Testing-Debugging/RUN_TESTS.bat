@echo off
setlocal
if not exist out mkdir out
javac -d out src\main\java\*.java VerificationRunner.java
if errorlevel 1 (
  echo.
  echo Compilation failed. Please install Java JDK 17 or newer and ensure javac is in PATH.
  pause
  exit /b 1
)
echo.
echo Running Week 3 verification tests...
echo.
java -cp out VerificationRunner
set EXITCODE=%ERRORLEVEL%
echo.
if %EXITCODE%==0 (
  echo All verification tests passed.
) else (
  echo One or more verification tests failed.
)
pause
exit /b %EXITCODE%
