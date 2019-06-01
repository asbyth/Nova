## Nova
Nova is a Minecraft 1.8.9 client with no real purpose as of yet

Nova is written purely in Kotlin 
(exceptions for Java can be made, but if it's possible to use Kotlin, then use it)

Nova uses the [ASMHelper](https://github.com/Sk1erLLC/FalseHonesty) library from [Sk1er LLC](https://sk1er.club)


### Installation
Currently installing Nova is not possible as we don't have an installer.
This will be worked on in the future whenever ready.


### Working with Nova
To properly setup Nova to be ran in the development environment, you need to append 

`-Dasmhelper.deobf=true` to your VM Arguments in Intellij.

Make sure there's a file in src/main/resources called `notch-mcp.srg` for mcp_stable22 mappings.
If there's not a file there, go to the README of ASMHelper and follow the instructions at the bottom of the page.