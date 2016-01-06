var Logger = require('./Logger.js'),
	DirtyConsoleAppender = require('./dirty/ConsoleAppender'),
	DirtyMySQLAppender = require('./dirty/MySQLAppender'),
	ConsoleAppender = require('./clean/ConsoleAppender'),
	MySQLAppender = require('./clean/MySQLAppender'),
	appendersConfig = {
		baseMaxLength: Math.pow(2, 14),
		consoleMaxLength: Math.pow(2, 30),
		mySQLMaxLength: Math.pow(2, 14)
	};

function getConfig() {
	return appendersConfig;
}

function mergeAndCopy(a, b) {
	var dest = {};
	Object.keys(a).forEach(function (key) {
		dest[key] = a[key];
	});

	Object.keys(b).forEach(function (key) {
		dest[key] = b[key];
	});

	return dest;
}

function getLogger() {
	return new Logger();
}

function getDirtyConsoleAppender(cons, maxLength) {
	return new DirtyConsoleAppender(cons, maxLength);
}

function getDirtyMySQLAppender(repo, maxLength) {
	return new DirtyMySQLAppender(repo, maxLength);
}

function getConsoleAppender(cons, config) {
	var newConfig = mergeAndCopy(getConfig(), config || {});
	return new ConsoleAppender(cons, newConfig);
}

function getMySQLAppender(repo, config) {
	var newConfig = mergeAndCopy(getConfig(), config || {});
	return new MySQLAppender(repo, newConfig);
}

exports.getLogger = getLogger;
exports.getDirtyConsoleAppender = getDirtyConsoleAppender;
exports.getDirtyMySQLAppender = getDirtyMySQLAppender;

exports.getConsoleAppender = getConsoleAppender;
exports.getMySQLAppender = getMySQLAppender;