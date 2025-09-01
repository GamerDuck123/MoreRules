# More Gamerules!
Added 6 new gamerules all based around explosions doing damage to entities around them.
These new gamerules work alongside of the other gamerules that stop these sources from griefing

For example:
If you disable doesTntDamage and enable doesTntGrief, it will destroy blocks but not damage any entities nearby.

| GameRule                   | Fabric | Paper | NeoForge |
|----------------------------|--------|-------|----------|
| doesTntDamage              | X      | X     | X        |
| doesCrystalDamage          | X      | X     | X        |
| doesBedDamage              | X      | X     | X        |
| doesRespawnAnchorDamage    | X      | X     | X        |
| doesCreeperDamage          | X      | X     | X        |
| doesGhastDamage            | X      | X     | X        |

## Some minor bug fixes:
#### NeoForge
1. Ghast griefing was set improperly to listen to the TNT Griefing gamerule
2. Respawn anchor griefing was set improperly to listen to the TNT Griefing gamerule
3. End crystal griefing was set improperly to listen to the TNT Griefing gamerule

#### Paper
1. canZombiesBreakDoors didn't focus on zombies only so any entities that broke doors would be cancelled, this has been fixed
2. doesBedGrief didn't create a new explosion and only stopped the old one
3. doesRespawnAnchorGrief didn't create a new explosion and only stopped the old one