var assert = require('assert'),
	SOLIDLogs = require('../SOLIDLogs'),
  mocks = require('./mocks'),
  ConsoleMock = mocks.ConsoleMock,
  DBMSRepoMock = mocks.DBMSRepoMock,
  msgHandling = require('../messageHandling');

describe('Clean::ConsoleAppender', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new ConsoleMock(),
  		appender = SOLIDLogs.getConsoleAppender(mock);
  	
  	logger.addAppender(appender);

    it('should output to the console debug method', function () {
      var message = "Hello World!";
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, message);
    });

    it('should output to the console error method', function () {
      var message = "The world is on fire!!!";
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, message);
    });
  });
});

describe('Clean::MySQLAppender', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new DBMSRepoMock(),
  		appender = SOLIDLogs.getMySQLAppender(mock);
  	
  	logger.addAppender(appender);

    it('should output to the console debug method', function () {
      var message = "Hello World!";
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, message);
    });

    it('should output to the console error method', function () {
      var message = "The world is on fire!!!";
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, message);
    });
  });
});

var appendersConfig = {
    baseMaxLength: 3,
    consoleMaxLength: 5,
    mySQLMaxLength: 4
  };

describe('Clean::ConsoleAppender(maxLength=5)', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new ConsoleMock(),
  		appender = SOLIDLogs.getConsoleAppender(mock, appendersConfig);
  	
  	logger.addAppender(appender);
    var message = "123456";
    it('should output to the console debug method up to maxLength', function () {
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, "12345");
    });

    it('should output to the console error method up to maxLength', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, "12345");
    });
  });
});

describe('Clean::MySQLAppender(maxLength=4)', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new DBMSRepoMock(),
  		appender = SOLIDLogs.getMySQLAppender(mock, appendersConfig);
  	
  	logger.addAppender(appender);
    var message = "12345";
    it('should output to the console debug method up to maxLength', function () {
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, "1234");
    });

    it('should output to the console error method up to maxLength', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, "1234");
    });
  });
});

describe('Clean::ConsoleAppender(maxLength=5, handlingMode=Ignore)', function() {
  describe('#append', function () {
    var logger = SOLIDLogs.getLogger(),
      mock = new ConsoleMock(),
      appender = SOLIDLogs.getConsoleAppender(mock, appendersConfig);
    
    appender.messageHandling = msgHandling.ignore;
    logger.addAppender(appender);

    var message = "123456";
    it('should ignore debug message larger than maxLength', function () {
      logger.debug(message);
      assert.equal(mock.messages.length, 0);
    });

    it('should ignore error message larger than maxLength', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 0);
    });
  });
});

describe('Clean::MySQLAppender(maxLength=4, handlingMode=Ignore)', function() {
  describe('#append', function () {
    var logger = SOLIDLogs.getLogger(),
      mock = new DBMSRepoMock(),
      appender = SOLIDLogs.getMySQLAppender(mock, appendersConfig);
    
    appender.messageHandling = msgHandling.ignore;
    logger.addAppender(appender);

    var message = "12345";
    it('should ignore debug message larger than maxLength', function () {
      logger.debug(message);
      assert.equal(mock.messages.length, 0);
    });

    it('should ignore error message larger than maxLength', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 0);
    });
  });
});

var badAppendersConfig = {
  baseMaxLength: 6,
  consoleMaxLength: 4,
  mySQLMaxLength: 4
};

describe('Clean::ConsoleAppender(maxLength=4, baseLength=6)', function() {
  describe('#append', function () {
    var logger = SOLIDLogs.getLogger(),
      mock = new ConsoleMock(),
      appender = SOLIDLogs.getConsoleAppender(mock, badAppendersConfig);
    
    logger.addAppender(appender);

    var message = "1234567";
    it('should use base length instead (max) for debug message', function () {
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, "123456");
    });

    it('should use base length instead (max) for error message', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, "123456");
    });
  });
});

describe('Clean::MySQLAppender(maxLength=4, baseLength=6)', function() {
  describe('#append', function () {
    var logger = SOLIDLogs.getLogger(),
      mock = new DBMSRepoMock(),
      appender = SOLIDLogs.getMySQLAppender(mock, badAppendersConfig);
    
    logger.addAppender(appender);

    var message = "1234567";
    it('should use base length instead (max) for debug message', function () {
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, "123456");
    });

    it('should use base length instead (max) for error message', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, "123456");
    });
  });
});