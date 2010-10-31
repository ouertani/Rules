

package me.jtunisie.specifications

import org.specs.SpecificationWithJUnit
import org.specs.mock.Mockito

class BasicSpec extends SpecificationWithJUnit with Mockito {

  object Identity extends ASpecification[Boolean] {
    override def -> (b: Boolean)= b
  }
  object Neg extends ASpecification[Boolean] {
    override def -> (b: Boolean)= ! b
  }
  object AlwaysOk extends ASpecification[Boolean] {
    override def -> (b: Boolean)= true
  }
  object AlwaysKo extends ASpecification[Boolean] {
    override def -> (b: Boolean)=  false
  }

  """ basic AND """ in {
    val ooook=    AlwaysOk && AlwaysOk
    true mustBe (ooook) -> (true)
  
    val kooo = AlwaysKo &&  AlwaysKo
    false mustBe (kooo)-> (true)

    val  okko = AlwaysOk && AlwaysKo
    false mustBe (okko)-> (true)

    val  kook = AlwaysOk && AlwaysKo
    false mustBe (kook)-> (true)

    val okokokok=    AlwaysOk && AlwaysOk &&  AlwaysOk && AlwaysOk
    true mustBe (okokokok)-> (true)

    val okokokko=    AlwaysOk && AlwaysOk &&  AlwaysOk && AlwaysKo
    false mustBe (okokokko)-> (true)

    val kookokok=   AlwaysKo && AlwaysOk && AlwaysOk &&  AlwaysOk
    false mustBe (kookokok)-> (true)
  }

  """ basic OR """ in {
    val ooook=    AlwaysOk || AlwaysOk
    true mustBe (ooook)-> (true)

    val kooo = AlwaysKo ||   AlwaysKo
    false mustBe (kooo)-> (true)

    val  okko = AlwaysOk ||  AlwaysKo
    true mustBe (okko)-> (true)

    val  kook = AlwaysOk ||  AlwaysKo
    true mustBe (kook)-> (true)

    val okokokok=    AlwaysOk ||  AlwaysOk ||   AlwaysOk ||  AlwaysOk
    true mustBe (okokokok)-> (true)

    val okokokko=    AlwaysOk ||  AlwaysOk ||   AlwaysOk ||  AlwaysKo
    true mustBe (okokokko)-> (true)

    val kookokok=   AlwaysKo || AlwaysOk ||  AlwaysOk ||   AlwaysOk
    true mustBe (kookokok)-> (true)
  }


  """ basic Not """ in {
    val ooook=     ! AlwaysOk
    false mustBe (ooook)-> (true)

    val kooo = ! AlwaysKo
    true mustBe (kooo)-> (true)

  }

  """or and and""" in {

    val orandko = (AlwaysOk || AlwaysKo) && AlwaysKo
    false mustBe (orandko)-> (true)

    val orandok = AlwaysOk || (AlwaysKo && AlwaysKo)
    true mustBe (orandok)-> (true)

    val orand = AlwaysOk || AlwaysKo && AlwaysKo
    true mustBe (orand)-> (true)

    val andorko= AlwaysKo && (AlwaysOk || AlwaysKo)
    false mustBe (andorko)-> (true)

    val andorok =  (AlwaysKo && AlwaysKo) || AlwaysOk
    true mustBe (andorok)-> true

    val andor =  AlwaysKo && AlwaysKo || AlwaysOk
    true mustBe (andor)-> true
  }


  """and and or""" in {
    val andorok = (AlwaysKo && AlwaysOk) || AlwaysOk
    true mustBe (andorok)-> true

    val andorko = AlwaysKo && (AlwaysOk || AlwaysOk)
    false mustBe andorko-> true


    val andor = AlwaysKo && AlwaysOk || AlwaysOk
    true mustBe andor-> true

  }

  """not not""" in {
    val notnotok = ! (! AlwaysOk )
    true mustBe notnotok -> true


    true mustBe  ! Neg -> true
    false mustBe  ! Neg -> false
  }

  """not or""" in {
    val  ornot = AlwaysKo || !AlwaysOk
    false mustBe ornot -> true

    val notor = !AlwaysOk || AlwaysKo
    false mustBe notor -> true
  }

  """not and""" in {
    (!AlwaysKo && AlwaysOk ) -> true mustBe true
    (AlwaysOk && !AlwaysKo ) -> true mustBe true
    (!AlwaysKo && !AlwaysKo ) -> true mustBe true
    !(!AlwaysOk && !AlwaysKo ) -> true mustBe true
  }
}
