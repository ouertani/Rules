

package me.jtunisie

 package object specifications {

  class MyBool(b:Boolean){
     def   ->(candidate :Any ):Boolean = b
  }
  implicit def toMyBool(b:Boolean)= new MyBool(b)
 }