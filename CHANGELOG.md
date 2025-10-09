# Full Compat with Paper and Spigot & Some more Gamerules

Before I move on to updating to 1.21.9 I wanted to make one final update to ensure that paper and spigot were also on the same level of polish as neoforge and fabric so I've added their missing gamerules:

O = Already added in a previous version

X = Added this version

| GameRule                | Paper | Spigot |
|-------------------------|-------|--------|
| doesRespawnAnchorDamage | O     | X      |
| doesBedDamage           | O     | X      |
| doesDragonGrief         | X     | X      |
| doesDragonDamage        | X     | X      |

# For everyone else:

I also wanted to add a few more simple gamerules to help flesh out the project a bit more:

| GameRule                   | Fabric | Paper | Spigot | NeoForge |
|----------------------------|--------|-------|--------|----------|
| doLeavesDecay              | X      | X     | X      | X        |
| doPhantomsSpawn            | X      | X     | X      | X        |
| doesFireDamage             | X      | X     | X      | X        |
| doEnderPearlsDamage        | X      | X     | X      | X        |
| doesPlayersDrown           | X      | X     | X      | X        |
| doPetFriendlyFire          | X      | X     | X      | X        |