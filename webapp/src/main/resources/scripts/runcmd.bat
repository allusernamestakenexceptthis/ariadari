
::@echo off
set batdir=%~dp0
set "LIBSPATH=%batdir%..\lib\*;%batdir%."

java -classpath "%LIBSPATH%" com.gomilkyway.profile.adari.AdariCmd %*