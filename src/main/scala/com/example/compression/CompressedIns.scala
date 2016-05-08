package com.example.compression

import akka.event.Logging
import akka.actor.ActorSystem

/**
 * A trait for the Compressed Seq
 *
 * @author Tarun
 *
 */
trait CompressedSeq[+A] extends Seq[A] {
  def underlying: Seq[Compressed[A]]
}

/**
 * A class for representing the compressed Seq
 *
 * @author Tarun
 * @constructor Create a new CompressedSeq object with a 'seq'.
 */
class CompressedIns[A](seqA: Seq[A]) extends CompressedSeq[A] {

  /**
   * @return Returns the iterator on the Seq[A]
   */
  def iterator: Iterator[A] = seqA.iterator

  /**
   * @return Returns the element in the seq on the specified index/idx
   */
  def apply(idx: Int): A = {
    if (idx < 0 || idx >= length) throw new IndexOutOfBoundsException
    seqA(idx)
  }

  /**
   * @return Returns the length of the Seq[A]
   */
  def length: Int = seqA.size

  /**
   * @return Returns the underlying Seq of compressed[A]
   */
  def underlying: Seq[Compressed[A]] = (new CompressorApply).compress[A](seqA)
}

object CompressedIns { // remove extends App

  /**
   * @return Returns the formatted response on the Seq[Compressed[A]]
   */
  def formatResponse[A](seqComp: Seq[Compressed[A]]): String = seqComp.foldLeft[String]("")(_ + _.toString)

  // represents the current object in-memory
  var currentIns: CompressedSeq[String] = new CompressedIns[String](Seq[String](""))

  /**
   * @return Returns the instance of CompressedSeq[String]
   */
  def apply(seq: String): CompressedSeq[String] = {

    val newCompressIns: CompressedSeq[String] = new CompressedIns[String](seq.split("\n").toSeq)
    currentIns = newCompressIns
    newCompressIns
  }

}