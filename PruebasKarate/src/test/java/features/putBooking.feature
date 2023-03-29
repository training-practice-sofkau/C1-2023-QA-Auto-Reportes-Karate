Feature: Update Booking
  As a user of Restfull Booker
  I want to update an existing booking's information
  So that I can update the information

  @Token
  Scenario: Retrieve Token
  Given url "https://restful-booker.herokuapp.com/auth"
  And request {"username" : "admin","password" : "password123"}
  When method post
  Then status 200
    And def token = "token=" + response.token
    And karate.write('token.txt', token)
    And print token

  @Token
  @UpdateBooking
  Scenario: not Update an existing booking's information in Restfull Booker if the user doesn't have the token
    Given url "https://restful-booker.herokuapp.com" + "/booking/" + "2"
    And headers {"Content-Type": "application/json", "Accept": "application/json", "Cookie": token}
    And request {"firstname": "Susana","lastname": "Wil","totalprice": 525,"depositpaid": true, "bookingdates":{"checkin": "2023-09-03","checkout": "2023-05-07"}}
    When method put
    Then status 403
