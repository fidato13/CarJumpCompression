package com.example.compression

import org.scalatest.junit.JUnitRunner
import org.scalatest._
import org.junit.runner.RunWith

/**
 * @author fidato
 */

@RunWith(classOf[JUnitRunner])
class TestCompressedIns extends FlatSpec {

  "underlying" should "return the Seq[Compressed]" in {

    //1. create the sequence 
    val string = "true" + '\n' +  "false" + '\n' +  "true" + '\n' +  "true" + '\n' +  "false" + '\n' +  "false"

    //2. expected seq
    val expectedSeq: Seq[Compressed[String]] = Seq[Compressed[String]](Single[String]("true"), Single[String]("false"), Repeat[String](2, "true"), Repeat[String](2, "false"))

    assert(expectedSeq === CompressedIns(string).underlying)

  }

  
  "formatResponse" should "return the String" in {

    //1. create the sequence 
    val string = "true" + '\n' +  "false" + '\n' +  "true2" + '\n'  +  "false2" + '\n'

    //2. expected seq
    val compressedSeq: Seq[Compressed[String]] = Seq[Compressed[String]](Single[String]("true"), Single[String]("false"), Repeat[String](2, "true"), Repeat[String](2, "false"))

    assert(string === CompressedIns.formatResponse(compressedSeq))

  }
  
   "apply" should "return the element" in {

    //1. create the sequence 
    val string = "true" + '\n' +  "false" + '\n' +  "true2" + '\n'  +  "false2" + '\n'

    
    assert(CompressedIns(string).apply(0) === "true")
    assert(CompressedIns(string).apply(1) === "false")

  }

  

}