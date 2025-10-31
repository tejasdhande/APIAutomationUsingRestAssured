Feature: Update existing place

@UpdatePlace @Regression
Scenario Outline: Veify if existing place gets updated

Given Update place payload with "<address>".
When Users call "UpdatePlaceAPI" with "PUT" request.
Then API call should success with 200
And Verify Address is updated using "<address>" call

Examples:
|address|
|NewYork 142543|
|boston1213|