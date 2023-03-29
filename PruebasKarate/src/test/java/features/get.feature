Feature: Get

Background:
* url 'https://reqres.in/api'
* header Accept = 'application/json'


Scenario: Get de un scenario simple
Given url 'https://reqres.in/api/users/2'
When method GET
Then status 200
And print response


Scenario: Get con brackground y path
Given path '/users/2'
When method GET
Then status 200

Scenario: Get con brackground y path y params
Given path '/users'
And param page = 2
When method GET
Then status 200


Scenario: Get con assertions
Given path '/users'
And param page = 2
When method GET
Then status 200
And match response.data[0].first_name != null
And assert response.data.length == 6
And match $.data[1].id == 8
And match $.data[3].last_name == 'Fields'