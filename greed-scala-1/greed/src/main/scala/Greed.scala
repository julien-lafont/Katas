package fr.studiodev.greed

object Greed {
	
	def apply(numbers: List[Int]) = {

		if (numbers.size != 5) throw new IllegalArgumentException("5 dices are required")
		
		val grouped = numbers.groupBy(i => i).mapValues(_.size)
	
		val scoreTriple = grouped.foldLeft(0)((acc, i) => 
			if (i._2 >= 3) acc + i._1 * 
					(if (i._1 == 1) 1000 else 100) 
			else acc)
			
		val score1 = grouped.getOrElse(1, 0) % 3 * 100
		val score5 = grouped.getOrElse(5, 0) % 3 * 50

		scoreTriple + score1 + score5
	}
}
