package com.example.compression

/**
 * A trait for the Compressed[A]
 *
 * @author Tarun
 *
 */
sealed trait Compressed[+A]{
  def toString: String
}

/**
 * A class for representing the Single instance of data 
 *
 * @author Tarun
 * @constructor Create a new Single object with element.
 */
case class Single[A](element: A) extends Compressed[A] {
  override def toString: String = element.toString + "\n"
}

/**
 * A class for representing the Repeat instance of data 
 *
 * @author Tarun
 * @constructor Create a new Single object with count and element.
 */
case class Repeat[A](count: Int, element: A) extends Compressed[A] {
  override def toString: String = element.toString + count.toString + "\n"
}