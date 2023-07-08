Keyboard is a simple library for Fabric mods to do keyboard related things.

Yup. That's it.

If you were sent here that means you are using a mod that requires this library and they did not include it as a jar-in-jar dependency.

If that's the case, just download the version they specified (or the latest if they didn't) and install it to your mods folder.

No configs or anything for you to worry about.

https://modrinth.com/mod/keyboard

#### Developers

Javadoc: https://javadoc.pl3x.net/keyboard/

```groovy
repositories {
    maven { url = 'https://repo.pl3x.net/public/' }
}

dependencies {
    modImplementation include('net.pl3x.keyboard:keyboard:0.0.1')
}
```
