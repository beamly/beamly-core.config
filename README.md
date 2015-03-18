# beamly-core.config


## Reasoning

This library is to wrap the [Typesafe config library](https://github.com/typesafehub/config).  Most of the [Beamly] services at the time of writing are Scala based and make extensive use of this and maintaining consistency with them is valuable.


## Releases

* Releases are published to [Clojars]

* Latest stable release is [0.1.0]

[Leiningen] dependency information:

    [beamly/beamly-core.config "0.1.0"]

[Maven] dependency information:

    <dependency>
      <groupId>beamly</groupId>
      <artifactId>beamly-core.config</artifactId>
      <version>0.1.0</version>
    </dependency>

[Beamly]: https://beamly.com/
[Clojars]: http://clojars.org/
[Leiningen]: http://leiningen.org/
[Maven]: http://maven.apache.org/

## Usage

A simple example:

```clojure

(ns example.application
  (:require [beamly-core.config :as cfg]))

(defn get-my-config-as-a-map [filename]
  (cfg/load-config filename))

```

The config is built up in the following way.

The default config ("reference.conf") is looked for in the class path, over this the provided configuration is overlayed, and upon that the system properties are overlayed.

In this manner default config may be overwritten, but system properties cannot.


## Copyright and License

The MIT License (MIT)

Copyright © 2015 Beamly Ltd.

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


