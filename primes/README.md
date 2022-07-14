# Sum Primes REST Service

Sample java program to sum all prime numbers up to a particular value.

## Description

Uses Jersey to expose the sum application as a REST API. Embeds a Jetty Web Server so it can be run as a java application and JUnit test can be run.


### Installing

Is set up as Maven project. The easiest way to try it out is to import Maven project into a java IDE.

### Executing program

* Running the JUnit tests will exercise all features of the service.
* The SumPrimesServer class has a main method. If this is run, the server will be started. Hitting url http://127.0.0.1:8080/primes/sum/10000000 in a browser will then return the sum of all primes up to 10 million
