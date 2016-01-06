var AppenderBase = require('./BlackHoleAppender.js');

function MySQLAppender(rdbmsRepository, config) {
	var finalArgs = ['mySQL', config];
	Array.prototype.forEach.call(arguments, function(arg) {
		finalArgs.push(arg);
	});
	AppenderBase.apply(this, Array.prototype.slice.call(finalArgs, 2));

	this.repository = rdbmsRepository;
	this.maxLength = Math.max(config.mySQLMaxLength, this.maxLength);
}

MySQLAppender.prototype = new AppenderBase('mySQL', {});

MySQLAppender.prototype.write = function(finalMessage, category) {
  switch (category) {
		case "Error":
			this.repository.persist( { text: finalMessage, category: category });
			break;
		default:
			this.repository.persist( { text: finalMessage, category: "Debug" });
	}
};

module.exports = MySQLAppender;