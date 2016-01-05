function BlackHoleAppender(args) {
  this.name = (args.name || 'defaultAppender');
};

BlackHoleAppender.prototype.normalizeMessage = function(message, category) {
	return message;
}

BlackHoleAppender.prototype.append = function(message, category) {
  var finalMessage = this.normalizeMessage(message, category);
  this.write(finalMessage, category);
};

BlackHoleAppender.prototype.write = function(finalMessage, category) {
  /* To be implemented by sub-classes */
};

module.exports = BlackHoleAppender;