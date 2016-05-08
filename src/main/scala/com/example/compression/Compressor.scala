package com.example.compression


/**
 * A trait for the Compressor
 *
 * @author Tarun
 *
 */
trait Compressor {
  def compress[A]: Seq[A] => Seq[Compressed[A]]
  def decompress[A]: Seq[Compressed[A]] => Seq[A]
}

/**
 * A class for representing the Instance of Compressor 
 *
 * @author Tarun
 * @constructor Create a new Single object with element.
 */
class CompressorApply extends Compressor {
  
  /**
   * @return Returns the Seq[Compressed[A]
   */
  def compress[A]: Seq[A] => Seq[Compressed[A]] = (seqType: Seq[A]) => {

    def innerSeq[A](seqInner: Seq[A]): Seq[Seq[A]] = {
      if (seqInner.isEmpty) Seq(Seq())
      else {
        val (collected, next) = seqInner span { _.equals(seqInner.head) } // this needs replacing for " and dependent on Object#equals() method to discover duplicates."
        if (next == Nil) Seq(collected)
        else collected +: innerSeq(next)
      }
    } 

    innerSeq(seqType) map { e =>
      e match {
        case x if x.length != 1 => Repeat[A](x.length, x.head)
        case x => Single[A](x.head)
      }

    }

  } 
  
  /**
   * @return Returns the Seq[A]
   */
  def decompress[A]: Seq[Compressed[A]] => Seq[A] = (seqCompressed: Seq[Compressed[A]]) => {
    seqCompressed flatMap { seqX => 
      seqX match {
        case x: Repeat[A] => Seq.fill(x.count)(x.element)
        case x: Single[A] => Seq(x.element)
      }  
    }
  }
  
  
}