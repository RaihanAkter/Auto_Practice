Feature: Livestock indemnity program

Scenario Outline: scenario description
		Given user log into the LIP application "<URL>"
		Then select state program year from list
		Then Add new application
		Then Add first Loss of notice information
		And Close browser
Examples:
|                      URL                            |
|https://cert1-intranet-apps.fsa.usda.gov/lip/login.do|

