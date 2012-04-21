var should = require('should');
var tennis = require('./tennis');

describe("Système de calcul du score au tennis", function() {
	
	it("Une partie est jouée entre exactement deux joueurs", function() {
		var game = tennis.game("Julien", "Marc");
		(game.players[0].name).should.equal("Julien");
		(game.players[1].name).should.equal("Marc");
	})
	
	it("Au début de la partie, le score est de 0-0", function() {
		newTestGame().forEachPlayer(function(player) {
			(player.score).should.equal(0);
		})
	});
	
	it("Quand un joueur gagne la première balle, son score est de 15", function() {
		var game = newTestGame();
		game.playerWin(0);
		(game.players[0].score).should.equal(15);
	})
	
	it("Quand un joueur gagne 4x d'affilé, le match est terminé et il est le gagnant", function() {
		var game = newTestGame();
		for (var i in fourTimes) game.playerWin(0);
		(game.winner).should.equal(0);
	})
	
	it("Quand un joueur gagne 3 balles et son adversaire 4, le match n'est pas terminé, le score est 'Deuce'", function() {
		var game = initDeuceGame();
		(game.winner).should.not.be.ok;
		game.forEachPlayer(function(player) { (player.score).should.equal(40); });
	})
	
	it(".. Et le second second joueur a l'Avantage", function() {
		var game = initDeuceGame();
		(game.players[1].advantage).should.be.true;
		(game.players[0].advantage).should.be.false;
	})
	
	it("Quand un joueur qui a l'avantage marque un nouveau point, il gagne le match", function() {
		var game = initDeuceGame();
		game.playerWin(1);
		(game.winner).should.equal(1);
	})
	
	it("Quand le joueur qui a l'avantage perd le point, le score revient à 'Deuce'", function() {
		var game = initDeuceGame();
		game.playerWin(0);
		(game.players[0].advantage).should.be.false;
		(game.winner).should.not.be.ok;
	})
	
	it("Plusieur situations 'Deuce' peuvent s'enchainer sans que le match soit terminé", function() {
		var game = initDeuceGame();
		game.playerWin(0);
		game.playerWin(1);
		game.playerWin(1);
		(game.winner).should.equal(1);
	})
	
	it("Il n'est pas possible de marquer de point une fois le match terminé", function() {
		var game = initDeuceGame();
		game.playerWin(1);
		(function(){
		  game.playerWin(1);
		}).should.throw("game ended");
	})
	
});

/**
 * Helper to init a new game and add tests methods
 */
function newTestGame() {
	var game = tennis.game();
	
	game.forEachPlayer = function(f) {
		game.players.forEach(function(player) {
			f(player);
		})
	}
	
	return game;
}

/**
 * Init a new test game where the score is 40/40A (second player has advantage)
 */
function initDeuceGame() {
	var game = newTestGame();
	for (var i in threeTimes) game.playerWin(0);
	for (var i in fourTimes) game.playerWin(1);
	return game;
}

// Helpers de flemmard
var threeTimes = [1,2,3];
var fourTimes = [1,2,3,4];