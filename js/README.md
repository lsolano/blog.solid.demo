# Liskov substitution principle
"objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program."

# Requirements
- Node.js 4.x
- mocha (test framework)


# Run the tests 
- (*first time only*) `npm install -g mocha`
- Run tests: `mocha`


# Use in your own code: 
    var SOLIDLogs = require('${pathToLib}/SOLIDLogs');
    var logger = SOLIDLogs.getLogger();
    var appender = SOLIDLogs.getConsoleAppender();
    logger.addAppender(appender);
    
    logger.debug('Hello World!');
    logger.error('The world is on fire!!!');


