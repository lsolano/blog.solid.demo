var AppenderBase = require('./BlackHoleAppender.js');

function ConsoleAppender(cons, config) {
	var finalArgs = ['console', config];
	Array.prototype.forEach.call(arguments, function(arg) {
		finalArgs.push(arg);
	});

	AppenderBase.apply(this, Array.prototype.slice.call(finalArgs, 2));

	this.maxLength = Math.max(config.consoleMaxLength, this.maxLength);
	this.console = (cons || console);
}

ConsoleAppender.prototype = new AppenderBase('console', {});

ConsoleAppender.prototype.write = function(finalMessage, category) {
  switch (category) {
		case "Error":
			this.console.error(finalMessage);
			break;
		default:
			this.console.debug(finalMessage);
	}
};

module.exports = ConsoleAppender;
