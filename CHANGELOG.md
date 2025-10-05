# v1.0.0 Now With Configuration!

This is the first major update of the entire project and helps to flesh out features and make the entire project more stable across all platforms!

## First off, Thank you!
Firstly before I continue with the changelog for this update, I want to say thank you all for your continued support on this project! I made this project 2 years ago and had planned on updating it a lot more back then but school and life caught up to me, it makes me extremely happy seeing it get support even after I abandoned it and came back! I hope that the other mods and plugins I am working on will have a similar path!

## New Config!
With this update there is a new config called morerules.properties, its quite a simple one but adds a feature that this project was missing

### What is its purpose?
The config's purpose is quite simple, it provides you, the user, a way to change where the files for your gamerules are located, the default path should be sufficient for most people, but this is huge as before I was storing all data hidden away in the world's folder and it made it quite hard for anyone to find! So now you can just find it in the default config and find your storage, match up the name/id of the file and bam, you can change any of the gamerules through the file instead of having to use the ingame command. 

### Default Config
For modded instances (NeoForge & Fabric)
```properties
storage-path=mods/MoreRules/storage
```

For bukkit based instances (Paper)
```properties
storage-path=plugins/MoreRules/storage
```

## Better Stability
Before this update the project was a bit unstable if looked at the wrong way and updates didn't always seem to work on all different platforms and I would constantly be pushing half updates just to fix them. So I've made some major changes to hopefully mitigate this in the future, however please let it be known I only officially support Paper, Fabric and NeoForge, any of their forks (LeafPaper, Quilt, etc.) may or may not work.

On top of fixing the compatibility with all the platforms, I have also increased the stability of the backend of the code, ensuring that new gamerules can be added quickly without compromising on their stability!

## The Future
For future updates I plan on adding tons of more gamerules as well as support for other platforms such as Sponge, Forge, Nukkit, etc.