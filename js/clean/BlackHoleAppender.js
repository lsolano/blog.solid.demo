var msgHandling = require('../messageHandling');

function BlackHoleAppender(name, config) {
  this.name = (name || 'blackHole');
  this.maxLength = (!!config.baseMaxLength? config.baseMaxLength : 0);
  this._messageHandling = msgHandling.truncate;
};

Object.defineProperty(BlackHoleAppender.prototype, 'messageHandling', {
	get: function() {
		return this._messageHandling;
	},
	set: function(messageHandling) {
		return this._messageHandling = messageHandling;
	}
});

BlackHoleAppender.prototype.normalizeMessage = function(message, category) {
	var msg = (message || '');

	if (msg.length > this.maxLength) {

		if (this.messageHandling === msgHandling.truncate) {
			msg = msg.substring(0, this.maxLength);
		} else {
			msg = null;
		}
	}
	
	return msg;
}

BlackHoleAppender.prototype.append = function(message, category) {
  var finalMessage = this.normalizeMessage(message, category);

  if (!!finalMessage) {
  	this.write(finalMessage, category);
  }
};

BlackHoleAppender.prototype.write = function(finalMessage, category) {
  /* To be implemented by sub-classes */
};

module.exports = BlackHoleAppender;