Required infrastructure:

ActiveMQ 5.15.3
MongoDB 3.6.3


When running from Anypoint add -P Anypoint to run configuration to get ActiveMQ libraries on classpath.

When running from Mule Standalone add the ActiveMQ jars to lib/user:

cp ~/Development/apache-activemq/lib/activemq-client-5.15.3.jar lib/user/
cp ~/Development/apache-activemq/lib/geronimo-j2ee-management_1.1_spec-1.0.1.jar lib/user/
cp ~/Development/apache-activemq/lib/hawtbuf-1.11.jar lib/user/


Add wrapper.java.additional.17=-Dmule.env=standalone to conf/wrapper.conf
or run mule -M-Dmule.env=standalone. Add standalone.properties to conf with values overriding defaults.


Copy target/MusicLibraryMule-${version}.zip to $MULE_HOME/apps


MP3 files must be writable before copying to dropin folder.


Export:

http://localhost:8081/musiclibrarymule/export


Log:

http://localhost:8081/musiclibrarymule/log
http://localhost:8081/musiclibrarymule/clearlog
