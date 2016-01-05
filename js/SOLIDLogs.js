var Logger = require('./Logger.js'),
	DirtyConsoleAppender = require('./dirty/ConsoleAppender'),
	DirtyMySQLAppender = require('./dirty/MySQLAppender');

function getLogger() {
	return new Logger();
}

function getDirtyConsoleAppender(cons, maxLength) {
	return new DirtyConsoleAppender(cons, maxLength);
}

function getDirtyMySQLAppender(repo, maxLength) {
	return new DirtyMySQLAppender(repo, maxLength);
}

exports.getLogger = getLogger;
exports.getDirtyConsoleAppender = getDirtyConsoleAppender;
exports.getDirtyMySQLAppender = getDirtyMySQLAppender;