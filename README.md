Spde Examples
=============

Hi! You've found some sample applications for [Spde], a Scala toolkit for [Processing].

Spde is a deconstructed toolkit, rather the opposite of an IDE like Eclipse or the Processing Development Environment. Instead of installing an environment to work in, you can download / fork these or any other Spde projects and the environment will be built around them. Huh? Just try it. All you need to have installed is [simple-build-tool], which is really worth having if you want to do anything in Scala!

    sbt             # downloads and compiles spde-sbt plugin, enters sbt console
    update          # downloads Spde and Processing dependencies
    project Explode # enters the "Explode" sub-project
    run             # compile and launch the sketch!

PLEASE improve these sketches and make your own, ideally something that shows off both Scala and Processing at the same time but the main rule is to *be cool*.

Also: because Processing OpenGL uses [Jogl] with native libraries, it can be tricky to get working across all platforms. Would you mind trying the Matrix example? And, **if it doesn't work** could you inform [me] your `os.name` and `os.arch` system properties? You can get these easily inside sbt:

    get os.name
    get os.arch

Thanks!

[Spde]:http://technically.us/spde
[Processing]:http://processing.org/
[simple-build-tool]:http://code.google.com/p/simple-build-tool/
[Jogl]:https://jogl.dev.java.net/
[me]:http://github.com/n8han