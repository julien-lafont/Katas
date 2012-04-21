package fr.studiodev.greed

object Greed {
	
	def apply(numbers: List[Int]) = {

		if (numbers.size != 5) throw new IllegalArgumentException("5 dices are required")
		
		//val grouped = numbers.groupBy(i => i).mapValues(_.size)
		val grouped = 0.to(6).map(i => numbers.count(_==i))
	
		val scoreTriple = grouped.zipWithIndex.foldLeft(0)((acc, i) => 
			if (i._1 >= 3) acc + i._2 * 
					(if (i._2 == 1) 1000 else 100) 
			else acc)
			
		val score1 = grouped(1) % 3 * 100
		val score5 = grouped(5) % 3 * 50

		scoreTriple + score1 + score5
	}
}
