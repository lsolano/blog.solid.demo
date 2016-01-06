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

exports.ConsoleMock = ConsoleMock;
exports.DBMSRepoMock = DBMSRepoMock;