#mac 上安装myeclipse2014 出现如下报错的解决方案
###Your system does not have sufficient memory to support MyEclipse. MyEclipse requires 256 MBs physical memory and 64 MBs of virtual memory. Your system only has 4056708 MBs of physical memory, and 0 MBs of virtual memory".
###这种报错的解决办法

Download the full installer
*   Open the DMG and then show the package content of the installer
*   Copy the content of the Show Package contents to a separate folder
*   Delete the CodeSignature folder
*   From SystemPrefences temporarly enable "Allow apps downloaded from Anywhere" (in your Security & Privacy settings)
*   Edit the file MacOS/standard-install and add the following to the vmargs: "-Dcom.genuitec.pulse.debug.memory.check=true"
```sh
#!/bin/sh
dir=`dirname "$0"`
cd "${dir}/../Installer"
dir=`pwd`
./pulse-one -vmargs -Dcom.genuitec.pulse.debug.memory.check=false -Xms192m -Xmx256m -XX:MaxPermSize=128m -d32 -XstartOnFirstThread -Dorg.eclipse.swt.internal.carbon.smallFonts "-Dpulse.settings.directory=${dir}/../Settings" "-Dpulse.artifact.bin.path=${dir}/../Resources/artifacts.bin" "-Dpulse.installer.directory=${dir}" &

```
6. Run standard-install


##注意了
有时候用
```sh
-Dcom.genuitec.pulse.debug.memory.check=true
```
并不会成功，可以使用false,我这里就是用false的。
于是成功了