package fr.studiodev.greed

import org.specs2.mutable._

class AppSpec extends Specification {
	
	"The greed calculator" should {
		
		"throw an error if there is less than 5 dices" in {
			Greed(Nil) must throwAn[IllegalArgumentException]
			Greed(List()) must throwAn[IllegalArgumentException]
			Greed(List(1, 2, 3, 4)) must throwAn[IllegalArgumentException]
		}
		
		"return a positive score" in {
			Greed(List(1, 2, 4, 5, 6)) must be_>=(0)
		}
		
		"add 1000 points for a triple 1" in {
			Greed(List(1, 1, 1, 0, 0)) must_==(1000) 
		}
		
		"add X*100 points for a triple X" in {
			2.to(6).map(i => Greed(List(i, i, i, 0, 0)) must_==(i * 100))
		}
		
		"add 100 points for each additionally 1 which is not a triple 1" in {
			Greed(List(1, 0, 0, 0, 0)) must_==(100)
			Greed(List(1, 1, 0, 0, 0)) must_==(200)
			Greed(List(1, 1, 1, 1, 0)) must_==(1100)
			Greed(List(1, 1, 1, 1, 1)) must_==(1200)
		}
		
		"add 50 points for each additionally 5 which is not a triple 5" in {
			Greed(List(5, 0, 0, 0, 0)) must_==(50)
			Greed(List(5, 5, 0, 0, 0)) must_==(100)
			Greed(List(5, 5, 5, 5, 0)) must_==(550)
			Greed(List(5, 5, 5, 5, 5)) must_==(600)
		}
		
		"work with all complexe cases" in {
			Greed(List(1, 1, 1, 5, 1)) must_==(1150)
			Greed(List(2, 3, 4, 6, 2)) must_==(0)
			Greed(List(3, 4, 5, 3, 3)) must_==(350)
			Greed(List(1, 5, 1, 2, 4)) must_==(250)
			Greed(List(5, 5, 5, 5, 5)) must_==(600)
		}
	}
}
