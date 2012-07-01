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

	// 2ème essai : boucle for simplifiée grâce à la fonction ’String.*(times)’
	def apply(num: Int) : String = {

		val (roman, _) = table.foldRight(("", num))((tuple, result) => {
			val (arabicNumber, romanNumber) = tuple
			val (romanString, remainder)  = result

			val romanUnit = romanNumber * (remainder / arabicNumber)
			(romanString + romanUnit, remainder % arabicNumber) 
		})

		roman
	}

	// ------------------

	// Version One-Liner
	def convert(n: Int) = table.:\(("", n))((e, r) => (r._1 + (e._2 * (r._2 / e._1)), r._2 % e._1 ))._1
	
}