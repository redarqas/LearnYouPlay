// @SOURCE:/Users/redarqas/projects/learning/LearnYouPlay/conf/routes
// @HASH:774bc5865f689cfbe3f13b411401988c81d01b04
// @DATE:Thu Jun 13 21:50:32 CEST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._
import java.net.URLEncoder

import play.api.mvc._


import Router.queryString


// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
package controllers.ch03 {

// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
class ReverseExercises {
    

// @LINE:37
def charsetIso_8859_1(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch03/charsetIso_8859_1")
}
                                                

// @LINE:39
def helloAddCookie(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch03/helloAddCookie")
}
                                                

// @LINE:40
def helloDiscardCookie(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch03/helloDiscardCookie")
}
                                                

// @LINE:38
def hello(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch03/hello")
}
                                                
    
}
                          
}
                  

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
package controllers.ch05 {

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
class ReverseExercises {
    

// @LINE:63
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch05/login")
}
                                                

// @LINE:58
def saveText(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch05/saveText")
}
                                                

// @LINE:57
def save(): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "ch05/save")
}
                                                

// @LINE:59
def limitedText(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch05/limitedText")
}
                                                

// @LINE:60
def saveUpload(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch05/saveUpload")
}
                                                

// @LINE:61
def saveUploads(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch05/saveUploads")
}
                                                

// @LINE:62
def saveLimitUploads(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch05/saveLimitUploads")
}
                                                
    
}
                          
}
                  

// @LINE:31
package controllers {

// @LINE:31
class ReverseAssets {
    

// @LINE:31
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  

// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
package controllers.ch01 {

// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseExercises {
    

// @LINE:20
def optionalParam(version:Option[String]): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "optionalParam" + queryString(List(Some(implicitly[QueryStringBindable[Option[String]]].unbind("version", version)))))
}
                                                

// @LINE:29
def limitZeroOne(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "limit/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:14
def simpleOkResult(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "simpleOkResult")
}
                                                

// @LINE:23
def echoProvidedName(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "sayHello/" + implicitly[PathBindable[String]].unbind("name", URLEncoder.encode(name, "utf-8")))
}
                                                

// @LINE:24
def redirectToAction(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "resayHello/" + implicitly[PathBindable[String]].unbind("name", URLEncoder.encode(name, "utf-8")))
}
                                                

// @LINE:25
def reverseEchoProvidedName(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "sayReverseHello/" + implicitly[PathBindable[String]].unbind("name", URLEncoder.encode(name, "utf-8")))
}
                                                

// @LINE:27
def spanIt(theSpan:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "span/" + implicitly[PathBindable[String]].unbind("theSpan", theSpan))
}
                                                

// @LINE:18
def myTODO(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "myTODO")
}
                                                

// @LINE:9
def echoVal(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "echoVal")
}
                                                

// @LINE:13
def echoWithBodyParser(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "echoWithBodyParser")
}
                                                

// @LINE:12
def echoImplicitRequest(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "echoImplicitRequest")
}
                                                

// @LINE:8
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:15
def ok(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ok")
}
                                                

// @LINE:16
def other(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "other")
}
                                                

// @LINE:11
def echoRequest(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "echoRequest")
}
                                                

// @LINE:19
def withDefault(id:Long = 1): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "withDefault" + queryString(List(if(id == 1) None else Some(implicitly[QueryStringBindable[Long]].unbind("id", id)))))
}
                                                

// @LINE:10
def echoBlock(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "echoBlock")
}
                                                

// @LINE:17
def redirectMe(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "redirectIt")
}
                                                
    
}
                          
}
                  

// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
package controllers.ch06 {

// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
class ReverseExercises {
    

// @LINE:73
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch06/login")
}
                                                

// @LINE:75
def youCan(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch06/youCan")
}
                                                

// @LINE:74
def welcome(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch06/welcome")
}
                                                

// @LINE:71
def user(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch06/user")
}
                                                

// @LINE:69
def index(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch06/index")
}
                                                

// @LINE:70
def hello(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ch06/hello")
}
                                                

// @LINE:72
def needAuthenticateUser(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch06/needAuthenticateUser")
}
                                                
    
}
                          
}
                  

// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
package controllers.ch04 {

// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
class ReverseExercises {
    

// @LINE:46
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch04/login")
}
                                                

// @LINE:48
def logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch04/logout")
}
                                                

// @LINE:47
def welecome(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch04/welecome")
}
                                                

// @LINE:49
def save(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch04/save")
}
                                                

// @LINE:51
def lostFlash(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch04/lostFlash")
}
                                                

// @LINE:50
def home(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ch04/home")
}
                                                
    
}
                          
}
                  


// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
package controllers.ch03.javascript {

// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
class ReverseExercises {
    

// @LINE:37
def charsetIso_8859_1 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch03.Exercises.charsetIso_8859_1",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch03/charsetIso_8859_1"})
      }
   """
)
                        

// @LINE:39
def helloAddCookie : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch03.Exercises.helloAddCookie",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch03/helloAddCookie"})
      }
   """
)
                        

// @LINE:40
def helloDiscardCookie : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch03.Exercises.helloDiscardCookie",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch03/helloDiscardCookie"})
      }
   """
)
                        

// @LINE:38
def hello : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch03.Exercises.hello",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch03/hello"})
      }
   """
)
                        
    
}
              
}
        

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
package controllers.ch05.javascript {

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
class ReverseExercises {
    

// @LINE:63
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch05.Exercises.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch05/login"})
      }
   """
)
                        

// @LINE:58
def saveText : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch05.Exercises.saveText",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch05/saveText"})
      }
   """
)
                        

// @LINE:57
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch05.Exercises.save",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "ch05/save"})
      }
   """
)
                        

// @LINE:59
def limitedText : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch05.Exercises.limitedText",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch05/limitedText"})
      }
   """
)
                        

// @LINE:60
def saveUpload : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch05.Exercises.saveUpload",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch05/saveUpload"})
      }
   """
)
                        

// @LINE:61
def saveUploads : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch05.Exercises.saveUploads",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch05/saveUploads"})
      }
   """
)
                        

// @LINE:62
def saveLimitUploads : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch05.Exercises.saveLimitUploads",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch05/saveLimitUploads"})
      }
   """
)
                        
    
}
              
}
        

// @LINE:31
package controllers.javascript {

// @LINE:31
class ReverseAssets {
    

// @LINE:31
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        

// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
package controllers.ch01.javascript {

// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseExercises {
    

// @LINE:20
def optionalParam : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.optionalParam",
   """
      function(version) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "optionalParam" + _qS([(""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("version", version)])})
      }
   """
)
                        

// @LINE:29
def limitZeroOne : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.limitZeroOne",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "limit/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:14
def simpleOkResult : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.simpleOkResult",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "simpleOkResult"})
      }
   """
)
                        

// @LINE:23
def echoProvidedName : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.echoProvidedName",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sayHello/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        

// @LINE:24
def redirectToAction : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.redirectToAction",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "resayHello/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        

// @LINE:25
def reverseEchoProvidedName : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.reverseEchoProvidedName",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sayReverseHello/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        

// @LINE:27
def spanIt : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.spanIt",
   """
      function(theSpan) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "span/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("theSpan", theSpan)})
      }
   """
)
                        

// @LINE:18
def myTODO : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.myTODO",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "myTODO"})
      }
   """
)
                        

// @LINE:9
def echoVal : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.echoVal",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "echoVal"})
      }
   """
)
                        

// @LINE:13
def echoWithBodyParser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.echoWithBodyParser",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "echoWithBodyParser"})
      }
   """
)
                        

// @LINE:12
def echoImplicitRequest : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.echoImplicitRequest",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "echoImplicitRequest"})
      }
   """
)
                        

// @LINE:8
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:15
def ok : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.ok",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ok"})
      }
   """
)
                        

// @LINE:16
def other : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.other",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "other"})
      }
   """
)
                        

// @LINE:11
def echoRequest : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.echoRequest",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "echoRequest"})
      }
   """
)
                        

// @LINE:19
def withDefault : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.withDefault",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "withDefault" + _qS([(id == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("id", id))])})
      }
   """
)
                        

// @LINE:10
def echoBlock : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.echoBlock",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "echoBlock"})
      }
   """
)
                        

// @LINE:17
def redirectMe : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch01.Exercises.redirectMe",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "redirectIt"})
      }
   """
)
                        
    
}
              
}
        

// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
package controllers.ch06.javascript {

// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
class ReverseExercises {
    

// @LINE:73
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch06.Exercises.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch06/login"})
      }
   """
)
                        

// @LINE:75
def youCan : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch06.Exercises.youCan",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch06/youCan"})
      }
   """
)
                        

// @LINE:74
def welcome : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch06.Exercises.welcome",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch06/welcome"})
      }
   """
)
                        

// @LINE:71
def user : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch06.Exercises.user",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch06/user"})
      }
   """
)
                        

// @LINE:69
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch06.Exercises.index",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch06/index"})
      }
   """
)
                        

// @LINE:70
def hello : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch06.Exercises.hello",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ch06/hello"})
      }
   """
)
                        

// @LINE:72
def needAuthenticateUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch06.Exercises.needAuthenticateUser",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch06/needAuthenticateUser"})
      }
   """
)
                        
    
}
              
}
        

// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
package controllers.ch04.javascript {

// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
class ReverseExercises {
    

// @LINE:46
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch04.Exercises.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch04/login"})
      }
   """
)
                        

// @LINE:48
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch04.Exercises.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch04/logout"})
      }
   """
)
                        

// @LINE:47
def welecome : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch04.Exercises.welecome",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch04/welecome"})
      }
   """
)
                        

// @LINE:49
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch04.Exercises.save",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch04/save"})
      }
   """
)
                        

// @LINE:51
def lostFlash : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch04.Exercises.lostFlash",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch04/lostFlash"})
      }
   """
)
                        

// @LINE:50
def home : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ch04.Exercises.home",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ch04/home"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
package controllers.ch03.ref {

// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
class ReverseExercises {
    

// @LINE:37
def charsetIso_8859_1(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch03.Exercises.charsetIso_8859_1(), HandlerDef(this, "controllers.ch03.Exercises", "charsetIso_8859_1", Seq(), "GET", """""", _prefix + """ch03/charsetIso_8859_1""")
)
                      

// @LINE:39
def helloAddCookie(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch03.Exercises.helloAddCookie(), HandlerDef(this, "controllers.ch03.Exercises", "helloAddCookie", Seq(), "GET", """""", _prefix + """ch03/helloAddCookie""")
)
                      

// @LINE:40
def helloDiscardCookie(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch03.Exercises.helloDiscardCookie(), HandlerDef(this, "controllers.ch03.Exercises", "helloDiscardCookie", Seq(), "GET", """""", _prefix + """ch03/helloDiscardCookie""")
)
                      

// @LINE:38
def hello(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch03.Exercises.hello(), HandlerDef(this, "controllers.ch03.Exercises", "hello", Seq(), "GET", """""", _prefix + """ch03/hello""")
)
                      
    
}
                          
}
                  

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
package controllers.ch05.ref {

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
class ReverseExercises {
    

// @LINE:63
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch05.Exercises.login(), HandlerDef(this, "controllers.ch05.Exercises", "login", Seq(), "GET", """""", _prefix + """ch05/login""")
)
                      

// @LINE:58
def saveText(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch05.Exercises.saveText(), HandlerDef(this, "controllers.ch05.Exercises", "saveText", Seq(), "POST", """""", _prefix + """ch05/saveText""")
)
                      

// @LINE:57
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch05.Exercises.save(), HandlerDef(this, "controllers.ch05.Exercises", "save", Seq(), "PUT", """""", _prefix + """ch05/save""")
)
                      

// @LINE:59
def limitedText(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch05.Exercises.limitedText(), HandlerDef(this, "controllers.ch05.Exercises", "limitedText", Seq(), "POST", """""", _prefix + """ch05/limitedText""")
)
                      

// @LINE:60
def saveUpload(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch05.Exercises.saveUpload(), HandlerDef(this, "controllers.ch05.Exercises", "saveUpload", Seq(), "POST", """""", _prefix + """ch05/saveUpload""")
)
                      

// @LINE:61
def saveUploads(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch05.Exercises.saveUploads(), HandlerDef(this, "controllers.ch05.Exercises", "saveUploads", Seq(), "POST", """""", _prefix + """ch05/saveUploads""")
)
                      

// @LINE:62
def saveLimitUploads(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch05.Exercises.saveLimitUploads(), HandlerDef(this, "controllers.ch05.Exercises", "saveLimitUploads", Seq(), "POST", """""", _prefix + """ch05/saveLimitUploads""")
)
                      
    
}
                          
}
                  

// @LINE:31
package controllers.ref {

// @LINE:31
class ReverseAssets {
    

// @LINE:31
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
                  

// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
package controllers.ch01.ref {

// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseExercises {
    

// @LINE:20
def optionalParam(version:Option[String]): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.optionalParam(version), HandlerDef(this, "controllers.ch01.Exercises", "optionalParam", Seq(classOf[Option[String]]), "GET", """""", _prefix + """optionalParam""")
)
                      

// @LINE:29
def limitZeroOne(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.limitZeroOne(id), HandlerDef(this, "controllers.ch01.Exercises", "limitZeroOne", Seq(classOf[Long]), "GET", """ define a limit /""", _prefix + """limit/$id<[0-1]>""")
)
                      

// @LINE:14
def simpleOkResult(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.simpleOkResult(), HandlerDef(this, "controllers.ch01.Exercises", "simpleOkResult", Seq(), "GET", """""", _prefix + """simpleOkResult""")
)
                      

// @LINE:23
def echoProvidedName(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.echoProvidedName(name), HandlerDef(this, "controllers.ch01.Exercises", "echoProvidedName", Seq(classOf[String]), "GET", """ Dynamic part """, _prefix + """sayHello/$name<[^/]+>""")
)
                      

// @LINE:24
def redirectToAction(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.redirectToAction(name), HandlerDef(this, "controllers.ch01.Exercises", "redirectToAction", Seq(classOf[String]), "GET", """""", _prefix + """resayHello/$name<[^/]+>""")
)
                      

// @LINE:25
def reverseEchoProvidedName(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.reverseEchoProvidedName(name), HandlerDef(this, "controllers.ch01.Exercises", "reverseEchoProvidedName", Seq(classOf[String]), "GET", """""", _prefix + """sayReverseHello/$name<[^/]+>""")
)
                      

// @LINE:27
def spanIt(theSpan:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.spanIt(theSpan), HandlerDef(this, "controllers.ch01.Exercises", "spanIt", Seq(classOf[String]), "GET", """ Span several /""", _prefix + """span/$theSpan<.+>""")
)
                      

// @LINE:18
def myTODO(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.myTODO(), HandlerDef(this, "controllers.ch01.Exercises", "myTODO", Seq(), "GET", """""", _prefix + """myTODO""")
)
                      

// @LINE:9
def echoVal(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.echoVal(), HandlerDef(this, "controllers.ch01.Exercises", "echoVal", Seq(), "GET", """""", _prefix + """echoVal""")
)
                      

// @LINE:13
def echoWithBodyParser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.echoWithBodyParser(), HandlerDef(this, "controllers.ch01.Exercises", "echoWithBodyParser", Seq(), "GET", """""", _prefix + """echoWithBodyParser""")
)
                      

// @LINE:12
def echoImplicitRequest(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.echoImplicitRequest(), HandlerDef(this, "controllers.ch01.Exercises", "echoImplicitRequest", Seq(), "GET", """""", _prefix + """echoImplicitRequest""")
)
                      

// @LINE:8
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.index(), HandlerDef(this, "controllers.ch01.Exercises", "index", Seq(), "GET", """ Routes
 This file defines all ch01.Exercises routes (Higher priority routes first)
 ~~~~
------------------
Chapter 01
------------------
 Static path""", _prefix + """""")
)
                      

// @LINE:15
def ok(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.ok(), HandlerDef(this, "controllers.ch01.Exercises", "ok", Seq(), "GET", """""", _prefix + """ok""")
)
                      

// @LINE:16
def other(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.other(), HandlerDef(this, "controllers.ch01.Exercises", "other", Seq(), "GET", """""", _prefix + """other""")
)
                      

// @LINE:11
def echoRequest(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.echoRequest(), HandlerDef(this, "controllers.ch01.Exercises", "echoRequest", Seq(), "GET", """""", _prefix + """echoRequest""")
)
                      

// @LINE:19
def withDefault(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.withDefault(id), HandlerDef(this, "controllers.ch01.Exercises", "withDefault", Seq(classOf[Long]), "GET", """""", _prefix + """withDefault""")
)
                      

// @LINE:10
def echoBlock(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.echoBlock(), HandlerDef(this, "controllers.ch01.Exercises", "echoBlock", Seq(), "GET", """""", _prefix + """echoBlock""")
)
                      

// @LINE:17
def redirectMe(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch01.Exercises.redirectMe(), HandlerDef(this, "controllers.ch01.Exercises", "redirectMe", Seq(), "GET", """""", _prefix + """redirectIt""")
)
                      
    
}
                          
}
                  

// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
package controllers.ch06.ref {

// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
class ReverseExercises {
    

// @LINE:73
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch06.Exercises.login(), HandlerDef(this, "controllers.ch06.Exercises", "login", Seq(), "GET", """""", _prefix + """ch06/login""")
)
                      

// @LINE:75
def youCan(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch06.Exercises.youCan(), HandlerDef(this, "controllers.ch06.Exercises", "youCan", Seq(), "GET", """""", _prefix + """ch06/youCan""")
)
                      

// @LINE:74
def welcome(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch06.Exercises.welcome(), HandlerDef(this, "controllers.ch06.Exercises", "welcome", Seq(), "GET", """""", _prefix + """ch06/welcome""")
)
                      

// @LINE:71
def user(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch06.Exercises.user(), HandlerDef(this, "controllers.ch06.Exercises", "user", Seq(), "POST", """""", _prefix + """ch06/user""")
)
                      

// @LINE:69
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch06.Exercises.index(), HandlerDef(this, "controllers.ch06.Exercises", "index", Seq(), "POST", """""", _prefix + """ch06/index""")
)
                      

// @LINE:70
def hello(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch06.Exercises.hello(), HandlerDef(this, "controllers.ch06.Exercises", "hello", Seq(), "POST", """""", _prefix + """ch06/hello""")
)
                      

// @LINE:72
def needAuthenticateUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch06.Exercises.needAuthenticateUser(), HandlerDef(this, "controllers.ch06.Exercises", "needAuthenticateUser", Seq(), "GET", """""", _prefix + """ch06/needAuthenticateUser""")
)
                      
    
}
                          
}
                  

// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
package controllers.ch04.ref {

// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
class ReverseExercises {
    

// @LINE:46
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch04.Exercises.login(), HandlerDef(this, "controllers.ch04.Exercises", "login", Seq(), "GET", """""", _prefix + """ch04/login""")
)
                      

// @LINE:48
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch04.Exercises.logout(), HandlerDef(this, "controllers.ch04.Exercises", "logout", Seq(), "GET", """""", _prefix + """ch04/logout""")
)
                      

// @LINE:47
def welecome(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch04.Exercises.welecome(), HandlerDef(this, "controllers.ch04.Exercises", "welecome", Seq(), "GET", """""", _prefix + """ch04/welecome""")
)
                      

// @LINE:49
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch04.Exercises.save(), HandlerDef(this, "controllers.ch04.Exercises", "save", Seq(), "GET", """""", _prefix + """ch04/save""")
)
                      

// @LINE:51
def lostFlash(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch04.Exercises.lostFlash(), HandlerDef(this, "controllers.ch04.Exercises", "lostFlash", Seq(), "GET", """""", _prefix + """ch04/lostFlash""")
)
                      

// @LINE:50
def home(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ch04.Exercises.home(), HandlerDef(this, "controllers.ch04.Exercises", "home", Seq(), "GET", """""", _prefix + """ch04/home""")
)
                      
    
}
                          
}
                  
      