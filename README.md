beamly-core.config
==================

This library is to wrap the [Typesafe config library](https://github.com/typesafehub/config).  Most of the Beamly services at the time of writing are Scala based and make extensive use of this and maintaining consistency with them is valuable.

```
(require [beamly-core.config :as cfg])

(def myconfig (cfg/load-config "my-config"))
```

The config is built up in the following way.

The default config ("reference.conf") is looked for in the class path, over this the provided configuration is overlayed, and upon that the system properties are overlayed.

In this manner default config may be overwritten, but system properties cannot.




