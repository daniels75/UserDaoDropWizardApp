mvn clean install

Initialize database:
java -jar target\DaoDropWizardApp-1.0.0.jar db migrate example.yml

or with MySql

java -jar target\DaoDropWizardApp-1.0.0.jar db migrate myexample.yml

Then run application from command line (in the appliation direcotry)
for h2 database
java -jar target\DaoDropWizardApp-1.0.0.jar server myexample.yml
or mysql database
java -jar target\DaoDropWizardApp-1.0.0.jar server example.yml


Eclipse:

Debug:

Project: DaoDropWizardApp
MainClass: org.daniels.examples.dropwizard.HelloWorldApplication
Arguments: 
server example.yml
or
myexample.yml


Run Advanced REST Client (Chrome plugin)
GET
http://localhost:8080/user

POST
http://localhost:8080/user
Payload: 
{"name":"DanielS"}


POST
http://localhost:8080/user/random



