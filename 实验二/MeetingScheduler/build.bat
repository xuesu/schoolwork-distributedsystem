javac src\*.java
rmic -classpath src MeetingImpl
rmiregistry
cd ..
