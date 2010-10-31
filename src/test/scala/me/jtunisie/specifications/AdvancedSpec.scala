
package me.jtunisie.specifications
import org.specs.SpecificationWithJUnit
import org.specs.mock.Mockito
import scala.testing.SUnit._
class AdvancedSpec  extends SpecificationWithJUnit with Mockito {
  object Identity extends ASpecification[Boolean] {
    override def -> (b: Boolean)= b
  }

  object NoEval extends ASpecification[Boolean] {
    override def -> (b: Boolean)= throw new AssertionError("should not be evaluated!")
  }
  """ AND """ in {
   
    false mustBe (Identity  &&  NoEval) -> (false)
    (NoEval  && Identity ) -> (false)  must throwA[AssertionError]
    true mustBe !(Identity  &&  NoEval) -> (false)
    false mustBe (Identity  &&  !NoEval) -> (false)
    false mustBe (Identity  &&  !NoEval || Identity) -> (false)
  }

  """ OR """ in {

    true mustBe (Identity  ||  NoEval) -> (true)
    (NoEval  || Identity ) -> (true)  must throwA[AssertionError]
    false mustBe !(Identity  ||  NoEval) -> (true)
    true mustBe (Identity  ||  !NoEval) -> (true)
    true mustBe (Identity  ||  !NoEval && Identity ) -> (true)
  }

  """ RAND """ in {
    (Identity  &  NoEval) -> false  must throwA[AssertionError]
    (NoEval  &  Identity) -> false  must throwA[AssertionError]
   
  }


  """ ROR """ in {
    (Identity  |  NoEval) -> true  must throwA[AssertionError]
    (NoEval  |  Identity) -> true  must throwA[AssertionError]

  }
}
