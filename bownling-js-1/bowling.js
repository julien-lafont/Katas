var bowling =  {
	start: function() {
		return {
			lancers: [],
			lancerCourant: 0,
			lancer: function(nb) {
				this.lancers[this.lancerCourant] = nb;
				this.lancerCourant++;
			},
			score: function() {
				var score = 0;
				var coup = 0;
				for (var frame=0; frame<10; frame++) {
					
					if (this.isStrike(frame)) {
						score += this.scoreCoup(coup) + this.scoreCoup(coup+1) + this.scoreCoup(coup+2);
						coup++;
					} else if (this.isSpare(frame)) {
						score += 10 + this.scoreCoup(coup+2);
						coup+=2;
					} else {
						score += this.scoreCoup(coup) + this.scoreCoup(coup+1);
						coup +=2;
					}
					
				}
				return score;
			},
			isSpare: function(i) {
				return (this.lancers[i] + this.lancers[i+1]) == 10;
			},
			isStrike: function(i) {
				return (this.lancers[i] == 10);
			},
			scoreCoup: function (coup) {
				return 	this.lancers[coup] || 0;
			}
		};
	}
};


module.exports.bowling = bowling;