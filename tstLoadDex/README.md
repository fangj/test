
	- testjar: eclipse project
	- tstLoadAssetDex: IntelliJ IEDA project
	- jar2dex.bat: a script to trasform jar to dex jar

1. use eclipse export Test1.java as test_in.jar
2. use jar2dex.bat to generate test_out.jar (under \adt-bundle-windows-x86_64-20130522\sdk\build-tools\android-4.2.2)
3. put test_out.jar to tstLoadAssetDex's asset folder