@echo off

set MAIN_SOURCE_FILE="TicTacToe.java"
set JAVAC="C:/Program Files/Java/jdk1.8.0_60/bin/javac.exe"
set CLASS_PATH="."

del /S *.class
%JAVAC% -cp %CLASS_PATH% %MAIN_SOURCE_FILE%
