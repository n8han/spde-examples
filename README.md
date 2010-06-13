Spde Examples
=============

Hi! You've found some sample applications for [Spde][1], a Scala toolkit for [Processing][2].

Spde is a deconstructed toolkit, rather the opposite of an IDE like Eclipse or the Processing Development Environment. Instead of installing an environment to work in, you can download / fork these or any other Spde projects and the environment will be built around them. Huh? Just try it. All you need to have installed is [simple-build-tool][3] (which is really worth having if you want to do anything in Scala). Then, inside the `spde-examples` directory:

    $ sbt             # downloads and compiles spde-sbt plugin, enters sbt console
    > update          # downloads Spde and Processing dependencies
    > project Explode # enters the "Explode" sub-project
    > run             # compile and launch the sketch!

PLEASE improve these sketches and make your own, ideally something that shows off both Scala and Processing at the same time but the main rule is to *be cool*. To start a new sketch that isn't going to be a spde-example, see the [source-only Graft][sog].

[sog]: http://technically.us/spde/Download#source-only

Also: because Processing OpenGL uses [Jogl][4] with native libraries, it can be tricky to get working across all platforms. Would you mind trying the Matrix example? And, **if it doesn't work** could you inform [me][5] your `os.name` and `os.arch` system properties? You can get these easily inside sbt:

    > get os.name
    > get os.arch

Thanks!

[1]:http://technically.us/spde
[2]:http://processing.org/
[3]:http://code.google.com/p/simple-build-tool/
[4]:https://jogl.dev.java.net/
[5]:http://github.com/n8han