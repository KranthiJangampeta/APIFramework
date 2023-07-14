# APIFramework

Test Plan Document is present at root folder of the project.
APIFramework/Test Plan Document.docx

Feature file contains 7 scenarios covering the given requirement.

Run Instruction:
Use Maven command "mvn test verify" in command prompt to run complete feature file at once and it intern creates result reports

To run a specific scenario use below command:
"mvn test -Dcucumber.options"--tags @scenarioTagHere"

Example: each scenario is given a tag name.
Maven command for first scenario will be: "mvn test -Dcucumber.options"--tags @Test1"


Result Reports: results will be generated in below location in target folder after successful build.

Request & Response logs are presnt at: APIFramework\logs.txt

Cucumber Html Reports: target/cucumber-html-reports

Json & html Reports: target/jsonReports




 
