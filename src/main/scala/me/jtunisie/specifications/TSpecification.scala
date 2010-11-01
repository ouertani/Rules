

package me.jtunisie.specifications


trait TSpecification [T]{
  def   ->(candidate :T ):Boolean
  def   ||( specification : TSpecification[T]):TSpecification[T]
  def   &&(specification :TSpecification[T] ):TSpecification[T]
  def   unary_!():TSpecification[T]
  //Addition
  def |  ( specification : TSpecification[T]):TSpecification[T]
  def &  (specification :TSpecification[T] ):TSpecification[T]
}

 abstract class ASpecification[T] extends  TSpecification [T]{
  
  def   ->(candidate :T ):Boolean
  def   ||( s : TSpecification[T]):TSpecification[T] =  OrSpecification(this,s)
  def   &&(s :TSpecification[T] ):TSpecification[T] =  AndSpecification(this,s)
  def   unary_!():TSpecification[T] =  NotSpecification(this)

  // Recurssive Add-0ns
  def   | ( s : TSpecification[T]):TSpecification[T] = new ROrSpecification(this,s)
  def   &(s :TSpecification[T] ):TSpecification[T] = new RAndSpecification(this,s)
 
}


case class AndSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  override def   -> (candidate :T ):Boolean= (true /: s) (_ -> candidate  &&  _ -> candidate)

}
case class OrSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  override def   ->(candidate :T ):Boolean= (false /: s) (_ -> candidate  ||  _ -> candidate)
}
case class NotSpecification[T]( s: TSpecification[T] ) extends ASpecification[T]{
  override def   ->(candidate :T )=  ! (s -> candidate )
}


case class RAndSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  override def   ->(candidate :T ):Boolean= (s :\  true) (_ -> candidate  &&  _ -> candidate)
}
case class ROrSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  override def   ->(candidate :T ):Boolean= (s :\ false) (_ -> candidate  ||  _ -> candidate)
}