# Thermometer Technical Assessment

### Details

This application is designed to be run only via the tests due to mocking external sources

### Assumptions Made
- The external source has a particular model as shown in the following example
```json
{
 [
   {
     "temperature_unit": "fahrenheit",
     "temperature": 32.0
   }
 ],
...
}
```
- The first temperature in the list is the oldest and the last is the most recent reading
- Temperature precision is only one digit (i.e. 32.1)

### Features
- Ability to convert temperatures based on the configured `ThermometerUnit` variable on the Thermometer
- Configurable Notification rules for the following
  - Notify only on increasing temperatures
  - Notify only on decreasing temperatures
  - Temperature differential to ignore small fluctuations
- Configurable Thresholds
  - Freezing and Boiling have booleans to turn on and off
  - Ability to add custom thresholds in the form of Java predicates

### Personal Thoughts
- I felt I potentially overengineered this a bit, but had a load of fun making it
- Overengineering it led to taking up too much of the limited time I was able to spend on this, when a simpler solution could have been satisfactory.
- If I were to do it over again I might try to hook it up to an external api and model the responses properly. To extend it further, I would either have a rest api to fetch the results or have it as a scheduled service or batch process that could save the data off somewhere so the application could run beyond just the tests.
