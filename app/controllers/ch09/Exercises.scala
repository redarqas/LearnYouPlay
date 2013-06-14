package controllers.ch09

import play.api.libs.iteratee.Enumerator
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.ResponseHeader
import play.api.mvc.SimpleResult

//Since HTTP 1.1, to keep a single connection open to serve several HTTP requests and responses,
//the server must send the appropriate Content-Length HTTP header along with the response

object Exercises extends Controller {
  def index = Action {
    SimpleResult(
      header = ResponseHeader(200),
      //Play will cosnume the whole Enumerator 
      //loads its content into memory 
      //and compute assign its size to Content-Length 
      //The size is computed according to used charset
      //by default UTF-8
      body = Enumerator("Hello world"))
  }

  /** ---------------------------------- **/
  /**  Serving Static content            **/
  /** ---------------------------------- **/

  //Send large amount of data 
  def sendHeavyFile = Action {
    //Define an Enumarator for the file : Try a very big file
    val file = new java.io.File("README.md")
    //We will avoid to load the whole file into memory
    //by specifying Content-Length we want to serve
    //thus, play will serve chuncks to the client from Enumerator
    //untill it reaches specified Content-Length    
    val fileContent: Enumerator[Array[Byte]] = Enumerator.fromFile(file)
    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = fileContent)
  }

  //An example by using play helper to serve a file
  //sendFile wrapps an enumerator chunks serving
  //With client download
  def sendDownload = Action {
    Ok.sendFile(
      content = new java.io.File("README.md"),
      fileName = _ => "YourCopyOfReadme.md" //We put -> Content-Disposition: attachment; filename=YourCopeOfReadme.md
      )
  }
  //With client display
  def sendFileDisplay = Action {
    Ok.sendFile(
      content = new java.io.File("README.md"),
      inline = true //We put -> Content-Disposition: inline
      )
  }

  /** ------------------------------------ **/
  /**  Streaming :Serving dynamic contend  **/
  /** ------------------------------------ **/

  //Play uses Chunked transfer encoding. 
  //It uses the Transfer-Encoding
  //the Content-Length header is not used
  //the server does not need to know the length of the content 
  //before it starts transmitting a response to the client

  def stream = Action {
    //Fake stream : ["OH", "0H", "", ..], 
    Ok.stream(Enumerator(fakeStream[String](100, "OH\n"): _*).andThen(Enumerator.eof))
  }

  def fakeStream[A](i: Int, n: A): List[A] = i match {
    case 0 => Nil
    case i => n :: fakeStream((i - 1), n)
  }

}