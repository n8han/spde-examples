object AppletTemplate {
  // Hey, maybe there is a way to get resources in a sbt plugin, but for now...
  val resource = 
"""
<html>

  <!--  IF and only if the web server is configured to serve jpgz files with 
        the correct content type and encoding, you can swap the 3 jar names
        below to their smaller jpgz equivalents. e.g., for Apache:
      
        AddEncoding pack200-gzip .jpgz
        AddType application/x-java-archive .jpgz -->

  <!--[if !IE]> -->
    <object classid="java:{{sketch}}.class" 
              type="application/x-java-applet"
              archive="{{archive}}"
              width="{{width}}" height="{{height}}"
              standby="Loading Processing software..." >
            
      <param name="archive" value="{{archive}}" />
  
      <param name="mayscript" value="true" />
      <param name="scriptable" value="true" />
  
      <param name="image" value="loading.gif" />
      <param name="boxmessage" value="Loading Processing software..." />
      <param name="boxbgcolor" value="#FFFFFF" />
  
      <param name="test_string" value="outer" />
  <!--<![endif]-->
  
    <object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
        codebase="http://java.sun.com/update/1.5.0/jinstall-1_5_0_15-windows-i586.cab"
        width="{{width}}" height="{{height}}"
        standby="Loading Processing software..."  >
      
      <param name="code" value="{{sketch}}" />
      <param name="archive" value="{{archive}}" />
    
      <param name="mayscript" value="true" />
      <param name="scriptable" value="true" />
    
      <param name="image" value="loading.gif" />
      <param name="boxmessage" value="Loading Processing software..." />
      <param name="boxbgcolor" value="#FFFFFF" />
    
      <param name="test_string" value="inner" />
    
      <p>
        <strong>
          This browser does not have a Java Plug-in.
          <br />
          <a href="http://java.sun.com/products/plugin/downloads/index.html" title="Download Java Plug-in">
            Get the latest Java Plug-in here.
          </a>
        </strong>
      </p>
  
    </object>
  
  <!--[if !IE]> -->
    </object>
  <!--<![endif]-->

</html>
"""
}