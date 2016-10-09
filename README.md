# VotingLib

This is a library for comparing the effectiveness of different voting systems. It was written for a short research project in my [Math of Voting] class, and is likely not suitable for general-purpose use.

Here is sample output from running `VotingLib`:

```
Running tests: Baseline, Popularity, ManyCandidates, Polarized

Voting system           | Baseline               | Popularity             | ManyCandidates         | Polarized
------------------------+------------------------+------------------------+------------------------+-----------------------
Anti-plurality          | (162, 162) = 162 ±   0 | (234, 243) = 238 ±   4 | (178, 180) = 179 ±   1 | (863, 866) = 865 ±   1
Plurality               | (157, 158) = 158 ±   0 | (136, 137) = 137 ±   1 | (166, 168) = 167 ±   1 | (873, 876) = 874 ±   2
Instant runoff          | (155, 155) = 155 ±   0 | (135, 137) = 136 ±   1 | (147, 148) = 148 ±   0 | (857, 860) = 858 ±   1
Borda count             | (154, 155) = 154 ±   0 | (135, 137) = 136 ±   1 | (139, 140) = 139 ±   0 | (825, 827) = 826 ±   1
Range voting (scaled)   | (154, 154) = 154 ±   0 | (136, 137) = 136 ±   1 | (139, 140) = 139 ±   0 | (818, 820) = 819 ±   1
Range voting (unscaled) | (154, 154) = 154 ±   0 | (135, 137) = 136 ±   1 | (139, 139) = 139 ±   0 | (812, 814) = 813 ±   1
Max utility             | (154, 154) = 154 ±   0 | (135, 137) = 136 ±   1 | (139, 139) = 139 ±   0 | (812, 813) = 812 ±   1

Elapsed time: 33.8 seconds.
```

The parameters can be configured by manually changing the code in [`Main.java`][main].

[math of voting]: https://www.math.hmc.edu/~orrison/math189g/index.html
[main]: src/voting/Main.java
