logLevel := Level.Warn

resolvers += "JBoss" at "https://repository.jboss.org"

resolvers += Resolver.url("bintray-sbt-plugin", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)