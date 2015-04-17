# Distributed Sistems Project #

## First Part ##

SD group 10:
77077 David Lourenço
78081 Gabriel Freire
79780 Tiago Gomes

Repository:
[tecnico-softeng-distsys-2015/T_00_00_10-project] https://github.com/tecnico-softeng-distsys-2015/T_00_00_10-project

--------------------------------------------------------------------------------------------------

## Service SD-ID

## Installation instructions

[0] Start Operative system: Windows

[1] Start Server

In order to publish the server, you must first run startup.bat on your terminal, (assuming you have the needed jUDDI tools already set up). If this doesn't work, using your terminal, go to the folder where you have created CATALINA_HOME variable and run there startup.bat. 

[2] Create temporary directory

[3] Get the latest version


[4] Construct Server:
In order to construct the server, do cd.. on your terminal, until you get to the directory "Projeto". After that, execute the following command on your terminal:
mvn clean generate-sources package exec:java -Dexec.args="http://localhost:8081 SDId http://localhost:8080/id-ws/endpoint"

Info: 
mvn clean -> cleans the generated sources
mvn generate-sources -> generates sources according to the wsdl
mvn package -> compiles everything and packages it
mvn exec:java -Dexec.args="arg1 arg2 arg3" -> Executes the main java file. the -Dexec flag is optional, only use it to specify the uddiurl, wsname and wsurl; otherwise it will run the default values.

Right now, if you don't specify the arguments, here are the default ones:
ws.url = "http://localhost:8080/id-ws/endpoint"
ws.name = "SDId"
uddi.url = "http://localhost:8081"
        
[5] Construct Client:
In order to construct the client, do cd.. on your terminal, until you get to the directory "Projeto_cliente". After that, execute the following commands on your terminal:
mvn generate-sources -> generates sources according to the wsdl
mvn exec:java -> runs de program

--------------------------------------------------------------------------------------------------

## Test instructions
(How to verify if all functions are running correctly)

[1] Execute the Test Client
    cd on your terminal until you get to the "\Projeto_cliente" directory. Then, in order to run the tests, just type the following command:
   > mvn test    



--------------------------------------------------------------------------------------------------
** END **

