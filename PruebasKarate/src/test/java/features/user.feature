Feature: Login user
  AS API USER
  I WANT TO
  make  a request POST
  SO THAT
  i can login in the API
  Scenario: Resquest sucessful
  Given url "https://reqres.in/api/login"
  When request {"email": "eve.holt@reqres.in","password": "cityslicka"}
  And method Post
  Then status 200
  And match response == {"token": "QpwL5tke4Pnpja7X4"}

