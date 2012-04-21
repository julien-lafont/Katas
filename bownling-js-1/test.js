var should = require('should');
var bowling = require('./bowling').bowling;

describe('Bowling', function () {
	
	var game;
	
	beforeEach(function() {
		game = init();
	});
	
	it('Le joueur commence avec le score de 0', function () {
		(typeof game).should.equal(typeof {});
		(game.score()).should.equal(0);
		
	})
	
	it("Aura un score de 0 si il ne touche aucune quille", function () {
		game.testLancerSuccessifs(20, 0);
		(game.score()).should.equal(0);
	})
	
	it("aura un score de 20 si il touche une quille à chaque fois", function() {
		game.testLancerSuccessifs(20, 1);
		(game.score()).should.equal(20);
	})
	
	it("pourra faire un spare et le score du lancer suivant sera doublé", function() {
		game.testLancerSuccessifs(2, 5);
		game.lancer(3);
		game.testLancerSuccessifs(17, 0);
		(game.score()).should.equal(5+5+2*3);
	})
	
	it("pourra faire un strike et le score ", function() {
		game.lancer(10);
		game.lancer(3);
		game.lancer(4);
		game.testLancerSuccessifs(17, 0);
		(game.score()).should.equal(24);
	});
	
	it("pourra avoir le score maximum", function() {
		game.testLancerSuccessifs(12, 10);
		(game.score()).should.equal(300);
	})

	
});

function init(game) {
	var game = bowling.start();
	
	game.testLancerSuccessifs = function(nb, pt) {
		for (var i=0; i<nb; i++) {
			this.lancer(pt);
		}
	}
	
	return game;
}