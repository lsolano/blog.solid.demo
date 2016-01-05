const maxMessageLength = Math.pow(2, 14);

var AppenderBase = require('./BlackHoleAppender.js');

function MySQLAppender(rdbmsRepository, maxLength) {
	this.repository = rdbmsRepository;
	this.maxLength = maxLength || 0;
	var finalArgs = ['MySQLAppender'];
	Array.prototype.forEach.call(arguments, function(arg) {
		finalArgs.push(arg);
	});
	AppenderBase.apply(this, Array.prototype.slice.call(finalArgs));
}

MySQLAppender.prototype = new AppenderBase('MySQLAppender');

MySQLAppender.prototype.write = function(finalMessage, category) {
  switch (category) {
		case "Error":
			this.repository.persist( { text: finalMessage, category: category });
			break;
		default:
			this.repository.persist( { text: finalMessage, category: "Debug" });
	}
};

MySQLAppender.prototype.normalizeMessage = function(message, category) {
	var msg = (message || '');
	var allowedLength = (this.maxLength || maxMessageLength);
	msg = msg.length > allowedLength? msg.substring(0, allowedLength) : msg;
	return msg;
}

module.exports = MySQLAppender;