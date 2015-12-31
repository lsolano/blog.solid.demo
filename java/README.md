# Single responsibility principle
"A class should have only a single responsibility (i.e. only one potential change in the software's specification  should be able to affect the specification of the class)."

# Dependency inversion principle
"One should Depend upon Abstractions. Do not depend upon concretions."

# Browsing the Code
To browse the code just look it online, or clone the repository and open it in with Eclipse IDE, or your favorite editor.

# Running the Code
### Basic requirements
- Java SE 1.8 JDK or higher 
- Apache Maven 3.3 or higher

### From the command line
With java and maven properly installed and on the PATH system variable run:

    $ mvn test

At the end you must see something like this

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 4.689 s
    [INFO] Finished at: 2015-12-31T08:59:09-04:00
    [INFO] Final Memory: 15M/199M
    [INFO] ------------------------------------------------------------------------


## From the IDE (eclipse)
Look for the folder `\src\test\java` select it, and use the option `Run As... JUnit Test`.