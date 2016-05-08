package com.example.compression

import org.scalatest.junit.JUnitRunner
import org.scalatest._
import org.junit.runner.RunWith

/**
 * @author fidato
 */

@RunWith(classOf[JUnitRunner])
class TestCompressor extends FlatSpec {

  "compress" should "return the Seq[Compressed]" in {

    //1. create the sequence 
    val seqString: Seq[String] = Seq[String]("true", "false", "true", "true", "false", "false")

    //2. expected seq
    val expectedSeq: Seq[Compressed[String]] = Seq[Compressed[String]](Single[String]("true"), Single[String]("false"), Repeat[String](2, "true"), Repeat[String](2, "false"))

    assert(expectedSeq === (new CompressorApply).compress[String](seqString))

  }

  "decompress" should "return the Seq[A]" in {

    //2. expected seq
    val compressedSeq: Seq[Compressed[String]] = Seq[Compressed[String]](Single[String]("true"), Single[String]("false"), Repeat[String](2, "true"), Repeat[String](2, "false"))

    //1. create the sequence 
    val expectedSeq: Seq[String] = Seq[String]("true", "false", "true", "true", "false", "false")

    assert(expectedSeq === (new CompressorApply).decompress[String](compressedSeq))

  }

  "Single toString" should "return the String" in {

    val singleIns: Single[String] = Single[String]("First")

    val expectedString: String = "First\n"

    assert(expectedString === singleIns.toString)

  }

  "Repeat toString" should "return the String" in {

    val repeatIns: Repeat[String] = Repeat[String](2, "First")

    val expectedString: String = "First2\n"

    assert(expectedString === repeatIns.toString)

  }

}