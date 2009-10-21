Video Projects
==============

The original [Processing Video library][1] uses Quicktime for Java, which isn't compatible with 64-bit JVMs and [may never be][2]. To gain 64-bit compatibility, Linux compatibility, and to avoid the possibility of future vendor abandonment, Spde defaults to the [GSVideo][3] library for Processing. GSVideo renders video with the open-source [GStreamer][4] library.

Most Linux installations have GStreamer already installed but they may need to install a GStreamer ffmpeg plugin. Mac OS X users can install these through [MacPorts][5]. This command will compile and install everything you need (it may take a while):

    sudo port install gst-ffmpeg

(Unfortunately, it [doesn't yet work][6] on Mac OS 10.6. Probably it will soon.)

GSVideo requires an environment variable to find a MacPorts GStreamer installation:

    export GST_PLUGIN_PATH=/opt/local/lib/gstreamer-0.10

If that works, or you're on Linux, try running the Loop example in sbt:

    $ sbt
    > project Loop
    > update
    > run

[1]:http://processing.org/reference/libraries/video/
[2]:http://lists.apple.com/archives/quicktime-java/2009/Jul/msg00004.html
[3]:http://users.design.ucla.edu/~acolubri/processing/gsvideo/home/
[4]:http://gstreamer.freedesktop.org/
[5]:http://www.macports.org/
[6]:http://trac.macports.org/ticket/21370