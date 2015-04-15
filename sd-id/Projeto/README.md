# Bubble Docs SD-ID

In order to publish the server, you must first run startup.bat on your terminal (assuming you have the needed jUDDI tools already set up). Then, change directory to the one with the .pom file and run the following command:

mvn clean generate-sources package exec:java -Dexec.args="http://localhost:8081 SDId http://localhost:8080/id-ws/endpoint"

Info: 
mvn clean -> cleans the generated sources
mvn generate-sources -> generates sources according to the wsdl
mvn package -> compiles everything and packages it
mvn exec:java -Dexec.args="arg1 arg2 arg3" -> Executes the main java file. the -Dexec flag is optional, only use it to specify the uddiurl, wsname and wsurl; otherwise it will run the default values.
