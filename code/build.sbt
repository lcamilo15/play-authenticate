organization := "com.feth"

name := "play-authenticate"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.11.6")

libraryDependencies ++= Seq(
  "org.apache.httpcomponents" % "httpclient" % "4.5",
  "com.feth" %% "play-easymail" % "0.7.0",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.mitre" % "openid-connect-client" % "1.1.19",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.springframework.security.oauth" % "spring-security-oauth2" % "2.0.0.RELEASE",
  cache,
  javaWs
)

// add resolver for easymail snapshots
resolvers += Resolver.sonatypeRepo("snapshots")

lazy val playAuthenticate = (project in file(".")).enablePlugins(PlayJava)
