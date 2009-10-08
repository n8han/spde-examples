Video Projects
==============

The original [Processing Video library][1] uses the Carbon-bound Quicktime for Java, which isn't compatible with 64-bit JVMs and [will likely never be][2]. To gain 64-bit and Linux compatibility, Spde defaults to the [GSVideo][3] library for Processing. GSVideo renders video with the open-source [GStreamer][4] library.

Most Linux installations have GStreamer already installed; Mac OS X users can install it through [MacPorts][5]. To play videos you'll need not only GStreamer, but codecs. This command will compile and install everything you need (it may take a while):

    sudo port install gst-ffmpeg

(Unfortunately, gst-ffmpeg [doesn't yet compile][6] on Mac OS 10.6. Probably it will soon.)

GSVideo requires an environment variable to find a MacPorts GStreamer installation:

    export GST_PLUGIN_PATH=/opt/local/lib/gstreamer-0.10

If that works, or you're on Linux, try running the Loop example in sbt:

    $ sbt
    > project Loop
    > update
    > run

If you see an error message "no suitable image found ... wrong architecture", it means the Java runtime you're using was complied for a different processor architecture than your gst-ffmpeg library. For a Mac with an Intel processor, you can cover your bases by adding the following line to `/opt/local/etc/macports/macports.conf`:

    universal_archs         x86_64 i386

Then, run this command to update gst-ffmpeg and everything it depends on to a universal binary, and wait:

    sudo port upgrade --enforce-variants gst-ffmpeg +universal

###This is too hard for Mac users!

Well, yes, and what are you going to do about it? Apple is not going to rebuild the QuickTime for Java bridge for the newer, 64-bit capable Cocoa QTKit framework. There is however [a third-party effort through Rococoa][7] to bridge Java to QTKit. No one has made a Processing interface to it yet, but if Andrés Colubri could clone Processing's Video API to use GStreamer for Linux support then some enterprising Mac-head can do the same to rescue QuickTime. (Consider the open-source gauntlet thrown.)

[1]:http://processing.org/reference/libraries/video/
[2]:http://lists.apple.com/archives/quicktime-java/2009/Jul/msg00004.html
[3]:http://users.design.ucla.edu/~acolubri/processing/gsvideo/home/
[4]:http://gstreamer.freedesktop.org/
[5]:http://www.macports.org/
[6]:http://trac.macports.org/ticket/21370
[7]:https://rococoa.dev.java.net/rococoa-quicktime.html