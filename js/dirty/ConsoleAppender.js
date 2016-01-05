const maxMessageLength = Math.pow(2, 30);

var AppenderBase = require('./BlackHoleAppender.js');

function ConsoleAppender(cons, maxLength) {
	this.console = (cons || console);
	this.maxLength = maxLength || 0;
	var finalArgs = ['ConsoleAppender'];
	Array.prototype.forEach.call(arguments, function(arg) {
		finalArgs.push(arg);
	});
	AppenderBase.apply(this, Array.prototype.slice.call(finalArgs));
}

ConsoleAppender.prototype = new AppenderBase('ConsoleAppender');

ConsoleAppender.prototype.write = function(finalMessage, category) {
  switch (category) {
		case "Error":
			this.console.error(finalMessage);
			break;
		default:
			this.console.debug(finalMessage);
	}
};

ConsoleAppender.prototype.normalizeMessage = function(message, category) {
	var msg = (message || '');
	var allowedLength = (this.maxLength || maxMessageLength);
	msg = msg.length > allowedLength? msg.substring(0, allowedLength) : msg;
	return msg;
}

module.exports = ConsoleAppender;
