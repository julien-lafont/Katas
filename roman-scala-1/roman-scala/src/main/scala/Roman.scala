package fr.studiodev.roman

import scala.collection.immutable._

object Roman {

	private val table = ListSet(
		1000 -> "M", 900 -> "CM", 
		500  -> "D", 400 -> "CD", 
		100  -> "C", 90  -> "XC", 
		50   -> "L", 40  -> "XL",
		10   -> "X", 9   -> "IX",
		5    -> "V", 4   -> "IV",
		1    -> "I")

	// 1er essai : difficilement compréhensible, mais 100% immutable
	def apply(num: Integer) : String = {
	
		val (roman, _) = table.foldRight(("", num))((tuple, result) => {
			val (algebric, roman) = tuple
			val (acc, rest)       = result
			
			val multiple = rest / algebric
			val pattern  = (for (i <- 1 to multiple) yield roman) mkString
			
			(acc + pattern, rest % algebric)		
		})

		roman
	}
	
}