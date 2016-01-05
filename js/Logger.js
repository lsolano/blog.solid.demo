function Logger() {
  this.appenders = [];
}

Logger.prototype.addAppender = function(appender) {
	if (!!appender.append && (typeof appender.append === 'function')) {
		this.appenders.push(appender);
	}
}

Logger.prototype.debug = function(message) {
	this.appenders.forEach(function(appender) {
		appender.append(message, "Debug");
	});
};

Logger.prototype.error = function(message) {
	this.appenders.forEach(function(appender) {
		appender.append(message, "Error");
	});
};

module.exports = Logger;