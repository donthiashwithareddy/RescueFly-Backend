@echo off
set MAVEN_PROJECTBASEDIR=%~dp0
if exist "%MAVEN_HOME%\bin\mvn.cmd" (call "%MAVEN_HOME%\bin\mvn.cmd" %*) else (echo Maven is not installed. Install Maven 3.9+ or use Docker Compose.& exit /b 1)
