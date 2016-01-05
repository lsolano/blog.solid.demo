var assert = require('assert'),
	SOLIDLogs = require('../SOLIDLogs');

function ConsoleMock() {
	this.messages = [];
}

ConsoleMock.prototype.debug = function(message) {
	this.messages.push({ category: "Debug", text: message });
};
ConsoleMock.prototype.error = function(message) {
	this.messages.push({ category: "Error", text: message });
};

function DBMSRepoMock() {
	this.messages = [];
}

DBMSRepoMock.prototype.persist = function(message) {
	this.messages.push({ category: message.category, text: message.text });
};

describe('ConsoleAppender', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new ConsoleMock(),
  		consoleAppender = SOLIDLogs.getDirtyConsoleAppender(mock);
  	
  	logger.addAppender(consoleAppender);

    it('should output to the console debug method', function () {
      var message = "Hello World!";
      logger.debug(message);
      assert.equal(1, mock.messages.length);
      assert.equal(message, mock.messages[0].text);
    });

    it('should output to the console error method', function () {
      var message = "The world is on fire!!!";
      logger.error(message);
      assert.equal(2, mock.messages.length);
      assert.equal(message, mock.messages[1].text);
    });
  });
});

describe('MySQLAppender', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new DBMSRepoMock(),
  		consoleAppender = SOLIDLogs.getDirtyMySQLAppender(mock);
  	
  	logger.addAppender(consoleAppender);

    it('should output to the console debug method', function () {
      var message = "Hello World!";
      logger.debug(message);
      assert.equal(1, mock.messages.length);
      assert.equal(message, mock.messages[0].text);
    });

    it('should output to the console error method', function () {
      var message = "The world is on fire!!!";
      logger.error(message);
      assert.equal(2, mock.messages.length);
      assert.equal(message, mock.messages[1].text);
    });
  });
});

describe('ConsoleAppender(maxLength=10)', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new ConsoleMock(),
  		consoleAppender = SOLIDLogs.getDirtyConsoleAppender(mock, 10);
  	
  	logger.addAppender(consoleAppender);

    it('should output to the console debug method up to maxLength', function () {
      var message = "01234567890";
      logger.debug(message);
      assert.equal(1, mock.messages.length);
      assert.equal("0123456789", mock.messages[0].text);
    });

    it('should output to the console error method up to maxLength', function () {
      var message = "01234567890";
      logger.error(message);
      assert.equal(2, mock.messages.length);
      assert.equal("0123456789", mock.messages[1].text);
    });
  });
});

describe('MySQLAppender(maxLength=10)', function() {
  describe('#append', function () {
  	var logger = SOLIDLogs.getLogger(),
  		mock = new DBMSRepoMock(),
  		consoleAppender = SOLIDLogs.getDirtyMySQLAppender(mock, 10);
  	
  	logger.addAppender(consoleAppender);

    it('should output to the console debug method up to maxLength', function () {
      var message = "01234567890";
      logger.debug(message);
      assert.equal(1, mock.messages.length);
      assert.equal("0123456789", mock.messages[0].text);
    });

    it('should output to the console error method up to maxLength', function () {
      var message = "01234567890";
      logger.error(message);
      assert.equal(2, mock.messages.length);
      assert.equal("0123456789", mock.messages[1].text);
    });
  });
});