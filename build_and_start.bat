set JAVA_EXEC=%JAVA_HOME%\bin\java
SET JVM_OPTIONS=-Xlog:gc* -XX:CompileThreshold=1000 -XX:-UseCounterDecay
REM Specify the maximum heap size of the ULBridge
SET HEAPSIZE=2048

REM Specify the heap for young objects
SET YOUNGSIZE=256
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;path/to/needed/jars/my.jar

@echo off
cd scripts
call "build.bat"
REM "%JAVA_EXEC%" %JVM_OPTIONS% -Xms%HEAPSIZE%M -Xmx%HEAPSIZE%M -XX:NewSize=%YOUNGSIZE%m -XX:MaxNewSize=%YOUNGSIZE%m entrypoint.HelloWorldApp
REM ping 127.0.0.1 -n 6 > nul
cd scripts
call "start.bat"

GOTO CLEANUP

:CLEANUP
REM delete compiled classes