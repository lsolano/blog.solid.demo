var assert = require('assert'),
	SOLIDLogs = require('../SOLIDLogs'),
	mocks = require('./mocks'),
	ConsoleMock = mocks.ConsoleMock,
	DBMSRepoMock = mocks.DBMSRepoMock;

describe('Dirty::ConsoleAppender', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new ConsoleMock(),
  		appender = SOLIDLogs.getDirtyConsoleAppender(mock);
  	
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

describe('Dirty::MySQLAppender', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new DBMSRepoMock(),
  		appender = SOLIDLogs.getDirtyMySQLAppender(mock);
  	
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

describe('Dirty::ConsoleAppender(maxLength=10)', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new ConsoleMock(),
  		appender = SOLIDLogs.getDirtyConsoleAppender(mock, 10);
  	
  	logger.addAppender(appender);

    var message = "01234567890";
    it('should output to the console debug method up to maxLength', function () {
      
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, "0123456789");
    });

    it('should output to the console error method up to maxLength', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, "0123456789");
    });
  });
});

describe('Dirty::MySQLAppender(maxLength=10)', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new DBMSRepoMock(),
  		appender = SOLIDLogs.getDirtyMySQLAppender(mock, 10);
  	
  	logger.addAppender(appender);

    var message = "01234567890";
    it('should output to the console debug method up to maxLength', function () {
      logger.debug(message);
      assert.equal(mock.messages.length, 1);
      assert.equal(mock.messages[0].text, "0123456789");
    });

    it('should output to the console error method up to maxLength', function () {
      logger.error(message);
      assert.equal(mock.messages.length, 2);
      assert.equal(mock.messages[1].text, "0123456789");
    });
  });
});