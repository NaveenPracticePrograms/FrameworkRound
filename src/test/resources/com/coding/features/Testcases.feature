Feature: Validate the Testcases given in the Email 

@Testcase1
Scenario Outline:
Launch the Application and validate the login with valid and invalid credentials 
	Given User launches the application and clicks on "Form Authentication" 
	When User provides the username and password for "<Usertype>" 
	Then for valid credendtial login successfully else fail with an expected error 
	Examples: 
		|Usertype                |
		|Validuser               |
		|Invalidusername         |
		|Invalidpassword         |
		|InvaliduserAndPassword  |

@Testcase2	
Scenario:
Launch the Application and validate the Dynamic loading Page
	Given User launches the application and clicks on "Dynamic Loading" 
	When User selects the element and clicks on start 
	Then Validate the progress bar and once completed validate the final message
	
@Testcase3	
Scenario:
Launch the Application and validate the Multiple Windows 
	Given User launches the application and clicks on "Multiple Windows" 
	When User clicks on the link on multiple page window
	Then User should log the URL of the newly opened tab
	And User close the newly opened tab 
	And User logs and validates the title of the current page 
	
@Testcase4	
Scenario:
Launch the Application and validate the Drag And Drop 
	Given User launches the application and clicks on "Drag and Drop" 
	When User Should drag box A and drops it on box b
	Then Validate whether the box drop is success and take a screenshot		 

@Testcase5	
Scenario:
Launch the Application and validate the Frames 
	Given User launches the application and clicks on "Frames" 
	When User clicks on iFrame
	Then User should be able to clear the predefined text and enter some text
	And User should be able to make the newly entered text in bold and take a screenshot	

@Testcase6	
Scenario:
Launch the Application and validate the Javascript Alerts 
	Given User launches the application and clicks on "JavaScript Alerts" 
	When User Should clicks on js confirm link
	Then User should cancel the alert opened and validate the alert cancelled message		
