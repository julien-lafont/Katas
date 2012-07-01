package fr.studiodev.roman

object Roman {

	private val table = List(
		(1000 , "M"),  (900 , "CM"), 
		(500  , "D"),  (400 , "CD"), 
		(100  , "C"),  (90  , "XC"), 
		(50   , "L"),  (40  , "XL"),
		(10   , "X"),  (9   , "IX"),
		(5    , "V"),  (4   , "IV"),
		(1    , "I"))

	def apply(num: Int) = convertWithRec(num)

	// ------------------
	// Première version à base de foldLeft
	// ------------------
	def convertWithFold(num: Int) = {

		val (roman, _) = table.foldLeft(("", num))((result, tuple) => {
			val (arabicNumber, romanNumber) = tuple
			val (romanString, remainder)    = result

			val romanUnit = romanNumber * (remainder / arabicNumber)
			(romanString + romanUnit, remainder % arabicNumber) 
		})

		roman
	}

	// ------------------
	// Seconde version avec récursivité
	// ------------------
	def convertWithRec(num: Int) = {
		def convert(value: Int, table: List[(Int,String)]) : String = table.headOption match {
			case None => ""
			case Some((arabic, roman)) => roman * (value / arabic) + convert(value % arabic, table.tail)
		}
		convert(num, table)
	}

	// ------------------
	// Version One-Liner
	// ------------------
	def convertOneLiner(n: Int) = table./:(("", n))((r, e) => (r._1 + (e._2 * (r._2 / e._1)), r._2 % e._1 ))._1
	
}