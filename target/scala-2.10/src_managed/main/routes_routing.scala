// @SOURCE:/Users/redarqas/projects/learning/LearnYouPlay/conf/routes
// @HASH:774bc5865f689cfbe3f13b411401988c81d01b04
// @DATE:Thu Jun 13 21:50:32 CEST 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:8
private[this] lazy val controllers_ch01_Exercises_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_ch01_Exercises_echoVal1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("echoVal"))))
        

// @LINE:10
private[this] lazy val controllers_ch01_Exercises_echoBlock2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("echoBlock"))))
        

// @LINE:11
private[this] lazy val controllers_ch01_Exercises_echoRequest3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("echoRequest"))))
        

// @LINE:12
private[this] lazy val controllers_ch01_Exercises_echoImplicitRequest4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("echoImplicitRequest"))))
        

// @LINE:13
private[this] lazy val controllers_ch01_Exercises_echoWithBodyParser5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("echoWithBodyParser"))))
        

// @LINE:14
private[this] lazy val controllers_ch01_Exercises_simpleOkResult6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("simpleOkResult"))))
        

// @LINE:15
private[this] lazy val controllers_ch01_Exercises_ok7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ok"))))
        

// @LINE:16
private[this] lazy val controllers_ch01_Exercises_other8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("other"))))
        

// @LINE:17
private[this] lazy val controllers_ch01_Exercises_redirectMe9 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("redirectIt"))))
        

// @LINE:18
private[this] lazy val controllers_ch01_Exercises_myTODO10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("myTODO"))))
        

// @LINE:19
private[this] lazy val controllers_ch01_Exercises_withDefault11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("withDefault"))))
        

// @LINE:20
private[this] lazy val controllers_ch01_Exercises_optionalParam12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("optionalParam"))))
        

// @LINE:23
private[this] lazy val controllers_ch01_Exercises_echoProvidedName13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sayHello/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:24
private[this] lazy val controllers_ch01_Exercises_redirectToAction14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("resayHello/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:25
private[this] lazy val controllers_ch01_Exercises_reverseEchoProvidedName15 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sayReverseHello/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:27
private[this] lazy val controllers_ch01_Exercises_spanIt16 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("span/"),DynamicPart("theSpan", """.+""",false))))
        

// @LINE:29
private[this] lazy val controllers_ch01_Exercises_limitZeroOne17 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("limit/"),DynamicPart("id", """[0-1]""",false))))
        

// @LINE:31
private[this] lazy val controllers_Assets_at18 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:37
private[this] lazy val controllers_ch03_Exercises_charsetIso_8859_119 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch03/charsetIso_8859_1"))))
        

// @LINE:38
private[this] lazy val controllers_ch03_Exercises_hello20 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch03/hello"))))
        

// @LINE:39
private[this] lazy val controllers_ch03_Exercises_helloAddCookie21 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch03/helloAddCookie"))))
        

// @LINE:40
private[this] lazy val controllers_ch03_Exercises_helloDiscardCookie22 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch03/helloDiscardCookie"))))
        

// @LINE:46
private[this] lazy val controllers_ch04_Exercises_login23 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch04/login"))))
        

// @LINE:47
private[this] lazy val controllers_ch04_Exercises_welecome24 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch04/welecome"))))
        

// @LINE:48
private[this] lazy val controllers_ch04_Exercises_logout25 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch04/logout"))))
        

// @LINE:49
private[this] lazy val controllers_ch04_Exercises_save26 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch04/save"))))
        

// @LINE:50
private[this] lazy val controllers_ch04_Exercises_home27 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch04/home"))))
        

// @LINE:51
private[this] lazy val controllers_ch04_Exercises_lostFlash28 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch04/lostFlash"))))
        

// @LINE:57
private[this] lazy val controllers_ch05_Exercises_save29 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch05/save"))))
        

// @LINE:58
private[this] lazy val controllers_ch05_Exercises_saveText30 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch05/saveText"))))
        

// @LINE:59
private[this] lazy val controllers_ch05_Exercises_limitedText31 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch05/limitedText"))))
        

// @LINE:60
private[this] lazy val controllers_ch05_Exercises_saveUpload32 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch05/saveUpload"))))
        

// @LINE:61
private[this] lazy val controllers_ch05_Exercises_saveUploads33 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch05/saveUploads"))))
        

// @LINE:62
private[this] lazy val controllers_ch05_Exercises_saveLimitUploads34 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch05/saveLimitUploads"))))
        

// @LINE:63
private[this] lazy val controllers_ch05_Exercises_login35 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch05/login"))))
        

// @LINE:69
private[this] lazy val controllers_ch06_Exercises_index36 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch06/index"))))
        

// @LINE:70
private[this] lazy val controllers_ch06_Exercises_hello37 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch06/hello"))))
        

// @LINE:71
private[this] lazy val controllers_ch06_Exercises_user38 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch06/user"))))
        

// @LINE:72
private[this] lazy val controllers_ch06_Exercises_needAuthenticateUser39 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch06/needAuthenticateUser"))))
        

// @LINE:73
private[this] lazy val controllers_ch06_Exercises_login40 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch06/login"))))
        

// @LINE:74
private[this] lazy val controllers_ch06_Exercises_welcome41 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch06/welcome"))))
        

// @LINE:75
private[this] lazy val controllers_ch06_Exercises_youCan42 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ch06/youCan"))))
        
def documentation = List(("""GET""", prefix,"""controllers.ch01.Exercises.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """echoVal""","""controllers.ch01.Exercises.echoVal"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """echoBlock""","""controllers.ch01.Exercises.echoBlock"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """echoRequest""","""controllers.ch01.Exercises.echoRequest"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """echoImplicitRequest""","""controllers.ch01.Exercises.echoImplicitRequest"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """echoWithBodyParser""","""controllers.ch01.Exercises.echoWithBodyParser"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """simpleOkResult""","""controllers.ch01.Exercises.simpleOkResult"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ok""","""controllers.ch01.Exercises.ok"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """other""","""controllers.ch01.Exercises.other"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """redirectIt""","""controllers.ch01.Exercises.redirectMe"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """myTODO""","""controllers.ch01.Exercises.myTODO"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """withDefault""","""controllers.ch01.Exercises.withDefault(id:Long ?= 1)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """optionalParam""","""controllers.ch01.Exercises.optionalParam(version:Option[String])"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sayHello/$name<[^/]+>""","""controllers.ch01.Exercises.echoProvidedName(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """resayHello/$name<[^/]+>""","""controllers.ch01.Exercises.redirectToAction(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sayReverseHello/$name<[^/]+>""","""controllers.ch01.Exercises.reverseEchoProvidedName(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """span/$theSpan<.+>""","""controllers.ch01.Exercises.spanIt(theSpan:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """limit/$id<[0-1]>""","""controllers.ch01.Exercises.limitZeroOne(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch03/charsetIso_8859_1""","""controllers.ch03.Exercises.charsetIso_8859_1()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch03/hello""","""controllers.ch03.Exercises.hello()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch03/helloAddCookie""","""controllers.ch03.Exercises.helloAddCookie()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch03/helloDiscardCookie""","""controllers.ch03.Exercises.helloDiscardCookie()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch04/login""","""controllers.ch04.Exercises.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch04/welecome""","""controllers.ch04.Exercises.welecome()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch04/logout""","""controllers.ch04.Exercises.logout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch04/save""","""controllers.ch04.Exercises.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch04/home""","""controllers.ch04.Exercises.home()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch04/lostFlash""","""controllers.ch04.Exercises.lostFlash()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch05/save""","""controllers.ch05.Exercises.save()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch05/saveText""","""controllers.ch05.Exercises.saveText()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch05/limitedText""","""controllers.ch05.Exercises.limitedText()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch05/saveUpload""","""controllers.ch05.Exercises.saveUpload()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch05/saveUploads""","""controllers.ch05.Exercises.saveUploads()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch05/saveLimitUploads""","""controllers.ch05.Exercises.saveLimitUploads()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch05/login""","""controllers.ch05.Exercises.login"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch06/index""","""controllers.ch06.Exercises.index()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch06/hello""","""controllers.ch06.Exercises.hello()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch06/user""","""controllers.ch06.Exercises.user()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch06/needAuthenticateUser""","""controllers.ch06.Exercises.needAuthenticateUser()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch06/login""","""controllers.ch06.Exercises.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch06/welcome""","""controllers.ch06.Exercises.welcome()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ch06/youCan""","""controllers.ch06.Exercises.youCan()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:8
case controllers_ch01_Exercises_index0(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.index, HandlerDef(this, "controllers.ch01.Exercises", "index", Nil,"GET", """ Routes
 This file defines all ch01.Exercises routes (Higher priority routes first)
 ~~~~
------------------
Chapter 01
------------------
 Static path""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_ch01_Exercises_echoVal1(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.echoVal, HandlerDef(this, "controllers.ch01.Exercises", "echoVal", Nil,"GET", """""", Routes.prefix + """echoVal"""))
   }
}
        

// @LINE:10
case controllers_ch01_Exercises_echoBlock2(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.echoBlock, HandlerDef(this, "controllers.ch01.Exercises", "echoBlock", Nil,"GET", """""", Routes.prefix + """echoBlock"""))
   }
}
        

// @LINE:11
case controllers_ch01_Exercises_echoRequest3(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.echoRequest, HandlerDef(this, "controllers.ch01.Exercises", "echoRequest", Nil,"GET", """""", Routes.prefix + """echoRequest"""))
   }
}
        

// @LINE:12
case controllers_ch01_Exercises_echoImplicitRequest4(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.echoImplicitRequest, HandlerDef(this, "controllers.ch01.Exercises", "echoImplicitRequest", Nil,"GET", """""", Routes.prefix + """echoImplicitRequest"""))
   }
}
        

// @LINE:13
case controllers_ch01_Exercises_echoWithBodyParser5(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.echoWithBodyParser, HandlerDef(this, "controllers.ch01.Exercises", "echoWithBodyParser", Nil,"GET", """""", Routes.prefix + """echoWithBodyParser"""))
   }
}
        

// @LINE:14
case controllers_ch01_Exercises_simpleOkResult6(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.simpleOkResult, HandlerDef(this, "controllers.ch01.Exercises", "simpleOkResult", Nil,"GET", """""", Routes.prefix + """simpleOkResult"""))
   }
}
        

// @LINE:15
case controllers_ch01_Exercises_ok7(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.ok, HandlerDef(this, "controllers.ch01.Exercises", "ok", Nil,"GET", """""", Routes.prefix + """ok"""))
   }
}
        

// @LINE:16
case controllers_ch01_Exercises_other8(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.other, HandlerDef(this, "controllers.ch01.Exercises", "other", Nil,"GET", """""", Routes.prefix + """other"""))
   }
}
        

// @LINE:17
case controllers_ch01_Exercises_redirectMe9(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.redirectMe, HandlerDef(this, "controllers.ch01.Exercises", "redirectMe", Nil,"GET", """""", Routes.prefix + """redirectIt"""))
   }
}
        

// @LINE:18
case controllers_ch01_Exercises_myTODO10(params) => {
   call { 
        invokeHandler(controllers.ch01.Exercises.myTODO, HandlerDef(this, "controllers.ch01.Exercises", "myTODO", Nil,"GET", """""", Routes.prefix + """myTODO"""))
   }
}
        

// @LINE:19
case controllers_ch01_Exercises_withDefault11(params) => {
   call(params.fromQuery[Long]("id", Some(1))) { (id) =>
        invokeHandler(controllers.ch01.Exercises.withDefault(id), HandlerDef(this, "controllers.ch01.Exercises", "withDefault", Seq(classOf[Long]),"GET", """""", Routes.prefix + """withDefault"""))
   }
}
        

// @LINE:20
case controllers_ch01_Exercises_optionalParam12(params) => {
   call(params.fromQuery[Option[String]]("version", None)) { (version) =>
        invokeHandler(controllers.ch01.Exercises.optionalParam(version), HandlerDef(this, "controllers.ch01.Exercises", "optionalParam", Seq(classOf[Option[String]]),"GET", """""", Routes.prefix + """optionalParam"""))
   }
}
        

// @LINE:23
case controllers_ch01_Exercises_echoProvidedName13(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.ch01.Exercises.echoProvidedName(name), HandlerDef(this, "controllers.ch01.Exercises", "echoProvidedName", Seq(classOf[String]),"GET", """ Dynamic part """, Routes.prefix + """sayHello/$name<[^/]+>"""))
   }
}
        

// @LINE:24
case controllers_ch01_Exercises_redirectToAction14(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.ch01.Exercises.redirectToAction(name), HandlerDef(this, "controllers.ch01.Exercises", "redirectToAction", Seq(classOf[String]),"GET", """""", Routes.prefix + """resayHello/$name<[^/]+>"""))
   }
}
        

// @LINE:25
case controllers_ch01_Exercises_reverseEchoProvidedName15(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.ch01.Exercises.reverseEchoProvidedName(name), HandlerDef(this, "controllers.ch01.Exercises", "reverseEchoProvidedName", Seq(classOf[String]),"GET", """""", Routes.prefix + """sayReverseHello/$name<[^/]+>"""))
   }
}
        

// @LINE:27
case controllers_ch01_Exercises_spanIt16(params) => {
   call(params.fromPath[String]("theSpan", None)) { (theSpan) =>
        invokeHandler(controllers.ch01.Exercises.spanIt(theSpan), HandlerDef(this, "controllers.ch01.Exercises", "spanIt", Seq(classOf[String]),"GET", """ Span several /""", Routes.prefix + """span/$theSpan<.+>"""))
   }
}
        

// @LINE:29
case controllers_ch01_Exercises_limitZeroOne17(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ch01.Exercises.limitZeroOne(id), HandlerDef(this, "controllers.ch01.Exercises", "limitZeroOne", Seq(classOf[Long]),"GET", """ define a limit /""", Routes.prefix + """limit/$id<[0-1]>"""))
   }
}
        

// @LINE:31
case controllers_Assets_at18(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:37
case controllers_ch03_Exercises_charsetIso_8859_119(params) => {
   call { 
        invokeHandler(controllers.ch03.Exercises.charsetIso_8859_1(), HandlerDef(this, "controllers.ch03.Exercises", "charsetIso_8859_1", Nil,"GET", """""", Routes.prefix + """ch03/charsetIso_8859_1"""))
   }
}
        

// @LINE:38
case controllers_ch03_Exercises_hello20(params) => {
   call { 
        invokeHandler(controllers.ch03.Exercises.hello(), HandlerDef(this, "controllers.ch03.Exercises", "hello", Nil,"GET", """""", Routes.prefix + """ch03/hello"""))
   }
}
        

// @LINE:39
case controllers_ch03_Exercises_helloAddCookie21(params) => {
   call { 
        invokeHandler(controllers.ch03.Exercises.helloAddCookie(), HandlerDef(this, "controllers.ch03.Exercises", "helloAddCookie", Nil,"GET", """""", Routes.prefix + """ch03/helloAddCookie"""))
   }
}
        

// @LINE:40
case controllers_ch03_Exercises_helloDiscardCookie22(params) => {
   call { 
        invokeHandler(controllers.ch03.Exercises.helloDiscardCookie(), HandlerDef(this, "controllers.ch03.Exercises", "helloDiscardCookie", Nil,"GET", """""", Routes.prefix + """ch03/helloDiscardCookie"""))
   }
}
        

// @LINE:46
case controllers_ch04_Exercises_login23(params) => {
   call { 
        invokeHandler(controllers.ch04.Exercises.login(), HandlerDef(this, "controllers.ch04.Exercises", "login", Nil,"GET", """""", Routes.prefix + """ch04/login"""))
   }
}
        

// @LINE:47
case controllers_ch04_Exercises_welecome24(params) => {
   call { 
        invokeHandler(controllers.ch04.Exercises.welecome(), HandlerDef(this, "controllers.ch04.Exercises", "welecome", Nil,"GET", """""", Routes.prefix + """ch04/welecome"""))
   }
}
        

// @LINE:48
case controllers_ch04_Exercises_logout25(params) => {
   call { 
        invokeHandler(controllers.ch04.Exercises.logout(), HandlerDef(this, "controllers.ch04.Exercises", "logout", Nil,"GET", """""", Routes.prefix + """ch04/logout"""))
   }
}
        

// @LINE:49
case controllers_ch04_Exercises_save26(params) => {
   call { 
        invokeHandler(controllers.ch04.Exercises.save(), HandlerDef(this, "controllers.ch04.Exercises", "save", Nil,"GET", """""", Routes.prefix + """ch04/save"""))
   }
}
        

// @LINE:50
case controllers_ch04_Exercises_home27(params) => {
   call { 
        invokeHandler(controllers.ch04.Exercises.home(), HandlerDef(this, "controllers.ch04.Exercises", "home", Nil,"GET", """""", Routes.prefix + """ch04/home"""))
   }
}
        

// @LINE:51
case controllers_ch04_Exercises_lostFlash28(params) => {
   call { 
        invokeHandler(controllers.ch04.Exercises.lostFlash(), HandlerDef(this, "controllers.ch04.Exercises", "lostFlash", Nil,"GET", """""", Routes.prefix + """ch04/lostFlash"""))
   }
}
        

// @LINE:57
case controllers_ch05_Exercises_save29(params) => {
   call { 
        invokeHandler(controllers.ch05.Exercises.save(), HandlerDef(this, "controllers.ch05.Exercises", "save", Nil,"PUT", """""", Routes.prefix + """ch05/save"""))
   }
}
        

// @LINE:58
case controllers_ch05_Exercises_saveText30(params) => {
   call { 
        invokeHandler(controllers.ch05.Exercises.saveText(), HandlerDef(this, "controllers.ch05.Exercises", "saveText", Nil,"POST", """""", Routes.prefix + """ch05/saveText"""))
   }
}
        

// @LINE:59
case controllers_ch05_Exercises_limitedText31(params) => {
   call { 
        invokeHandler(controllers.ch05.Exercises.limitedText(), HandlerDef(this, "controllers.ch05.Exercises", "limitedText", Nil,"POST", """""", Routes.prefix + """ch05/limitedText"""))
   }
}
        

// @LINE:60
case controllers_ch05_Exercises_saveUpload32(params) => {
   call { 
        invokeHandler(controllers.ch05.Exercises.saveUpload(), HandlerDef(this, "controllers.ch05.Exercises", "saveUpload", Nil,"POST", """""", Routes.prefix + """ch05/saveUpload"""))
   }
}
        

// @LINE:61
case controllers_ch05_Exercises_saveUploads33(params) => {
   call { 
        invokeHandler(controllers.ch05.Exercises.saveUploads(), HandlerDef(this, "controllers.ch05.Exercises", "saveUploads", Nil,"POST", """""", Routes.prefix + """ch05/saveUploads"""))
   }
}
        

// @LINE:62
case controllers_ch05_Exercises_saveLimitUploads34(params) => {
   call { 
        invokeHandler(controllers.ch05.Exercises.saveLimitUploads(), HandlerDef(this, "controllers.ch05.Exercises", "saveLimitUploads", Nil,"POST", """""", Routes.prefix + """ch05/saveLimitUploads"""))
   }
}
        

// @LINE:63
case controllers_ch05_Exercises_login35(params) => {
   call { 
        invokeHandler(controllers.ch05.Exercises.login, HandlerDef(this, "controllers.ch05.Exercises", "login", Nil,"GET", """""", Routes.prefix + """ch05/login"""))
   }
}
        

// @LINE:69
case controllers_ch06_Exercises_index36(params) => {
   call { 
        invokeHandler(controllers.ch06.Exercises.index(), HandlerDef(this, "controllers.ch06.Exercises", "index", Nil,"POST", """""", Routes.prefix + """ch06/index"""))
   }
}
        

// @LINE:70
case controllers_ch06_Exercises_hello37(params) => {
   call { 
        invokeHandler(controllers.ch06.Exercises.hello(), HandlerDef(this, "controllers.ch06.Exercises", "hello", Nil,"POST", """""", Routes.prefix + """ch06/hello"""))
   }
}
        

// @LINE:71
case controllers_ch06_Exercises_user38(params) => {
   call { 
        invokeHandler(controllers.ch06.Exercises.user(), HandlerDef(this, "controllers.ch06.Exercises", "user", Nil,"POST", """""", Routes.prefix + """ch06/user"""))
   }
}
        

// @LINE:72
case controllers_ch06_Exercises_needAuthenticateUser39(params) => {
   call { 
        invokeHandler(controllers.ch06.Exercises.needAuthenticateUser(), HandlerDef(this, "controllers.ch06.Exercises", "needAuthenticateUser", Nil,"GET", """""", Routes.prefix + """ch06/needAuthenticateUser"""))
   }
}
        

// @LINE:73
case controllers_ch06_Exercises_login40(params) => {
   call { 
        invokeHandler(controllers.ch06.Exercises.login(), HandlerDef(this, "controllers.ch06.Exercises", "login", Nil,"GET", """""", Routes.prefix + """ch06/login"""))
   }
}
        

// @LINE:74
case controllers_ch06_Exercises_welcome41(params) => {
   call { 
        invokeHandler(controllers.ch06.Exercises.welcome(), HandlerDef(this, "controllers.ch06.Exercises", "welcome", Nil,"GET", """""", Routes.prefix + """ch06/welcome"""))
   }
}
        

// @LINE:75
case controllers_ch06_Exercises_youCan42(params) => {
   call { 
        invokeHandler(controllers.ch06.Exercises.youCan(), HandlerDef(this, "controllers.ch06.Exercises", "youCan", Nil,"GET", """""", Routes.prefix + """ch06/youCan"""))
   }
}
        
}
    
}
        