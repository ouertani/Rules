

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
  def   ||( s : TSpecification[T]):TSpecification[T] = new OrSpecification(this,s)
  def   &&(s :TSpecification[T] ):TSpecification[T] = new AndSpecification(this,s)
  def   unary_!():TSpecification[T] = new NotSpecification(this)

  // Recurssive Addon
  def   | ( s : TSpecification[T]):TSpecification[T] = new ROrSpecification(this,s)
  def   &(s :TSpecification[T] ):TSpecification[T] = new RAndSpecification(this,s)
 
}

class AndSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  override def   ->(candidate :T ):Boolean= (true /: s) (_ -> candidate  &&  _ -> candidate)

}
class OrSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  override def   ->(candidate :T ):Boolean= (false /: s) (_ -> candidate  ||  _ -> candidate)
}
class NotSpecification[T]( s: TSpecification[T] ) extends ASpecification[T]{
  override def   ->(candidate :T )=  ! (s -> candidate )
}


class RAndSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  
  override def   ->(candidate :T ):Boolean= (s :\  true) (_ -> candidate  &&  _ -> candidate)

}
class ROrSpecification[T]( s: TSpecification[T]* ) extends ASpecification[T]{
  override def   ->(candidate :T ):Boolean= (s :\ false) (_ -> candidate  ||  _ -> candidate)
}